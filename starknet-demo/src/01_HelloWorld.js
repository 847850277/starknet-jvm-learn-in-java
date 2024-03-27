//import {RpcProvider} from "starknet";
const { RpcProvider, Account} = require("starknet");

async function main() {
    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs
    console.log("Provider connected to Starknet-devnet-rs");
    console.log(provider);

    const privateKey = "0x71d7bb07b9a64f6f78ac4c816aff4da9";
    const accountAddress = "0x64b48806902a367c8598f4f95c305e8c1a1acba5f082d294a43793113115691";
    const account0 = new Account(provider, accountAddress, privateKey);
    console.log("✅ Account 0 connected.\n");
    console.log(account0);

}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });