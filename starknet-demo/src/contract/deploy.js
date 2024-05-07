const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0xcec0c3486223b569c0cb2c22b236f753";
    const address0 = "0xb04631964dc2207e06f404c6136e1c51a66deaf08a675ef8a73d7341d97030";
    const account0 = new Account(provider, address0, privateKey0);

    // Deploy Test instance in devnet-rs
    // 这个合约地址从哪里来的？？？？
    const testClassHash = "0x4c9e06b73ffb0bb0e00a7ef039b1773e08be94e0ff7244a37656f9ba2c5aa0e";
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