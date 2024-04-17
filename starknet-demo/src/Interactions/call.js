const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0x6292682f5db67f5d26d300b7abb2fb9a";
    const address0 = "0x70518752ffca33798c93bc86c98f651a016694b3299f0d7a3d39e3a687a93e7";
    const account0 = new Account(provider, address0, privateKey0);

    // 当前hash地址根据decare&&deploy.js计算出来进行更改
    const testAddress = "0x52e27e600bdc090a6971715f6c9c1da9e64c47f0983ee4fd6d6a129d393aa0c";
    const compiledTest = json.parse(fs.readFileSync("../../compiledContracts/cairo240/counter.sierra.json").toString("ascii"));
    const myTestContract = new Contract(compiledTest.abi, testAddress, provider);
    console.log('Test Contract connected at =', myTestContract.address);

    // Interactions with the contract with call & invoke
    myTestContract.connect(account0);
    const  bal1  = await myTestContract.get_balance();
    const bal1b = await myTestContract.call("get_balance");
    console.log("Initial balance =", bal1);
    console.log("Initial balance =", bal1b);
    // estimate fee
    const { suggestedMaxFee: estimatedFee1 } = await account0.estimateInvokeFee({ contractAddress: testAddress, entrypoint: "increase_counter", calldata: [10] });

    const resu = await myTestContract.invoke("increase_counter", [10]);
    console.log("resu=", resu);
    await provider.waitForTransaction(resu.transaction_hash);
    const bal2 = await myTestContract.get_balance();
    console.log("Final balance =", bal2);
    console.log('✅ Test completed.');


}


main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });