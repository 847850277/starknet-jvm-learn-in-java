const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0xc9675bbe7cd1fb87950087cd7bfedae7";
    const address0 = "0x2456a58ef8d9e021e2122a6cebd93ea1fa8fc77e9fc4137981cca1ce76bf459";
    const account0 = new Account(provider, address0, privateKey0);

    // 当前hash地址根据decare&&deploy.js计算出来进行更改
    const testAddress = "0x30abef09facfe1beae5dd6c4000ef21e8bde4926c94a9d5b0703fb7d77921c1";
    const compiledTest = json.parse(fs.readFileSync("../../compiledContracts/cairo240/counter.sierra.json").toString("ascii"));
    const myTestContract = new Contract(compiledTest.abi, testAddress, provider);
    console.log('Test Contract connected at =', myTestContract.address);

    // Interactions with the contract with call & invoke
    myTestContract.connect(account0);
    //调用的是匿名函数
    const  bal1  = await myTestContract.get_balance();
    const bal1b = await myTestContract.call("get_balance");
    console.log("Initial balance =", bal1);
    console.log("Initial balance =", bal1b);

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