const { RpcProvider, Account,stark,ec,json,CallData,hash,Contract} = require("starknet");
const fs = require('fs')
const axios = require('axios')

async function main() {

    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs

    //连接已经存在的账户
    const privateKey0 = "0x826f1d71279c3a8e6e4d64c0c94df4ff";
    const address0 = "0x7e00afc528b590e8998d2c8a868d3b44201c6ceb58b89f9590c113b1c4660e6";
    const account0 = new Account(provider, address0, privateKey0);

    // 当前hash地址根据decare&&deploy.js计算出来进行更改
    const testAddress = "0x10a7adfec124b27795e83d244c808f501f4bf56709d3be15c7953379b9651fd";
    const compiledTest = json.parse(fs.readFileSync("../../compiledContracts/cairo240/counter.sierra.json").toString("ascii"));
    // Create a new contract object
    const myTestContract = new Contract(compiledTest.abi, testAddress, provider);
    console.log('Test Contract connected at =', myTestContract.address);

    // Interactions with the contract with call & invoke
    myTestContract.connect(account0);
    const  bal1  = await myTestContract.get_balance();
    console.log("Initial balance =", bal1);
    const bal1b = await myTestContract.call("get_balance");
    console.log("Initial balance =", bal1b);
    const resu = await myTestContract.invoke("increase_counter", [1000]);
    console.log("resu=", resu)
    await provider.waitForTransaction(resu.transaction_hash);
    const bal2 = await myTestContract.get_balance();
    console.log("Final balance =", bal2);

}


main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });