package com.dxc.ssi.agent.didcomm.constants

import com.dxc.ssi.agent.didcomm.model.problem.ProblemReportDescription


enum class DidCommProblemCodes(val description: String) {
    NO_CREDENTIALS_TO_SATISFY_PRESENTATION_REQUEST(description = "There are no credentials in user wallet to satisfy presentation request"),
    USER_REJECTED_PRESENTATION_REQUEST("User has rejected presentation request"),
    INTERNAL_AGENT_ERROR(description = "Internal agent error happened" ),
    CONNECTION_ABORTED(description = "Connection was broken"),
    COULD_NOT_DELIVER_MESSAGE(description = "Could not deliver message to peer"),
    COULD_NOT_SEND_CREDENTIAL_REQUEST("Could not send credential request"),
    COULD_NOT_SEND_PRESENTATION("Could not send presentation")
}

fun DidCommProblemCodes.toProblemReportDescription(): ProblemReportDescription {
    return ProblemReportDescription(en = description, code = this.name)

}