const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0x65d01c04157d0d9d6907a6b8bf553093";
    const address0 = "0x333ee3b4cc818dcf88adab96b4cd26bd2a405ef507b0ba62aac5a7750486b95";
    const account0 = new Account(provider, address0, privateKey0);

    // Declare & deploy Test contract in devnet
    const testSierra = json.parse(fs.readFileSync("../../compiledContracts/mycontract/name_registry/_04name_registry_NameRegistry.contract_class.json").toString("ascii"));
    const testCasm = json.parse(fs.readFileSync("../../compiledContracts/mycontract/name_registry/_04name_registry_NameRegistry.compiled_contract_class.json").toString("ascii"));
    const myCallData = new CallData(testSierra.abi);
    //构造函数,参考 cario-contract/_04name_registry/src/NameRegistry.cairo:64
    const constructor = myCallData.compile("constructor", { owner:{address:address0,name:99}});
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

    //调用函数
    const setResponse1 = await myTestContract.get_contract_name();
    console.log("setResponse1 =", setResponse1);

    // const setResponse2 = await myTestContract.invoke("get_owner_storage_address");
    // console.log("setResponse2 =", setResponse2);

    const setResponse3 = await myTestContract.get_owner();
    console.log("setResponse3 =", setResponse3);

    //错误的用法
    // const setResponse4 = await myTestContract.get_name();
    // console.log("setResponse4 =", setResponse4);

    const setResponse4 = await myTestContract.get_name(setResponse3.address);
    console.log("setResponse4 =", setResponse4);

    //TODO 用类库解析
    const helloCallData = new CallData(myCallData.abi);
    helloCallData.decodeParameters()

    //store_name函数
    const setResponse5 = await myTestContract.store_name({name:"test",registration_type:1});
    console.log("setResponse5 =", setResponse5);

    const setResponse6 = await myTestContract.get_owner();
    console.log("setResponse6 =", setResponse6);

    const setResponse7 = await myTestContract.get_name(setResponse6.address);
    console.log("setResponse7 =", setResponse7);


}


main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });