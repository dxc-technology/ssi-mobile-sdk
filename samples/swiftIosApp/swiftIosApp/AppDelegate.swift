//
//  AppDelegate.swift
//  swiftApp
//
//  Created by Krzysztof on 25/04/2021.
//

import UIKit
import ssi_agent


@main
class AppDelegate: UIResponder, UIApplicationDelegate {



    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        let cic = ConnectionInitiatorControllerImpl()
        let crc = CredentialReceiverControllerImpl()
        let cpc = CredPresenterControllerImpl()
        
        let indyLedgerConnectorConfiguration = IndyLedgerConnectorConfiguration(
            genesisFilePath: "./docker_pool_transactions_genesis.txt",
            ipAddress: "192.168.0.116",
            genesisMode: IndyLedgerConnectorConfiguration.GenesisMode.ip)
        
               let ssiAgentApi = SsiAgentBuilderImpl()
                .withEnvironment(environment: EnvironmentImpl())
                .withConnectionInitiatorController(connectionInitiatorController: cic)
                .withCredReceiverController(credReceiverController: crc)
                .withCredPresenterController(credPresenterController: cpc)
                .withLedgerConnector(ledgerConnector: IndyLedgerConnector(indyLedgerConnectorConfiguration: indyLedgerConnectorConfiguration))
                .build()
        
               ssiAgentApi.doInit()
               let issuerInvitation =
                "ws://192.168.0.116:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTY6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIkNVdVpmdzNmdFJWWXRBWU5SNlRUdTZLam00djRGcFhVSjJtcHVWQVdFeDgiXSwicmVjaXBpZW50S2V5cyI6WyJNd2c4R0tUcmFyS0w1UWdBWmIxaXR5TXJ3blBqYlJWMjdqNGlhN050d05EIl0sIkBpZCI6ImM2ODk0Njg0LTg5ZTktNDlmZS05YWEyLTVjMGNmNjQ1NWE2NiIsIkB0eXBlIjoiZGlkOnNvdjpCekNic05ZaE1yakhpcVpEVFVBU0hnO3NwZWMvY29ubmVjdGlvbnMvMS4wL2ludml0YXRpb24ifQ=="
               
               ssiAgentApi.connect(url: issuerInvitation)
        
        let verifierInvitation =
           "ws://192.168.0.116:9000/ws?c_i=eyJsYWJlbCI6IlZlcmlmaWVyIiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNjo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiR1Q1OUo0TWl5N2ZHcEV6ajN1SnpLbjVQbmVUYXNrYkNtSGtSWGQ0ekh1bVIiXSwicmVjaXBpZW50S2V5cyI6WyI4QWJUWGE1Y2pFV3hDcEZSRlZHZ3B2eUcyYUxWbVBHRzhwQnc2d3YzUkpFcSJdLCJAaWQiOiJjYzEyMDg1NS0zN2U2LTQ0ZmQtYWU3Yi01MTIxYTkxNzI3OTQiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="
        
        ssiAgentApi.connect(url: verifierInvitation)
        
               sleep(10000)
        // Override point for customization after application launch.
        return true
    }

    // MARK: UISceneSession Lifecycle

    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        // Called when a new scene session is being created.
        // Use this method to select a configuration to create the new scene with.
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }

    func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
        // Called when the user discards a scene session.
        // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
        // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
    }


}
class ConnectionInitiatorControllerImpl: ConnectionInitiatorController
{
    func onCompleted(connection: SharedConnection) -> CallbackResult {
        print ("ConnectionInitiatorControllerImpl:onCompleted")
        print (connection)
        
        return CallbackResult(canProceedFurther: true)
    }
    
    func onInvitationReceived(connection: SharedConnection, endpoint: String, invitation: Invitation) -> CallbackResult {
        print ("ConnectionInitiatorControllerImpl:onInvitationReceived")
        print (connection)
        print ("ConnectionInitiatorControllerImpl:Invitation:")
        print (invitation)
        return CallbackResult(canProceedFurther: true)
    }
    
   
    
    func onRequestSent(connection: SharedConnection, request: ConnectionRequest) -> CallbackResult {
        print ("ConnectionInitiatorControllerImpl:onRequestSent")
        print (connection)
        print ("ConnectionInitiatorControllerImpl:ConnectionRequest:")
        print (request)
 

        return CallbackResult(canProceedFurther: true)
    }
    
    func onResponseReceived(connection: SharedConnection, response: ConnectionResponse) -> CallbackResult {
        print ("ConnectionInitiatorControllerImpl:onResponseReceived")
        print (connection)
        print ("ConnectionInitiatorControllerImpl:ConnectionResponse:")
        print (response)
 
        return CallbackResult(canProceedFurther: true)
    }
    
    func onAbandoned(connection: SharedConnection, problemReport: ProblemReport) -> CallbackResult {
        print ("ConnectionInitiatorControllerImpl:onAbandoned")
        print (connection)
        print ("ConnectionInitiatorControllerImpl:ProblemReport:")
        print (problemReport)
        return CallbackResult(canProceedFurther: true)
        
    }
    
}
 
 
class CredentialReceiverControllerImpl: CredReceiverController {
    func onCredentialReceived(connection: SharedConnection, credentialContainer: CredentialContainer) -> CallbackResult {
        print ("CredentialReceiverControllerImpl:onCredentialReceived")
        print (connection)
        print ("CredentialReceiverControllerImpl:credentialContainer:")
        print (credentialContainer)
        return CallbackResult(canProceedFurther: true)
    }
    
    func onDone(connection: SharedConnection, credentialContainer: CredentialContainer) -> CallbackResult {
        print ("CredentialReceiverControllerImpl:onDone")
        print (connection)
        print ("CredentialReceiverControllerImpl:credentialContainer:")
        print (credentialContainer)
        return CallbackResult(canProceedFurther: true)
    }
    
    func onOfferReceived(connection: SharedConnection, credentialOfferContainer: CredentialOfferContainer) -> CallbackResult {
        print ("CredentialReceiverControllerImpl:onOfferReceived")
        print (connection)
        print ("CredentialReceiverControllerImpl:credentialOfferContainer:")
        print (credentialOfferContainer)
        return CallbackResult(canProceedFurther: true)
    }
    
    func onRequestSent(connection: SharedConnection, credentialRequestContainer: CredentialRequestContainer) -> CallbackResult {
        print ("CredentialReceiverControllerImpl:onRequestSent")
        print (connection)
        print ("CredentialReceiverControllerImpl:credentialRequestContainer:")
        print (credentialRequestContainer)
        return CallbackResult(canProceedFurther: true)
    }
    
}
 
 
class CredPresenterControllerImpl: CredPresenterController {
    func onDone(connection: SharedConnection) -> CallbackResult {
        print ("CredPresenterControllerImpl:onDone")
        print (connection)
        return CallbackResult(canProceedFurther: true)
    }
    
    func onRequestReceived(connection: SharedConnection, presentationRequest: PresentationRequestContainer) -> CallbackResult {
        print ("CredPresenterControllerImpl:onRequestReceived")
        print (connection)
        print ("CredPresenterControllerImpl:PresentationRequestContainer:")
        print (presentationRequest)
        return CallbackResult(canProceedFurther: true)
    }
}
