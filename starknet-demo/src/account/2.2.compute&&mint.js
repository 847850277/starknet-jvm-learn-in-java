const { RpcProvider, Account,stark,ec,json,CallData,hash} = require("starknet");
const fs = require('fs')
const axios = require('axios')


async function main() {
    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    // new Open Zeppelin account v0.5.1
    // Generate public and private key pair.
    const privateKey = stark.randomAddress();
    console.log('privateKey=', privateKey);
    const starkKeyPub = ec.starkCurve.getStarkKey(privateKey);
    console.log('publicKey=', starkKeyPub);

    const preAccountHash = '0x61dac032f228abef9c6626f995015233097ae253a7f72d68552db02f2971b8f';

    // Calculate future address of the ArgentX account
    const AXproxyConstructorCallData = CallData.compile({
        implementation: preAccountHash,
        selector: hash.getSelectorFromName('initialize'),
        calldata: CallData.compile({ signer: starkKeyPub, guardian: '0' }),
    });

    console.log("AXproxyConstructorCallData: ", AXproxyConstructorCallData);
    const AXcontractAddress = hash.calculateContractAddressFromHash(
        starkKeyPub,
        preAccountHash,
        AXproxyConstructorCallData,
        0
    );
    console.log('Precalculated account address=', AXcontractAddress);

    // fund account address before account creation
    const { data: answer } = await axios.post('http://127.0.0.1:5050/mint', { "address": AXcontractAddress, "amount": 10_000_000_000_000_000_000, "lite": true }, { headers: { "Content-Type": "application/json" } });
    console.log('Answer mint =', answer);
}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });