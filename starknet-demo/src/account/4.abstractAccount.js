const { RpcProvider, Account,stark,ec,json,CallData,hash} = require("starknet");
const fs = require('fs')
const axios = require('axios')


async function main() {
    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //dev环境存在的账号信息
    const privateKey0 = "0x98e6ee0df795ec069d177d0b83f698e2";
    const accountAddress0 = "0x4eba44762b36732d2fb3bb9bf5008709dd47407c808cb4a16dc9422a6a58c2";
    //连接已经存在的账号
    const account0 = new Account(provider, accountAddress0, privateKey0);

    const aaPrivateKey = "0x12345789012345678901234";
    console.log('AA account Private Key =', aaPrivateKey);
    const aaPubKey = ec.starkCurve.getStarkKey(aaPrivateKey);
    console.log('AA account Public Key  =', aaPubKey);
    //declare my wallet contract
    const compiledAAaccount = json.parse(
        fs.readFileSync("../../compiledContracts/cairo060/myAccountAbstraction.json").toString("ascii")
    );
    const {transaction_hash: t_hash, class_hash: c_hash} = await account0.declare({ contract: compiledAAaccount});
    console.log("AA contract declared", t_hash, c_hash);
    const transaction = await provider.waitForTransaction(t_hash);
    console.log("Transaction=", transaction);
    // Calculate future address of the account
    const AAaccountConstructorCallData = CallData.compile({ super_admin_address: account0.address, publicKey: aaPubKey });
    const AAcontractAddress = hash.calculateContractAddressFromHash(aaPubKey, c_hash, AAaccountConstructorCallData, 0);
    console.log('Precalculated account address=', AAcontractAddress);

    // fund account address before account creation
    const { data: answer } = await axios.post('http://127.0.0.1:5050/mint', { "address": AAcontractAddress, "amount": 50_000_000_000_000_000_000, "lite": true }, { headers: { "Content-Type": "application/json" } });
    console.log('Answer mint =', answer);

    // deploy account
    const AAaccount = new Account(provider, AAcontractAddress, aaPrivateKey);
    //此处有问题
    const { transaction_hash, contract_address } = await AAaccount.deployAccount({ classHash: c_hash, constructorCalldata: AAaccountConstructorCallData, addressSalt: aaPubKey }, { maxFee: 9000000000000000 });
    console.log("deployAccount transaction_hash1=", transaction_hash, contract_address);
    const transaction1 = await provider.waitForTransaction(transaction_hash);
    console.log("Transaction=", transaction1);





}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });