const { RpcProvider, Account,stark,ec,json,CallData,hash} = require("starknet");
const fs = require('fs')
const axios = require('axios')


async function main() {
    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    // new Open Zeppelin account v0.5.1
    // Generate public and private key pair.
    //const privateKey = stark.randomAddress();
    const privateKey = "11121";
    console.log('New OZ account:\nprivateKey=', privateKey);
    const starkKeyPub = ec.starkCurve.getStarkKey(privateKey);
    console.log('publicKey=', starkKeyPub);

    const OZaccountClassHash = '0x61dac032f228abef9c6626f995015233097ae253a7f72d68552db02f2971b8f';
    // Calculate future address of the account
    const OZaccountConstructorCallData = CallData.compile({ publicKey: starkKeyPub });
    const OZcontractAddress = hash.calculateContractAddressFromHash(
        starkKeyPub,
        OZaccountClassHash,
        OZaccountConstructorCallData,
        0
    );
    console.log('Precalculated account address=', OZcontractAddress);


}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });