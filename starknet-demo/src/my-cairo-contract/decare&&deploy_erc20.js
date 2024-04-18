const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0x705f56447dde7e4123fc6f7b8d71ecc4";
    const address0 = "0x7dc37ee5d77e73731bad4cb5cb792a14c7f81827a6e59cc55ac376d9bdb9519";
    const account0 = new Account(provider, address0, privateKey0);

    // Declare & deploy Test contract in devnet
    const testSierra = json.parse(fs.readFileSync("../../compiledContracts/mycontract/erc20/_28erc20_MyToken.contract_class.json").toString("ascii"));
    const testCasm = json.parse(fs.readFileSync("../../compiledContracts/mycontract/erc20/_28erc20_MyToken.compiled_contract_class.json").toString("ascii"));
    const myCallData = new CallData(testSierra.abi);
    //构造函数
    const constructor = myCallData.compile("constructor", { initial_supply: 100,recipient:account0.address });
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
    // const response = await myTestContract.call("hello_cairo");
    // console.log("response =", response);
    const  bal1  = await myTestContract.get_balance();
    console.log("Initial balance =", bal1);

}


main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });