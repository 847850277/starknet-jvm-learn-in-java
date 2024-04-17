const { RpcProvider, Account,stark,ec,json,CallData,hash} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0x98e6ee0df795ec069d177d0b83f698e2";
    const address0 = "0x4eba44762b36732d2fb3bb9bf5008709dd47407c808cb4a16dc9422a6a58c2";
    const account0 = new Account(provider, address0, privateKey0);

    // Declare Test contract in devnet
    const testSierra = json.parse(fs.readFileSync("../../compiledContracts/cairo240/counter.sierra.json").toString("ascii"));
    const testCasm = json.parse(fs.readFileSync("../../compiledContracts/cairo240/counter.casm.json").toString("ascii"));
    //计算出建议的费用
    const { suggestedMaxFee: fee1 } = await account0.estimateDeclareFee({ contract: testSierra, casm: testCasm });

    console.log("fee1=", fee1.toString());

    const declareResponse = await account0.declare({ contract: testSierra, casm: testCasm }, { maxFee: fee1 * 11n / 10n });
    console.log("declareResponse=", declareResponse);
    const transaction = await provider.waitForTransaction(declareResponse.transaction_hash);
    console.log("Transaction=", transaction);



}


main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });