//import {RpcProvider} from "starknet";
const { RpcProvider, Account,ec,stark,CallData, hash} = require("starknet");


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

    console.log("Provider connected to Starknet-devnet-rs");
    //new Argent X account v0.2.3
    const argentXproxyClassHash = '0x25ec026985a3bf9d0cc1fe17326b245dfdc3ff89b8fde106542a3ea56c5a918';
    const argentXaccountClassHash =
        '0x033434ad846cdd5f23eb73ff09fe6fddd568284a0fb7d1be20ee482f044dabe2';

// Generate public and private key pair.
    const privateKeyAX = stark.randomAddress();
    console.log('AX_ACCOUNT_PRIVATE_KEY=', privateKeyAX);
    const starkKeyPubAX = ec.starkCurve.getStarkKey(privateKeyAX);
    console.log('AX_ACCOUNT_PUBLIC_KEY=', starkKeyPubAX);

// Calculate future address of the ArgentX account
    const AXproxyConstructorCallData = CallData.compile({
        implementation: argentXaccountClassHash,
        selector: hash.getSelectorFromName('initialize'),
        calldata: CallData.compile({ signer: starkKeyPubAX, guardian: '0' }),
    });
    const AXcontractAddress = hash.calculateContractAddressFromHash(
        starkKeyPubAX,
        argentXproxyClassHash,
        AXproxyConstructorCallData,
        0
    );
    console.log('Precalculated account address=', AXcontractAddress);


    const body = {
        address: AXcontractAddress.toString(),
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

    //const estimatedFee = await estimateBraavosAccountDeployFee(privateKeyAX, provider);


    const accountAX = new Account(provider, AXcontractAddress.toString(), privateKeyAX,'1');

    const deployAccountPayload = {
        classHash: argentXproxyClassHash,
        constructorCalldata: AXproxyConstructorCallData,
        contractAddress: AXcontractAddress,
        addressSalt: starkKeyPubAX,
    };

    const { transaction_hash: AXdAth, contract_address: AXcontractFinalAddress } =
        await accountAX.deployAccount(deployAccountPayload);
    console.log('✅ ArgentX wallet deployed at:', AXcontractFinalAddress);
    await provider.waitForTransaction(AXdAth);

}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });



