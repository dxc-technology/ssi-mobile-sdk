package com.dxc.ssi.agent.didcomm.model.problem

import kotlinx.serialization.Serializable

@Serializable
data class ProblemReportDescription(val en: String, val code: String)
