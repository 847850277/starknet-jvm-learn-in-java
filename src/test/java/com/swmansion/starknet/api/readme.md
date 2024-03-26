this package is test for [apis](https://github.com/0xSpaceShard/starknet-devnet-rs)
- 充值账户
  
  ```bash
  
  
  curl --location 'http://127.0.0.1:5050/mint' \
--header 'accept: application/json' \
--header 'content-type: application/json' \
--data '{
"address": "0x00998f1Aa20CB1D57da61F5dae2bF5F35BCfB42D41c7bb9E33D3D281a1cE3b25",
"amount": 500000000000000000000000000000,
"unit": "WEI"
}'
  
  ```

- 部署账户

  ```bash

  -- request
  sncast --json --path-to-scarb-toml /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/src/test/resources/contracts/Scarb.toml --accounts-file /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/src/test/resources/accounts/standard_account_test/starknet_open_zeppelin_accounts.json --url http://127.0.0.1:5050/rpc --account __default__ account deploy --name standard_account_test --max-fee 0x38d7ea4c68000 --class-hash 0x4d07e40e93398ed3c76981e72dd1fd22557a78ce36c0515f679e27f0bb5bc5f

  -- response
  {"command":"account deploy","transaction_hash":"0x1cbe432a0c94353fd0e412626faed02cc7adb5098ba5d899d4a0743258d071a"}
  ```
- 查看交易状态

  ```bash

  -- request
  curl -X POST 'http://127.0.0.1:5050/rpc' \
  -H 'Content-Type: application/json' \
  -d '{"jsonrpc":"2.0","method":"starknet_getTransactionStatus","id":0,"params":{"transaction_hash":"0x1cbe432a0c94353fd0e412626faed02cc7adb5098ba5d899d4a0743258d071a"}}'

  -- response
  {"jsonrpc":"2.0","id":0,"result":{"finality_status":"ACCEPTED_ON_L2","execution_status":"SUCCEEDED"}}%   

  ```
  
- 创建账户
  - 创建账户
  - 为账户提供资金
  - 部署账户


```bash

0x025e5b9982a2c8e04cb477d7c71aec25e2043e4d52cb61604208e1939acfb8bf

curl -d '{"amount":8646000000000, "address":"0x0472fC1f0F96eAf99c607E7CDc925713C77b78da9811149733B18DF00Ab3B260"}' -H "Content-Type: application/json" -X POST http://127.0.0.1:5050/mint


```