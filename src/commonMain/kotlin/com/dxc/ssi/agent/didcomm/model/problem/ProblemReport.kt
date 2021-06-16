package com.dxc.ssi.agent.didcomm.model.problem

import com.dxc.ssi.agent.didcomm.model.common.Thread
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//TODO: understand purpose of this class and whether it will be common for all types of processes or there should be seoarate ProblemReport fro each process

/*
* {
  "@type"            : "https://didcomm.org/report-problem/1.0/problem-report",
  "@id"              : "an identifier that can be used to discuss this error message",
  "~thread"          : "info about the threading context in which the error occurred (if any)",
  "description"      : { "en": "localized message", "code": "symbolic-name-for-error" },
  "problem_items"    : [ {"<item descrip>": "value"} ],
  "who_retries"      : "enum: you | me | both | none",
  "fix_hint"         : { "en": "localized error-instance-specific hint of how to fix issue"},
  "impact"           : "enum: message | thread | connection",
  "where"            : "enum: you | me | other - enum: cloud | edge | wire | agency | ..",
  "noticed_time"     : "<time>",
  "tracking_uri"     : "",
  "escalation_uri"   : ""
}
* */
@Serializable
data class ProblemReport(
    @SerialName("@type") val type: String = "https://didcomm.org/report-problem/1.0/problem-report",
    @SerialName("@id") val id: String,
    @SerialName("~thread") val thread: Thread? = null,
    val description: ProblemReportDescription,
    @SerialName("problem_items") val problemItems: Map<String, String>? = null,
    @SerialName("who_retries") val whoRetries: String? = null,
    @SerialName("fix_hint") val fixHint: String? = null,
    val impact: String? = null,
    val where: String? = null,
    @SerialName("noticed_time") val noticed_time: String? = null,
    @SerialName("tracking_uri") val trackingUri: String? = null,
    @SerialName("escalation_uri") val escalationUri: String? = null
)
