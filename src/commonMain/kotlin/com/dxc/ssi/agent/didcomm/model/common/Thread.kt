package com.dxc.ssi.agent.didcomm.model.common

import kotlinx.serialization.Serializable

/*
 "~thread": {
    "thid": "6e7e3663-714b-40b0-a473-3379d8d85d07"
  },
* */
@Serializable
data class Thread(val thid: String)
