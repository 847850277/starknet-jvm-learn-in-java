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

    //这个哈希值很重要！在测试网提前部署的账号信息
    /**
     * Predeployed accounts using class with hash: 0x61dac032f228abef9c6626f995015233097ae253a7f72d68552db02f2971b8f
     * Initial balance of each account: 1000000000000000000000 WEI and FRI
     * Seed to replicate this account sequence: 2573139465
     */
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


    // fund account address before account creation
    const { data: answer } = await axios.post('http://127.0.0.1:5050/mint', { "address": OZcontractAddress, "amount": 10_000_000_000_000_000_000, "lite": true }, { headers: { "Content-Type": "application/json" } });
    console.log('Answer mint =', answer);


    const OZaccount = new Account(provider, OZcontractAddress, privateKey);

    const { transaction_hash, contract_address } = await OZaccount.deployAccount({
        classHash: OZaccountClassHash,
        constructorCalldata: OZaccountConstructorCallData,
        addressSalt: starkKeyPub,
    });
    console.log("deploy response ", transaction_hash, contract_address);

    const transaction = await provider.waitForTransaction(transaction_hash);
    console.log("transaction", transaction);
}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });