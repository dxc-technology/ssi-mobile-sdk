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
            genesisMode: IndyLedgerConnectorConfiguration.GenesisMode.ip)
        
               let ssiAgentApi = SsiAgentBuilderImpl()
                .withEnvironment(environment: EnvironmentImpl())
                .withConnectionInitiatorController(connectionInitiatorController: cic)
                .withCredReceiverController(credReceiverController: crc)
                .withCredPresenterController(credPresenterController: cpc)
                .withLedgerConnector(ledgerConnector: IndyLedgerConnector(indyLedgerConnectorConfiguration: indyLedgerConnectorConfiguration))
                .build()
        
               ssiAgentApi.doInit()
        /*
               let issuerInvitation = "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IlZlcmlmaWVyIiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiOXRUUVNHUUtHaThUeFBudjhlam5Bd1ZKNHFMb3BFN3dHanFBUDRxekxyNkUiXSwicmVjaXBpZW50S2V5cyI6WyI1dzJ6S1JKeUw5UW4xcFp5cGNuNmFhUTFGa2l4TVBhOUo5QTgyQnBtakhDIl0sIkBpZCI6ImQyMmEzMjcyLWVmOTYtNGI2Zi05YWRhLTJlZjVkOTU0ZDA2MSIsIkB0eXBlIjoiZGlkOnNvdjpCekNic05ZaE1yakhpcVpEVFVBU0hnO3NwZWMvY29ubmVjdGlvbnMvMS4wL2ludml0YXRpb24ifQ=="
               
               ssiAgentApi.connect(url: issuerInvitation)
        
        let verifierInvitation = "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IlZlcmlmaWVyIiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiOXRUUVNHUUtHaThUeFBudjhlam5Bd1ZKNHFMb3BFN3dHanFBUDRxekxyNkUiXSwicmVjaXBpZW50S2V5cyI6WyI1U3NjZGR4NGJNNnl3UmFUTFE4WFdGVnRNUDVoOVpWQkR4WXM3SEdYRXdBRiJdLCJAaWQiOiJhNmU1OWU1ZC04OGY5LTQ4MjItODU1NS1kYWU3ZjU1Y2E5OWYiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="
        
        ssiAgentApi.connect(url: verifierInvitation)
       */
        
      
       ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiQVFxZ05kWnBaRGNIZnJkWGY5cVZoWWNoRGFEWml4bnRWOWdRVmZwTWhmYmQiXSwicmVjaXBpZW50S2V5cyI6WyJCUjlCcnpRbjE3Mm9GRXFtbnN3cVduWmd1QVo2Y0NURTNiTnI3NmF5ZzNodiJdLCJAaWQiOiI2YWRhZGU0Mi05YzEwLTRmNDYtOTRmYS00MjQzM2NhMGI1ZjUiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")
        ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiQVFxZ05kWnBaRGNIZnJkWGY5cVZoWWNoRGFEWml4bnRWOWdRVmZwTWhmYmQiXSwicmVjaXBpZW50S2V5cyI6WyJGRndKUXRkVGZTSHJVWEN0RERmcXZCWjdFTVo3VjZzOU5MMWtYRll3QXdZeCJdLCJAaWQiOiI0OGRmNWQ4Zi01NGRiLTQ3YzgtYTMzNS0wN2Y4MmIxZTg5ZTUiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")
        ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiQVFxZ05kWnBaRGNIZnJkWGY5cVZoWWNoRGFEWml4bnRWOWdRVmZwTWhmYmQiXSwicmVjaXBpZW50S2V5cyI6WyJBMUpuZkNoVURocEdTenFnVnhTNlpzTEVRWWVpelpucFBiczluS0U0cWNNYSJdLCJAaWQiOiJhMGVkYWIyZS0yNWI2LTQxNDYtOWRiMS1kYTAxMDNhN2I2OTAiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")
        ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiQVFxZ05kWnBaRGNIZnJkWGY5cVZoWWNoRGFEWml4bnRWOWdRVmZwTWhmYmQiXSwicmVjaXBpZW50S2V5cyI6WyI3Um9pWmlDWm45MkRpMkQ0eU5ZekhGY1BHU2VLQndoalQ1UGh1MkNrejh0dCJdLCJAaWQiOiJjNzkyNTgzMC01ZTljLTRiZTktYWFlZS0wZTNkMWU2NDc2YjUiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")
        ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiQVFxZ05kWnBaRGNIZnJkWGY5cVZoWWNoRGFEWml4bnRWOWdRVmZwTWhmYmQiXSwicmVjaXBpZW50S2V5cyI6WyIyUXdtSlB4QnkxakY5M1RQdDZDeVFyOExSaEtUMnFtWHgxZWhueVlOWm5URiJdLCJAaWQiOiIxODQ3NTk3NS1lNWFlLTRhMTEtYjBmZi0xMDQwNjI0MDM5MmUiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")
        ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiQVFxZ05kWnBaRGNIZnJkWGY5cVZoWWNoRGFEWml4bnRWOWdRVmZwTWhmYmQiXSwicmVjaXBpZW50S2V5cyI6WyI2S21jNTM1aHlVd0NHbVpTVVZ6YWRuTWlkWnhwV2hMTFN5UUhac3lLWGFyViJdLCJAaWQiOiJkYzJmODZmYy00NzdkLTQ2ZDktOTkzZi1lOTg1MGQ5ZGJiODQiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")
        ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiQVFxZ05kWnBaRGNIZnJkWGY5cVZoWWNoRGFEWml4bnRWOWdRVmZwTWhmYmQiXSwicmVjaXBpZW50S2V5cyI6WyI4TnlWOHFIR0dSUnEzUDVXVGpZV25vN1BVWHc3UXJwOHNFR2h3RUNjeXdzZCJdLCJAaWQiOiI4ZmM0ZDVmYS1mMGJjLTRmZjMtYjU2OC1iZGZiYjFmN2ZjYTYiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")
        ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiQVFxZ05kWnBaRGNIZnJkWGY5cVZoWWNoRGFEWml4bnRWOWdRVmZwTWhmYmQiXSwicmVjaXBpZW50S2V5cyI6WyJCSDVGQTM0VnJWQUdYSjl3WlpOdFc2WmNMOVc5RmNQZkdMMVJCZU1EbW1EVSJdLCJAaWQiOiIzMWZkOWJiNi0xZmQ2LTRkMzMtYTMxMi00NDhkOGJkOGVlZTMiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")
        ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiQVFxZ05kWnBaRGNIZnJkWGY5cVZoWWNoRGFEWml4bnRWOWdRVmZwTWhmYmQiXSwicmVjaXBpZW50S2V5cyI6WyIzYXJWaTN0ZUZhTG5leWduVTdRcTFyZXIzc0F4TlFZUXVaTkxRZnhQOXBZcCJdLCJAaWQiOiI3NjE4ZDk4Mi1hMDIwLTQ1N2ItODBkNC02MzdiN2I3NTM4YzMiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")
        ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiQVFxZ05kWnBaRGNIZnJkWGY5cVZoWWNoRGFEWml4bnRWOWdRVmZwTWhmYmQiXSwicmVjaXBpZW50S2V5cyI6WyJIZnNjNVhWUVV3amhubjRzeFNMM1NSMU5HOEpyZ2Z6cE1QN2pkeGV1NENFRSJdLCJAaWQiOiI2ZmU4YmZmOC1lNmJhLTRlZWQtYTMwNi0xNmZhZTU2YjkxNzkiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")
       
       
        
        //Sleeper().sleep(value: 10000)
     /*   ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiQVFxZ05kWnBaRGNIZnJkWGY5cVZoWWNoRGFEWml4bnRWOWdRVmZwTWhmYmQiXSwicmVjaXBpZW50S2V5cyI6WyJDMVFzNXZGdVVLSldZVThnSmJqTFV5cWF4RnRlVlhuRnQ0TEtOYVBLdVVtVCJdLCJAaWQiOiIxNGMwMDU4Zi0yZGMwLTQzNWItODUzZi1lZDcyMWY3MmQ3OWMiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")
*/


               sleep(180)
        ssiAgentApi.shutdown(force: true)
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
