package com.dxc.ssi.agent.ledger.indy.genesis

import com.dxc.utils.FileUtils

class GenesisGenerator(
    private val indyPoolIp: String?,
    private val dir: String,
    private val generatedGenesisFileName: String = "genesys.txn"
) {

    fun initGenesisFile(): String {

        var genesisContent: String = buildStandardSovrinPoolGenesis()
        if(indyPoolIp != null)
            genesisContent = buildStandardIndyPoolGenesis(indyPoolIp)
        
        println("Generated genesys: $genesisContent")
        val filePath = "$dir/$generatedGenesisFileName"
        if (FileUtils.fileExists(filePath)) {
            FileUtils.deleteRecursively(filePath)
        }
        FileUtils.createFileWithContent(filePath, genesisContent)

        return filePath

    }


    private fun buildStandardIndyPoolGenesis(poolIp: String): String {
        val poolIpStr = poolIp

        return """
            {"reqSignature":{},"txn":{"data":{"data":{"alias":"Node1","blskey":"4N8aUNHSgjQVgkpm8nhNEfDf6txHznoYREg9kirmJrkivgL4oSEimFF6nsQ6M41QvhM2Z33nves5vfSn9n1UwNFJBYtWVnHYMATn76vLuL3zU88KyeAYcHfsih3He6UHcXDxcaecHVz6jhCYz1P2UZn2bDVruL5wXpehgBfBaLKm3Ba","blskey_pop":"RahHYiCvoNCtPTrVtP7nMC5eTYrsUA8WjXbdhNc8debh1agE9bGiJxWBXYNFbnJXoXhWFMvyqhqhRoq737YQemH5ik9oL7R4NTTCz2LEZhkgLJzB3QRQqJyBNyv7acbdHrAT8nQ9UkLbaVL9NBpnWXBTw4LEMePaSHEw66RzPNdAX1","client_ip":"$poolIpStr","client_port":9702,"node_ip":"$poolIpStr","node_port":9701,"services":["VALIDATOR"]},"dest":"Gw6pDLhcBcoQesN72qfotTgFa7cbuqZpkX3Xo6pLhPhv"},"metadata":{"from":"Th7MpTaRZVRYnPiabds81Y"},"type":"0"},"txnMetadata":{"seqNo":1,"txnId":"fea82e10e894419fe2bea7d96296a6d46f50f93f9eeda954ec461b2ed2950b62"},"ver":"1"}
            {"reqSignature":{},"txn":{"data":{"data":{"alias":"Node2","blskey":"37rAPpXVoxzKhz7d9gkUe52XuXryuLXoM6P6LbWDB7LSbG62Lsb33sfG7zqS8TK1MXwuCHj1FKNzVpsnafmqLG1vXN88rt38mNFs9TENzm4QHdBzsvCuoBnPH7rpYYDo9DZNJePaDvRvqJKByCabubJz3XXKbEeshzpz4Ma5QYpJqjk","blskey_pop":"Qr658mWZ2YC8JXGXwMDQTzuZCWF7NK9EwxphGmcBvCh6ybUuLxbG65nsX4JvD4SPNtkJ2w9ug1yLTj6fgmuDg41TgECXjLCij3RMsV8CwewBVgVN67wsA45DFWvqvLtu4rjNnE9JbdFTc1Z4WCPA3Xan44K1HoHAq9EVeaRYs8zoF5","client_ip":"$poolIpStr","client_port":9704,"node_ip":"$poolIpStr","node_port":9703,"services":["VALIDATOR"]},"dest":"8ECVSk179mjsjKRLWiQtssMLgp6EPhWXtaYyStWPSGAb"},"metadata":{"from":"EbP4aYNeTHL6q385GuVpRV"},"type":"0"},"txnMetadata":{"seqNo":2,"txnId":"1ac8aece2a18ced660fef8694b61aac3af08ba875ce3026a160acbc3a3af35fc"},"ver":"1"}
            {"reqSignature":{},"txn":{"data":{"data":{"alias":"Node3","blskey":"3WFpdbg7C5cnLYZwFZevJqhubkFALBfCBBok15GdrKMUhUjGsk3jV6QKj6MZgEubF7oqCafxNdkm7eswgA4sdKTRc82tLGzZBd6vNqU8dupzup6uYUf32KTHTPQbuUM8Yk4QFXjEf2Usu2TJcNkdgpyeUSX42u5LqdDDpNSWUK5deC5","blskey_pop":"QwDeb2CkNSx6r8QC8vGQK3GRv7Yndn84TGNijX8YXHPiagXajyfTjoR87rXUu4G4QLk2cF8NNyqWiYMus1623dELWwx57rLCFqGh7N4ZRbGDRP4fnVcaKg1BcUxQ866Ven4gw8y4N56S5HzxXNBZtLYmhGHvDtk6PFkFwCvxYrNYjh","client_ip":"$poolIpStr","client_port":9706,"node_ip":"$poolIpStr","node_port":9705,"services":["VALIDATOR"]},"dest":"DKVxG2fXXTU8yT5N7hGEbXB3dfdAnYv1JczDUHpmDxya"},"metadata":{"from":"4cU41vWW82ArfxJxHkzXPG"},"type":"0"},"txnMetadata":{"seqNo":3,"txnId":"7e9f355dffa78ed24668f0e0e369fd8c224076571c51e2ea8be5f26479edebe4"},"ver":"1"}
            {"reqSignature":{},"txn":{"data":{"data":{"alias":"Node4","blskey":"2zN3bHM1m4rLz54MJHYSwvqzPchYp8jkHswveCLAEJVcX6Mm1wHQD1SkPYMzUDTZvWvhuE6VNAkK3KxVeEmsanSmvjVkReDeBEMxeDaayjcZjFGPydyey1qxBHmTvAnBKoPydvuTAqx5f7YNNRAdeLmUi99gERUU7TD8KfAa6MpQ9bw","blskey_pop":"RPLagxaR5xdimFzwmzYnz4ZhWtYQEj8iR5ZU53T2gitPCyCHQneUn2Huc4oeLd2B2HzkGnjAff4hWTJT6C7qHYB1Mv2wU5iHHGFWkhnTX9WsEAbunJCV2qcaXScKj4tTfvdDKfLiVuU2av6hbsMztirRze7LvYBkRHV3tGwyCptsrP","client_ip":"$poolIpStr","client_port":9708,"node_ip":"$poolIpStr","node_port":9707,"services":["VALIDATOR"]},"dest":"4PS3EDQ3dW1tci1Bp6543CfuuebjFrg36kLAUcskGfaA"},"metadata":{"from":"TWwCRQRZ2ZHMJFn9TzLp7W"},"type":"0"},"txnMetadata":{"seqNo":4,"txnId":"aa5e817d7cc626170eca175822029339a444eb0ee8f0bd20d3b0b76e566fb008"},"ver":"1"}
        """.trimIndent()
    }

    private fun buildStandardSovrinPoolGenesis(): String {
        return """
            {"reqSignature":{},"txn":{"data":{"data":{"alias":"FoundationBuilder","blskey":"3gmhmqpPLqznZF3g3niodaHjbpsB6TEeE9SpgXgBnZJLmXgeRzJqTLajVwbhxrkomJFTFU4ohDC4ZRXKbUPCQywJuPAQnst8XBtCFredMECn4Z3goi1mNt5QVRdU8Ue2xMSkdLpsQMjCsNwYUsBguwXYUQnDXQXnHqRkK9qrivucQ5Z","blskey_pop":"RHWacPhUNc9JWsGNdmWYHrAvvhsow399x3ttNKKLDpz9GkxxnTKxtiZqarkx4uP5ByTwF4kM8nZddFKWuzoKizVLttALQ2Sc2BNJfRzzUZMNeQSnESkKZ7U5vE2NhUDff6pjANczrrDAXd12AjSG61QADWdg8CVciZFYtEGmKepwzP","client_ip":"35.161.146.16","client_port":"9702","node_ip":"50.112.53.5","node_port":"9701","services":["VALIDATOR"]},"dest":"GVvdyd7Y6hsBEy5yDDHjqkXgH8zW34K74RsxUiUCZDCE"},"metadata":{"from":"V5qJo72nMeF7x3ci8Zv2WP"},"type":"0"},"txnMetadata":{"seqNo":1,"txnId":"fe991cd590fff10f596bb6fe2362229de47d49dd50748e38b96f368152be29c7"},"ver":"1"}
            {"reqSignature":{},"txn":{"data":{"data":{"alias":"vnode1","blskey":"t5jtREu8au2dwFwtH6QWopmTGxu6qmJ3iSnk321yLgeu7mHQRXf2ZCBuez8KCAQvFZGqqAoy2FcYvDGCqQxRCz9qXKgiBtykzxjDjYu87JECwwddnktz5UabPfZmfu6EoDn4rFxvd4myPu2hksb5Z9GT6UeoEYi7Ub3yLFQ3xxaQXc","blskey_pop":"QuHB7tiuFBPQ6zPkwHfMtjzWqXJBLACtfggm7zCRHHgdva18VN4tNg7LUU2FfKGQSLZz1M7oRxhhgJkZLL19aGvaHB2MPtnBWK9Hr8LMiwi95UjX3TVXJri4EvPjQ6UUvHrjZGUFvKQphPyVTMZBJwfkpGAGhpbTQuQpEH7f56m1X5","client_ip":"206.189.143.34","client_port":"9796","node_ip":"206.189.143.34","node_port":"9797","services":["VALIDATOR"]},"dest":"9Aj2LjQ2fwszJRSdZqg53q5e6ayScmtpeZyPGgKDswT8"},"metadata":{"from":"FzAaV9Waa1DccDa72qwg13"},"type":"0"},"txnMetadata":{"seqNo":2,"txnId":"5afc282bf9a7a5e3674c09ee48e54d73d129aa86aa226691b042e56ff9eaf59b"},"ver":"1"}
            {"reqSignature":{},"txn":{"data":{"data":{"alias":"xsvalidatorec2irl","blskey":"4ge1yEvjdcV6sDSqbevqPRWq72SgkZqLqfavBXC4LxnYh4QHFpHkrwzMNjpVefvhn1cgejHayXTfTE2Fhpu1grZreUajV36T6sT4BiewAisdEw59mjMxkp9teYDYLQqwPUFPgaGKDbFCUBEaNdAP4E8Q4UFiF13Qo5842pAY13mKC23","blskey_pop":"R5PoEfWvni5BKvy7EbUbwFMQrsgcuzuU1ksxfvySH6FC5jpmisvcHMdVNik6LMvAeSdt6K4sTLrqnaaQCf5aCHkeTcQRgDVR7oFYgyZCkF953m4kSwUM9QHzqWZP89C6GkBx6VPuL1RgPahuBHDJHHiK73xLaEJzzFZtZZxwoWYABH","client_ip":"52.50.114.133","client_port":"9702","node_ip":"52.209.6.196","node_port":"9701","services":["VALIDATOR"]},"dest":"DXn8PUYKZZkq8gC7CZ2PqwECzUs2bpxYiA5TWgoYARa7"},"metadata":{"from":"QuCBjYx4CbGCiMcoqQg1y"},"type":"0"},"txnMetadata":{"seqNo":3,"txnId":"1972fce7af84b7f63b7f0c00495a84425cce3b0c552008576e7996524cca04cb"},"ver":"1"}
            {"reqSignature":{},"txn":{"data":{"data":{"alias":"danube","blskey":"3Vt8fxn7xg8n8pR872cvGWNuR7STFzFSPMftX96zF6871wYVTR27aspxGSeEtx9wj8g4D3GdCxHJbQ4FsxQz6TATQswiiZfxAVNjLLUci8WSH4t1GPx9CvGXB2uzDfVnnJyhhnASxJEbvykLUBBFG3fW4tMQixujpowUADz5jHm427u","blskey_pop":"RJpXXLkjRRv9Lk8tJz8LTkhhC7RWjHQcB9CG8J8U8fXT6arTDMYc62zXtToBAmGkGu8Udsmo3Hh7mv4KB9JAf8ufGY9WsnppCVwar7zEXyBfLpCnDhvVcBAzkhRpHmqHygN24DeBu9aH6tw4uXxVJvRRGSbPtxjWa379BmfQWzXHCb","client_ip":"207.180.207.73","client_port":"9702","node_ip":"173.249.14.196","node_port":"9701","services":["VALIDATOR"]},"dest":"52muwfE7EjTGDKxiQCYWr58D8BcrgyKVjhHgRQdaLiMw"},"metadata":{"from":"VbPQNHsvoLZdaNU7fTBeFx"},"type":"0"},"txnMetadata":{"seqNo":4,"txnId":"ebf340b317c044d970fcd0ca018d8903726fa70c8d8854752cd65e29d443686c"},"ver":"1"}
        """.trimIndent()
    }


}