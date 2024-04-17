const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    // Connect the deployed Test instance in devnet
    const testAddress = "0x7667469b8e93faa642573078b6bf8c790d3a6184b2a1bb39c5c923a732862e1";
    const testSierra = json.parse(fs.readFileSync("../../compiledContracts/cairo240/counter.sierra.json").toString("ascii"));
    const myTestContract = new Contract(testSierra.abi, testAddress, provider);
    console.log("collection myTestContract: ", myTestContract);

}


main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });