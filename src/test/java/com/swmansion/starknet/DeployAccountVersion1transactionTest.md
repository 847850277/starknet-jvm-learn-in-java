
### code block to sign and send deploy account v1 transaction 1

```kotlin


@Test
fun `sign and send deploy account v1 transaction`() {
  val privateKey = Felt(11111)
  val publicKey = StarknetCurve.getPublicKey(privateKey)

  val salt = Felt.ONE
  val calldata = listOf(publicKey)
  val address = ContractAddressCalculator.calculateAddressFromHash(
    classHash = accountContractClassHash,
    calldata = calldata,
    salt = salt,
  )
  devnetClient.prefundAccountEth(address)
  println("===============================\nsign and send deploy account v1 transaction prefundAccountEth success!")

  val account = StandardAccount(
    address,
    privateKey,
    provider,
    chainId,
  )
  val payload = account.signDeployAccountV1(
    classHash = accountContractClassHash,
    salt = salt,
    calldata = calldata,
    // 10*fee from estimate deploy account fee
    maxFee = Felt.fromHex("0x11fcc58c7f7000"),
  )

  val response = provider.deployAccount(payload).send()

  // Make sure the address matches the calculated one
  assertEquals(address, response.address)

  // Make sure tx matches what we sent
  val tx = provider.getTransaction(response.transactionHash).send() as DeployAccountTransactionV1
  assertEquals(payload.classHash, tx.classHash)
  assertEquals(payload.salt, tx.contractAddressSalt)
  assertEquals(payload.constructorCalldata, tx.constructorCalldata)
  assertEquals(payload.version, tx.version)
  assertEquals(payload.nonce, tx.nonce)
  assertEquals(payload.maxFee, tx.maxFee)
  assertEquals(payload.signature, tx.signature)

  
}
```


### code block to sign and send deploy account v1 transaction 2

```kotlin




```
