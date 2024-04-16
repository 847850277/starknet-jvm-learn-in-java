const { RpcProvider, Account,stark,ec,json,CallData,hash} = require("starknet");
const fs = require('fs')
const axios = require('axios')


async function main() {
    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //每次部署都要根据dev生成的账号替换该记录值
    const privateKey0 = "0xc9ef4d92bc2979f15cca7deeb7b16963";
    const accountAddress0 = "0x5f655fa03d3faa1bac8038b4d752b9951f3166f6c0e3f0ee4206b60d13cf5dc";

    const account0 = new Account(provider, accountAddress0, privateKey0);

    const accountAXsierra = json.parse(fs.readFileSync("../../compiledContracts/cairo243/ArgentXAccount031.sierra.json").toString("ascii"));
    const accountAXcasm = json.parse(fs.readFileSync("../../compiledContracts/cairo243/ArgentXAccount031.casm.json").toString("ascii"));
    const ch = hash.computeContractClassHash(accountAXsierra);
    console.log("Class Hash of ArgentX contract =", ch);

    // Calculate future address of the ArgentX account
    const privateKeyAX = "0x1234567890abcdef987654321";
    console.log('AX account Private Key =', privateKeyAX);
    const starkKeyPubAX = ec.starkCurve.getStarkKey(privateKeyAX);
    console.log('AX account Public Key  =', starkKeyPubAX);

    // declare
    const respDecl = await account0.declare({ contract: accountAXsierra, casm: accountAXcasm });
    console.log("respDecl=", respDecl);
    //const contractAXclassHash = "0x029927c8af6bccf3f6fda035981e765a7bdbf18a2dc0d630494f8758aa908e2b";
    const contractAXclassHash= respDecl.class_hash;
    const transaction = await provider.waitForTransaction(respDecl.transaction_hash);
    console.log("Transaction=", transaction);
    console.log("ArgentX Cairo 1 contract declared")


    //compute future address of the ArgentX account
    const calldataAX = new CallData(accountAXsierra.abi);
    const ConstructorAXCallData = calldataAX.compile("constructor", {
        owner: starkKeyPubAX,
        guardian: "0"
    });
    const accountAXAddress = hash.calculateContractAddressFromHash(starkKeyPubAX, contractAXclassHash, ConstructorAXCallData, 0);
    console.log('Precalculated account address=', accountAXAddress);


    // fund account address before account creation
    // 10 ETH
    const { data: answer } = await axios.post('http://127.0.0.1:5050/mint', {
        "address": accountAXAddress,
        "amount": 10_000_000_000_000_000_000
    }, { headers: { "Content-Type": "application/json" } });
    console.log('Answer mint =', answer);
    // 10 STRK
    const { data: answer2 } = await axios.post('http://127.0.0.1:5050/mint', {
        "address": accountAXAddress,
        "amount": 10_000_000_000_000_000_000,
        "unit": "FRI",
    }, { headers: { "Content-Type": "application/json" } });
    console.log('Answer mint =', answer2);

    // deploy ArgentX account
    const accountAX = new Account(provider, accountAXAddress, privateKeyAX);
    const deployAccountPayload = {
        classHash: contractAXclassHash,
        constructorCalldata: ConstructorAXCallData,
        contractAddress: accountAXAddress,
        addressSalt: starkKeyPubAX
    };

    const { transaction_hash: AXdAth, contract_address: accountAXFinalAdress } = await accountAX.deployAccount(deployAccountPayload);
    console.log("deploy response ", AXdAth, accountAXFinalAdress);
    const transaction1 = await provider.waitForTransaction(AXdAth);
    console.log("transaction",transaction1);

}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });