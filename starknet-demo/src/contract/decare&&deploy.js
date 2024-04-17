const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0x6292682f5db67f5d26d300b7abb2fb9a";
    const address0 = "0x70518752ffca33798c93bc86c98f651a016694b3299f0d7a3d39e3a687a93e7";
    const account0 = new Account(provider, address0, privateKey0);

    // Declare & deploy Test contract in devnet
    const testSierra = json.parse(fs.readFileSync("../../compiledContracts/cairo240/counter.sierra.json").toString("ascii"));
    const testCasm = json.parse(fs.readFileSync("../../compiledContracts/cairo240/counter.casm.json").toString("ascii"));
    const myCallData = new CallData(testSierra.abi);
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


}


main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });