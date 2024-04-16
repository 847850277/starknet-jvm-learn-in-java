const { RpcProvider, Account,stark,ec,json,CallData,hash} = require("starknet");
const fs = require('fs')
const axios = require('axios')


async function main() {
    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    // initialize existing predeployed account 0 of Devnet
    const privateKey0 = "0x71d7bb07b9a64f6f78ac4c816aff4da9";
    const accountAddress0 = "0x64b48806902a367c8598f4f95c305e8c1a1acba5f082d294a43793113115691";
    const account0 = new Account(provider, accountAddress0, privateKey0);
    console.log("✅ Account 0 connected.\n");

    // new Open Zeppelin account v0.8.0 (Cairo 1) :
    const privateKey = stark.randomAddress();
    console.log("private key: ", privateKey);
    const pubKey = ec.starkCurve.getStarkKey(privateKey);
    console.log("public key: ", pubKey);

    //declare OZ wallet contract
    const compiledOZAccount = json.parse(
        fs.readFileSync("../../compiledContracts/cairo241/accountOZ081.sierra.json").toString("ascii")
    );
    //console.log("compiledOZAccount: ", compiledOZAccount);
    const casmOZAccount = json.parse(
        fs.readFileSync("../../compiledContracts/cairo241/accountOZ081.casm.json").toString("ascii")
    )
    const { transaction_hash: declTH, class_hash: decClassHash } = await account0.declareIfNot({ contract: compiledOZAccount, casm: casmOZAccount });
    console.log('OpenZeppelin account class hash =', decClassHash);
    if (declTH) {await provider.waitForTransaction(declTH);}

    // Calculate future address of the account
    const OZaccountConstructorCallData = CallData.compile({ publicKey: pubKey });
    console.log("OZaccountConstructorCallData: ", OZaccountConstructorCallData);
    const OZcontractAddress = hash.calculateContractAddressFromHash( pubKey,decClassHash, OZaccountConstructorCallData, 0);
    console.log("OZcontractAddress: ", OZcontractAddress);

    // account_balance
    const response1 = await axios.get('http://127.0.0.1:5050/account_balance', {
        params: {
            address: OZcontractAddress
        }
    });
    console.log("account_balance: ", response1.data);

    // fund account address before account creation
    const { data: answer } = await axios.post('http://127.0.0.1:5050/mint', { "address": OZcontractAddress, "amount": 10_000_000_000_000_000_000, "lite": true }, { headers: { "Content-Type": "application/json" } });
    console.log('Answer mint =', answer);
    // 10 ETH

    // account_balance
    const response = await axios.get('http://127.0.0.1:5050/account_balance', {
        params: {
            address: OZcontractAddress
        }
    });
    console.log("account_balance: ", response.data);

    //deploy account
    const OZaccount = new Account(provider, OZcontractAddress, privateKey);

    const {transaction_hash, contract_address} = await OZaccount.deployAccount(
        {
            classHash: decClassHash,
            constructorCalldata: OZaccountConstructorCallData,
            addressSalt: pubKey,
            contractAddress:OZcontractAddress
        }
    )

    console.log("deploy account response: ", transaction_hash, contract_address)

    const transaction = await provider.waitForTransaction(transaction_hash);
    console.log("transaction: ", transaction);


}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });