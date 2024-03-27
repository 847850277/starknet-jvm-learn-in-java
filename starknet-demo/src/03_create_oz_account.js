//import {RpcProvider} from "starknet";
const { Account, constants, ec, json, stark, Provider, hash, CallData,RpcProvider } = require("starknet");


class LoggingRpcProvider extends RpcProvider {
    async fetchEndpoint(method, params) {
        const uri = this.nodeUrl + '/' + method;
        console.log('URI:', uri);
        console.log('Request Method:', method);
        console.log('Request Params:', params? params : method['params']);

        const response = await super.fetchEndpoint(method, params? params : method['params']);

        console.log('Response:', response);
        return response;
    }
}

async function main() {
    //const provider = new RpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" }); // only for starknet-devnet-rs
    const provider = new LoggingRpcProvider({ nodeUrl: "http://127.0.0.1:5050/rpc" });

    // new Open Zeppelin account v0.5.1
// Generate public and private key pair.
    const privateKey = stark.randomAddress();
    console.log('New OZ account:\nprivateKey=', privateKey);
    const starkKeyPub = ec.starkCurve.getStarkKey(privateKey);
    console.log('publicKey=', starkKeyPub);

    const OZaccountClassHash = '0x2794ce20e5f2ff0d40e632cb53845b9f4e526ebd8471983f7dbd355b721d5a';
// Calculate future address of the account
    const OZaccountConstructorCallData = CallData.compile({ publicKey: starkKeyPub });
    const OZcontractAddress = hash.calculateContractAddressFromHash(
        starkKeyPub,
        OZaccountClassHash,
        OZaccountConstructorCallData,
        0
    );
    console.log('Precalculated account address=', OZcontractAddress);

    const body = {
        address: OZcontractAddress.toString(),
        amount: 10_000_000_000_000_000_000,
        unit: "WEI"
    };

// Send the request
    fetch('http://127.0.0.1:5050/mint', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    }).then(response => response.json())
        .then(data => console.log(data))
        .catch((error) => {
            console.error('Error:', error);
        });

    const OZaccount = new Account(provider, OZcontractAddress, privateKey);

    const { transaction_hash, contract_address } = await OZaccount.deployAccount({
        classHash: OZaccountClassHash,
        constructorCalldata: OZaccountConstructorCallData,
        addressSalt: starkKeyPub,
    });

    await provider.waitForTransaction(transaction_hash);
    console.log('✅ New OpenZeppelin account created.\n   address =', contract_address);

}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });



