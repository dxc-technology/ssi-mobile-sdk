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
            ipAddress: "192.168.0.117",
            genesisMode: IndyLedgerConnectorConfiguration.GenesisMode.ip,
            dirForGeneratedGenesis: NSHomeDirectory())
        
               let ssiAgentApi = SsiAgentBuilderImpl()
                .withConnectionInitiatorController(connectionInitiatorController: cic)
                .withCredReceiverController(credReceiverController: crc)
                .withCredPresenterController(credPresenterController: cpc)
                .withLedgerConnector(ledgerConnector: IndyLedgerConnector(indyLedgerConnectorConfiguration: indyLedgerConnectorConfiguration))
                .build()
        
               ssiAgentApi.doInit()
               let issuerInvitation = "ws://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTc6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIkN2RHBlN2Z4VlFzRDE1QjllTjhMcHJOQ1h0U243emZXWDVOQUxwWDNkVmtLIl0sInJlY2lwaWVudEtleXMiOlsiQUQ3ckF0dnZRVll0aTFlYk5xdVhRNFF5S2pTVE4xdktzYVk2R3RrNUFUTlkiXSwiQGlkIjoiODQzYjYyNjQtMDExNS00ZDYyLTk1ZTQtMzkxNWM2MTZjZmM4IiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"
               
               ssiAgentApi.connect(url: issuerInvitation)
        
        let verifierInvitation = "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IlZlcmlmaWVyIiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiRU1yYkhTUUVGdjlnRGdhZDVMOWZmR3FudG9Cc0J4UHAzWjZFclhZWU55alkiXSwicmVjaXBpZW50S2V5cyI6WyJFYVdZNnZxWTlMY25tNjJiR3U4TVpEaWZQMWh2amVWdVNoNFZLdFpVZ3VkSiJdLCJAaWQiOiJmNWRlMDMyNy1hYTVmLTQ1MjgtYTc3Zi1kYjdhY2Y0OTA4ODciLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="
        
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
    func onCompleted(connection: Connection) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onInvitationReceived(connection: Connection, endpoint: String, invitation: Invitation) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
   
    
    func onRequestSent(connection: Connection, request: ConnectionRequest) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onResponseReceived(connection: Connection, response: ConnectionResponse) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onAbandoned(connection: Connection, problemReport: ProblemReport) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
        
    }
    
}


class CredentialReceiverControllerImpl: CredReceiverController {
    func onCredentialReceived(connection: Connection, credentialContainer: CredentialContainer) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onDone(connection: Connection, credentialContainer: CredentialContainer) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onOfferReceived(connection: Connection, credentialOfferContainer: CredentialOfferContainer) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onRequestSent(connection: Connection, credentialRequestContainer: CredentialRequestContainer) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
}


class CredPresenterControllerImpl: CredPresenterController {
    func onDone(connection: Connection) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onRequestReceived(connection: Connection, presentationRequest: PresentationRequestContainer) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    
}
