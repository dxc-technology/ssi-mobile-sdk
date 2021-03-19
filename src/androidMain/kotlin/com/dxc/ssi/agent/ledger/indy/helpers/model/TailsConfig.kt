package com.dxc.ssi.agent.ledger.indy.helpers.model

/**
 * Allows to configure tails file creation and retrieving
 */
data class TailsConfig(val baseDir: String, val uriPattern: String = "")