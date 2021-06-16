package com.dxc.ssi.agent.didcomm.constants

import com.dxc.ssi.agent.didcomm.model.problem.ProblemReportDescription


enum class DidCommProblemCodes(val description: String) {
    NO_CREDENTIALS_TO_SATISFY_PRESENTATION_REQUEST(description = "There are no credentials in user wallet to satisfy presentation request"),
    USER_REJECTED_PRESENTATION_REQUEST("User has rejected presentation request"),
    INTERNAL_AGENT_ERROR(description = "Internal agent error happened" ),
    CONNECTION_ABORTED(description = "Connection was broken")
}

fun DidCommProblemCodes.toProblemReportDescription(): ProblemReportDescription {
    return ProblemReportDescription(en = description, code = this.name)

}