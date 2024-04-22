const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract,CairoCustomEnum} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0x2cf91d0b929f32b337d44736dc57e495";
    const address0 = "0x4b7d08d00133d3ff9df1b4f3019fd7981f3a40f210f16d1080581f0a685e77a";
    const account0 = new Account(provider, address0, privateKey0);

    // Declare & deploy Test contract in devnet
    const testSierra = json.parse(fs.readFileSync("../../compiledContracts/mycontract/my_test_contract/_05my_test_contract_MyTestContract.contract_class.json").toString("ascii"));
    const testCasm = json.parse(fs.readFileSync("../../compiledContracts/mycontract/my_test_contract/_05my_test_contract_MyTestContract.compiled_contract_class.json").toString("ascii"));
    const myCallData = new CallData(testSierra.abi);
    //构造函数,参考 cario-contract/_04name_registry/src/NameRegistry.cairo:64
    const constructor = myCallData.compile("constructor", { intial_value:100});
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

    const response1 = await myTestContract.test2(10);

    console.log("response1",response1);

    console.log("response1",response1.unwrap(),response1.activeVariant())

    const response2 = await myTestContract.test2(100);

    console.log("response2",response2);



}


main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });