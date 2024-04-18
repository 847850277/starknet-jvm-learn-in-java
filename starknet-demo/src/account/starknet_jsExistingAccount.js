const { RpcProvider, Account,stark,ec, CallData,json,hash,Contract,shortString} = require("starknet");
const fs = require('fs')
const axios = require('axios')


async function main() {
    const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs
    console.log("chain Id =", shortString.decodeShortString(await provider.getChainId()), ", rpc", await provider.getSpecVersion());

    //连接已经存在的账户
    const privateKey0 = "0x826f1d71279c3a8e6e4d64c0c94df4ff";
    const address0 = "0x7e00afc528b590e8998d2c8a868d3b44201c6ceb58b89f9590c113b1c4660e6";
    const account0 = new Account(provider, address0, privateKey0);

    // Deploy an ERC20 contract
    console.log("Deployment Tx - ERC20 Contract to StarkNet...");

    const erc20mintableSierra = json.parse(fs.readFileSync("../../compiledContracts/cairo241/erc20mintableDecimalsOZ081.sierra.json").toString("ascii"));
    const erc20mintableCasm = json.parse(fs.readFileSync("../../compiledContracts/cairo241/erc20mintableDecimalsOZ081.casm.json").toString("ascii"));
    const DECIMALS = 2;

    // define the constructor :
    const erc20CallData = new CallData(erc20mintableSierra.abi);
    const ERC20ConstructorCallData = erc20CallData.compile("constructor", {
        name: "niceToken",
        symbol: "NIT",
        decimals: DECIMALS,
        initial_supply: 10000, // 100 tokens with 2 decimals
        owner: account0.address
    });

    console.log("constructor=", ERC20ConstructorCallData);
    const deployERC20Response = await account0.declareAndDeploy({
        contract: erc20mintableSierra,
        casm: erc20mintableCasm,
        constructorCalldata: ERC20ConstructorCallData
    });

    console.log("deployERC20Response=", deployERC20Response);


    //获取合约的地址
    const erc20Address = deployERC20Response.deploy.contract_address;
    console.log("ERC20 Contract deployed at address =", erc20Address);
    // Create a new erc20 contract object
    const erc20 = new Contract(erc20mintableSierra.abi, erc20Address, provider);
    erc20.connect(account0);

    // Check balance - should be 100
    console.log(`Calling StarkNet for account balance...`);
    const balanceInitial = await erc20.balanceOf(account0.address);
    console.log("account0 has a balance of :", balanceInitial);

    // Mint 5 tokens to account address
    console.log("Invoke Tx - Minting 5 tokens to account0...");
    const { transaction_hash: mintTxHash } = await erc20.mint(account0.address, 500n, { maxFee: 900_000_000_000_000 }); // maxFee optional
    // Wait for the invoke transaction to be accepted on StarkNet
    console.log(`Waiting for Tx to be Accepted on Starknet - Minting...`);
    await provider.waitForTransaction(mintTxHash);
    // Check balance - should be 105
    console.log(`Calling StarkNet for account balance...`);
    const balanceBeforeTransfer = await erc20.balanceOf(account0.address);
    console.log("account0 has a balance of :", balanceBeforeTransfer);

    //Execute tx transfer of 2x10 tokens, showing 3 ways to write data in Starknet
    console.log("Invoke Tx - Transfer 3x10 tokens back to erc20 contract...")
    const transferCallData = erc20.populate("transfer", {
        recipient: erc20Address,
        amount: 1000
    });
    console.log("transfer1...");
    const { transaction_hash: transferTxHash } = await account0.execute(transferCallData, undefined, { maxFee: 900_000_000_000_000 });  // maxFee optional
    await provider.waitForTransaction(transferTxHash);
    const balance1 = await erc20.balanceOf(account0.address);
    console.log("account0 has a balance of :", balance1);
    console.log("transfer2...");
    const { transaction_hash: transferTxHash2 } = await erc20.transfer(account0.address, 1000n);
    await provider.waitForTransaction(transferTxHash2);
    const balance2 = await erc20.balanceOf(account0.address);
    console.log("account0 has a balance of :", balance2);
    console.log("transfer3...");
    const { transaction_hash: transferTxHash3 } = await erc20.transfer(...transferCallData.calldata, { parseRequest: false });
    // Warning message is normal with the ParseRequest option de-activated
    await provider.waitForTransaction(transferTxHash3);
    const balance3 = await erc20.balanceOf(account0.address);
    console.log("account0 has a balance of :", balance3);


}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });