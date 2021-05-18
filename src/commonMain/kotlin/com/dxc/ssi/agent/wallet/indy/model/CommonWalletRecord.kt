package com.dxc.ssi.agent.wallet.indy.model

import kotlinx.serialization.Serializable

/*
* {
      "type": "ConnectionRecord",
      "id": "799c37d3-9152-4a93-bbbd-7f4f9626d632",
      "value": "{\"id\":\"799c37d3-9152-4a93-bbbd-7f4f9626d632\",\"state\":\"Complete\",\"invitation\":\"ws://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTc6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIkVNOW1KZ3pVcjdYc2RyNzM2TEVzZk5KMjhIUDJqWW1qRDhUbnRncjJCZDlQIl0sInJlY2lwaWVudEtleXMiOlsiRE1YMXRaN0toUHdRWWRUZ214NENqZHFWQndWOGFpRHlqVDNFTDdFOHkzZnEiXSwiQGlkIjoiMTExZWIzNjktYzg0MC00Y2YyLTliYTItOTEzZmFjNjkwZDI4IiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9\",\"isSelfInitiated\":true,\"peerRecipientKeys\":[\"DMX1tZ7KhPwQYdTgmx4CjdqVBwV8aiDyjT3EL7E8y3fq\"],\"endpoint\":\"ws://192.168.0.117:7000/ws\"}",
      "tags": null
    }
* */
@Serializable
data class CommonWalletRecord(
    val type: WalletRecordType,
    val id: String,
    val value: String,
    val tags: String?
)
