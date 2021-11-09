import './App.css';
import {useState} from "react";

let ipAddress = "localhost:8031";

function App() {
    let [invite, setInvite] = useState("");
    let [did, setDid] = useState("");
    let [verkey, setVerkey] = useState("");
    let [schemaId, setSchemaId] = useState("")
    let [credDef, setCredDef] = useState("");
    let [connectionId, setConnectionId] = useState("");
    let [record, setRecord] = useState("");
    let [thread, setThread] = useState("");


    let createInvite = () => {
        console.log("Create invite")
        const requestOptions = {
            method: 'POST',
            headers: {'accept': 'application/json', 'Content-Type': 'application/json'},
            body: "{}"
        };
        return fetch(`http://${ipAddress}/connections/create-invitation?auto_accept=true&multi_use=false`, requestOptions)
            .then(response => response.json())
            .then(data => {
                console.log(data)
                setInvite(`"${data.invitation_url}"`)
            });
    }
    let listDid = () => {
        console.log("List DID")
        const requestOptions = {
            method: 'GET',
            headers: {'accept': 'application/json'},
        };
        return fetch(`http://${ipAddress}/wallet/did`, requestOptions)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                let did = data.results[0].did
                let verkey = data.results[0].verkey

                setDid(did)
                setVerkey(verkey)
                console.log(did);
            });
    }

    let createDid = () => {
        console.log("DID create")
        const requestOptions = {
            method: 'POST',
            headers: {'accept': 'application/json', 'Content-Type': 'application/json'},
            body: JSON.stringify({'method': 'sov', 'options': {'key_type': 'ed25519'}})
        };
        return fetch(`http://${ipAddress}/wallet/did/create`, requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));
    }

    let postDid = () => {
        console.log("Post public DID")
        const requestOptions = {
            method: 'POST',
            headers: {'accept': 'application/json'},
            body: "{}"
        };
        return fetch(`http://${ipAddress}/wallet/did/public?did=${did}`, requestOptions)
            .then(response => response.json())
            .then(data => {
                console.log(data)
            });
    }
    let postSchema = () => {
        console.log("Post schema")
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                "attributes": [
                    "name",
                    "date",
                    "degree",
                    "age"
                ],
                "schema_name": "my-schema",
                "schema_version": "1.0"
            })
        };
        return fetch(`http://${ipAddress}/schemas`, requestOptions)
            .then(response => response.json())
            .then(data => {
                setSchemaId(data.schema_id)
                console.log(data)
            });

    }
    let postCredDef = () => {
        console.log("Post credential definition")
        const requestOptions = {
            method: 'POST',
            headers: {'accept': 'application/json'},
            body: JSON.stringify({
                "schema_id": `${did}:2:my-schema:1.0`,
                "tag": "default"
            })
        };
        return fetch(`http://${ipAddress}/credential-definitions`, requestOptions)
            .then(response => response.json())
            .then(data => {
                console.log(data)
                setCredDef(data.credential_definition_id)
            });

    }

    let issueCredDef = () => {
        console.log("Issue credential definition")
        const requestOptions = {
            method: 'POST',
            headers: {'accept': 'application/json', 'Content-Type': 'application/json'},
            body: JSON.stringify({
                    "auto_issue": true,
                    "auto_remove": true,
                    "comment": "string",
                    "cred_def_id": credDef,
                    "credential_preview": {
                        "@type":
                            "issue-credential/1.0/credential-preview",
                        "attributes": [{"mime-type": "plain/text", "name": "name", "value": "Alice Smith"},
                            {"mime-type": "plain/text", "name": "date", "value": "2020-01-01"},
                            {"mime-type": "plain/text", "name": "degree", "value": "Maths"},
                            {"mime-type": "plain/text", "name": "age", "value": "24"}]
                    }, "trace": true
                }
            )
        };
        return fetch(`http://${ipAddress}/issue-credential/create-offer`, requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));
    }
    let checkConnection = () => {
        console.log("Display connections")
        const requestOptions = {
            method: 'GET',
            headers: {'accept': 'application/json'},
        };
        return fetch(`http://${ipAddress}/connections`, requestOptions)
            .then(response => response.json())
            .then(data => {
                    setConnectionId(data.results[0].connection_id)
                    console.log(data)
                }
            );
    }

    let sendOfferCredDef = () => {
        console.log("Send credentials defintion offer")
        const requestOptions = {
            method: 'POST',
            headers: {'accept': 'application/json', 'Content-Type': 'application/json'},
            body: JSON.stringify({
                "auto_issue": "true",
                "auto_remove": "true",
                "comment": "string",
                "connection_id": connectionId,
                "cred_def_id": credDef,
                "credential_preview": {
                    "@type": "issue-credential/1.0/credential-preview",
                    "attributes": [{
                        "mime-type": "plain/text",
                        "name": "name",
                        "value": "Alice Smith"
                    }, {"mime-type": "plain/text", "name": "date", "value": "2020-01-01"}, {
                        "mime-type": "plain/text",
                        "name": "degree",
                        "value": "Maths"
                    }, {"mime-type": "plain/text", "name": "age", "value": "24"}]
                },
                "trace": true
            })
        };
        return fetch(`http://${ipAddress}/issue-credential/send-offer`, requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));
    }

    let sendReqTRUE = () => {
        console.log("Send proof request valid")
        const requestOptions = {
            method: 'POST',
            headers: {'accept': 'application/json', 'Content-Type': 'application/json'},
            body: JSON.stringify({
                    "comment": "string",
                    "connection_id": connectionId,
                    "proof_request": {
                        "name": "Proof request",
                        "nonce": "1",
                        "requested_attributes": {
                            "0_name_uuid": {
                                "names": ["name"],
                                "restrictions": [{"cred_def_id": credDef}]
                            }
                        },
                        "requested_predicates": {
                            "0_age_GE_uuid": {
                                "name": "age",
                                "p_type": "<=",
                                "p_value": 24,
                                "restrictions": [{"cred_def_id": credDef}]
                            }
                        },
                        "version": "1.0"
                    },
                    "trace": false
                }
            )
        };
        return fetch(`http://${ipAddress}/present-proof/send-request`, requestOptions)
            .then(response => response.json())
            .then(data => console.log(data)
            );
    }

    let sendReqFALSE = () => {
        console.log("Send proof request in-valid")
        const requestOptions = {
            method: 'POST',
            headers: {'accept': 'application/json', 'Content-Type': 'application/json'},
            body: JSON.stringify({
                "comment": "string",
                "connection_id": connectionId,
                "proof_request": {
                    "name": "Proof request",
                    "nonce": "1",
                    "requested_attributes": {
                        "0_name_uuid": {
                            "names": ["name"],
                            "restrictions": [{"cred_def_id": credDef}]
                        }
                    },
                    "requested_predicates": {
                        "0_age_GE_uuid": {
                            "name": "age",
                            "p_type": "<",
                            "p_value": 10,
                            "restrictions": [{"cred_def_id": credDef}]
                        }
                    },
                    "version": "1.0"
                },
                "trace": false
            })
        }
        return fetch(`http://${ipAddress}/present-proof/send-request`, requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));
    }

    let presentProof = () => {
        console.log("Present Proof")
        const requestOptions = {
            method: 'GET',
            headers: {'accept': 'application/json'},
        };
        return fetch(`http://${ipAddress}/present-proof/records?connection_id=${connectionId}`, requestOptions)
            .then(response => response.json())
            .then(data => {
                setRecord(data.results[0].presentation_exchange_id)
                setThread(data.results[0].thread_id)
                console.log(data)
            });
    }


    let sendPresentation = () => {
        console.log("Send presentation")
        const requestOptions = {
            method: 'POST',
            headers: {'accept': 'application/json', 'Content-Type': 'application/json'},
            body: JSON.stringify(
                {
                    "requested_attributes": {
                        "additionalProp1": {
                            "cred_id": thread,
                            "revealed": true
                        },
                        "additionalProp2": {
                            "cred_id": thread,
                            "revealed": true
                        },
                        "additionalProp3": {
                            "cred_id": thread,
                            "revealed": true
                        }
                    },
                    "requested_predicates": {
                        "additionalProp1": {
                            "cred_id": thread,
                            "timestamp": 1640995199
                        },
                        "additionalProp2": {
                            "cred_id": thread,
                            "timestamp": 1640995199
                        },
                        "additionalProp3": {
                            "cred_id": thread,
                            "timestamp": 1640995199
                        }
                    },
                    "self_attested_attributes": {
                        "additionalProp1": "self_attested_value",
                        "additionalProp2": "self_attested_value",
                        "additionalProp3": "self_attested_value"
                    },
                    "trace": false
                })
        }
        return fetch(`http://${ipAddress}/present-proof/records/${record}/send-presentation`, requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));
    }


    let verifyPresentation = () => {
        console.log("Verify presentation")
        const requestOptions = {
            method: 'POST',
            headers: {'accept': 'application/json', 'Content-Type': 'application/json'},
            body: "{}"
        };
        return fetch(`http://${ipAddress}/present-proof/records/${record}/verify-presentation`, requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));
    }


    let allRun1 = async () => {
        console.log("postCredDef")
        try {
            let res = await listDid()
            console.log(res)
            await postDid()
            await checkConnection()
            await postSchema()
            await postCredDef()
        } catch (e) {
            console.log(e)
        }
    }

    let allRun2 = async () => {
        console.log("sendReqTRUE")
        try {
            await issueCredDef()
            await sendOfferCredDef()
            await sendReqTRUE()
        } catch (e) {
            console.log(e)
        }
    }

    let allRun3 = async () => {
        console.log("presentProof")
        try {
            await presentProof()
        } catch (e) {
            console.log(e)
        }
    }


    let allRun4 = async () => {
        console.log("sendPresentation")
        try {
            await sendPresentation()
        } catch (e) {
            console.log(e)
        }
    }


    let allRun5 = async () => {
        console.log("Run")
        try {
            await verifyPresentation()
        } catch (e) {
            console.log(e)
        }
    }
    return (
        <div>
            <div className="App">
                <p>
                    ACA-Py integration app
                </p>
                <p>
                    IP Address: {ipAddress}
                </p>
                <p>
                    Invite:{invite}
                </p>
                <p>
                    DID: {did}
                </p>
                <p>
                    Verkey: {verkey}
                </p>
                <p>
                    SchemaId: {schemaId}
                </p>
                <p>
                    CredDef: {credDef}
                </p>
                <p>
                    ConnectionId: {connectionId}
                </p>
                <p>
                    Exchange Record: {record}
                </p>
            </div>
            <div className="Right">
                <p>
                    <button className="App-button" onClick={createInvite}>Create an invite</button>
                </p>
                <p>
                    <button className="App-button" onClick={listDid}>List DID</button>
                </p>
                <p>
                    <button className="App-button" onClick={createDid}>Create DID</button>
                </p>

                <p>
                    <a href="http://dev.greenlight.bcovrin.vonx.io/" target="_blank">British Columbia Sovrin</a>
                </p>
                <p>
                    Automated steps below:
                </p>
                <p>
                    <button className="App-button" onClick={postDid}>Post DID</button>
                </p>
                <p>
                    <button className="App-button" onClick={postSchema}>Post schema</button>
                </p>
                <p>
                    <button className="App-button" onClick={postCredDef}>Create credential definition</button>
                </p>
                <p>
                    <button className="App-button" onClick={issueCredDef}>Issue credential definition</button>
                </p>
                <p>
                    <button className="App-button" onClick={checkConnection}>Display connections</button>
                </p>
                <p>
                    <button className="App-button" onClick={sendOfferCredDef}>Send an offer with credential definition
                    </button>
                </p>
                <p>
                    <button className="App-button" onClick={sendReqTRUE}>Send request valid</button>
                </p>
                <p>
                    <button className="App-button" onClick={sendReqFALSE}>Send request in-valid</button>
                </p>
                <p>
                    <button className="App-button" onClick={presentProof}>Present proof</button>
                </p>
                <p>
                    <button className="App-button" onClick={verifyPresentation}>Verify presentation</button>
                </p>
                <p>
                    ------------------------------
                </p>
                <p>
                    <button className="App-button" onClick={allRun1}>All run1 - Start</button>
                </p>
                <p>
                    <button className="App-button" onClick={allRun2}>All run2</button>
                </p>
                <p>
                    <button className="App-button" onClick={allRun3}>All run3 - Present proof </button>
                </p>
                <p>
                    <button className="App-button" onClick={allRun4}>All run4 - Send Presentation</button>
                </p>
                <p>
                    <button className="App-button" onClick={allRun5}>All run5 - Verify Presentation</button>
                </p>
            </div>
        </div>
    )
        ;
}

export default App;

