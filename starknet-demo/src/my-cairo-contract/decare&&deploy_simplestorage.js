const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0xc9290f7e5a45dc71e7a2e9e28d1d9212";
    const address0 = "0xa94794e4605262584a4073db2e5d61806e8d404695da20d153fc8a1fa7e895";
    const account0 = new Account(provider, address0, privateKey0);

    // Declare & deploy Test contract in devnet
    const testSierra = json.parse(fs.readFileSync("../../compiledContracts/mycontract/simplestorage/_03simple_storage_SimpleStorage.contract_class.json").toString("ascii"));
    const testCasm = json.parse(fs.readFileSync("../../compiledContracts/mycontract/simplestorage/_03simple_storage_SimpleStorage.compiled_contract_class.json").toString("ascii"));
    const myCallData = new CallData(testSierra.abi);
    //构造函数
    const constructor = myCallData.compile("constructor", { intial_value: 100 });
    const deployResponse = await account0.declareAndDeploy({
        contract: testSierra,
        casm: testCasm,
        constructorCalldata: constructor,
        salt: "0"
    });
    console.log("deployResponse=", deployResponse);
    // In case of constructor, add for example : ,constructorCalldata: [encodeShortString('Token'),encodeShortString('ERC20'),account.address,],
    // Connect the new contract instance :
    const myTestContract = new Contract(testSierra.abi, deployResponse.deploy.contract_address, provider);
    console.log("myTestContract=", myTestContract);

    // Interactions with the contract with call & invoke
    myTestContract.connect(account0);
    //调用的是匿名函数
    const setResponse = await myTestContract.invoke("set", [10]);
    console.log("setResponse =", setResponse);
    await provider.waitForTransaction(setResponse.transaction_hash);
    const getResponse = await myTestContract.get();
    console.log("getResponse =", getResponse);

    const setResponse1 = await myTestContract.invoke("set", [0]);
    console.log("setResponse =", setResponse1);
    await provider.waitForTransaction(setResponse1.transaction_hash);
    const getResponse1 = await myTestContract.get();
    console.log("getResponse =", getResponse1);



}


main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });