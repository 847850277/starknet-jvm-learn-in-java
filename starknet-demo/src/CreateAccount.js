import React from 'react';
import { Account, constants, ec, json, stark, Provider, hash, CallData } from 'starknet';



const CreateAccount = () => {
    return (
        <div>
            <h1>Create Account</h1>
            222

        </div>
    );
};

// connect provider
//const provider = new Provider({ sequencer: { network: constants.NetworkName.SN_GOERLI } });
const provider = new Provider({ sequencer: { baseUrl: 'http://127.0.0.1:5050' } });



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


export default CreateAccount;