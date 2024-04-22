const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract,CairoCustomEnum} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0xf9f1167dfa384de9be7a57f7bbd29c11";
    const address0 = "0x1a34b4767795562e1fef04a6868b62d0bf671ef9466a744548e37c5076889a8";
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

    const response1 = await myTestContract.increase_counter(10);
    console.log("response1",response1);
    const response2 = await myTestContract.decrease_counter(11);
    console.log("response2",response2);

    const response3 = await myTestContract.test1(1);
    console.log("response3",response3);

    const response4 = await myTestContract.test2(100);
    console.log("response4",response4);

    const response5 = await myTestContract.call("test2",[200]);
    console.log("response5",response5);

    const response6 = await myTestContract.call("test3",[101]);
    console.log("response6",response6);

    const response7 = await myTestContract.call("test3",[10]);
    console.log("response7",response7);






}


main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });