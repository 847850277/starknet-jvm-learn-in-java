const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0xb3e67c188778c4d47dd088c57643f196";
    const address0 = "0x3da2afc1fb14a3ba060a18d1298fbeb8d70f1db93f6cd36c4b8d3628ad11f9";
    const account0 = new Account(provider, address0, privateKey0);

    // Deploy Test instance in devnet-rs
    // 这个合约地址从哪里来的？？？？
    const testClassHash = "0xf568733c46280a8c36992fc53808061a877e75dff67b8d2367409a4c9896ce";
    const testSierra = json.parse(fs.readFileSync("../../compiledContracts/cairo240/counter.sierra.json").toString("ascii"));
    //estimate fee
    const myCallData = new CallData(testSierra.abi);
    console.log("myCallData=", myCallData);
    const constructor = myCallData.compile("constructor", { intial_value: 100 });
    console.log("constructor=", constructor);
    const { suggestedMaxFee: estimatedFee1 } = await account0.estimateDeployFee({ classHash: testClassHash, constructorCalldata: constructor });
    console.log("estimatedFee1=", estimatedFee1.toString());
    //deploy
    const deployResponse = await account0.deployContract({
        classHash: testClassHash, constructorCalldata: constructor
    }, { maxFee: estimatedFee1 * 11n / 10n });
    console.log("deployResponse=", deployResponse);
    const transaction = await provider.waitForTransaction(deployResponse.transaction_hash);
    console.log("Transaction=", transaction);
    // Connect the new contract :
    const myTestContract = new Contract(testSierra.abi, deployResponse.contract_address, provider);
    console.log("myTestContract=", myTestContract);

}


main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });