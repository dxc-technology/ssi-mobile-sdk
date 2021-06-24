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

    let myWalletName = "newWalletName4"
    let myWalletPassword = "newWalletPassword"
    let myDid = "4PCVFCeZbKXyvgjCedbXDx"

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        let cic = ConnectionInitiatorControllerImpl()
        let crc = CredentialReceiverControllerImpl()
        let cpc = CredPresenterControllerImpl()
        

        EnvironmentUtils().doInitEnvironment(environment: EnvironmentImpl())

        let walletManager = IndyWalletManager.Companion()


        if (!walletManager.isWalletExistsAndOpenable(walletName: myWalletName, walletPassword: myWalletPassword)) {
            print("Recreating wallet")
            walletManager.createWallet(walletName: myWalletName, walletPassword: myWalletPassword, walletCreationStrategy: WalletCreationStrategy.truncateandcreate)}


        if (!walletManager.isDidExistsInWallet(did: myDid, walletName: myWalletName, walletPassword: myWalletPassword)) {
            print("Recreating did")
            let didResult: CreateAndStoreMyDidResult = walletManager.createDid(
                didConfig: DidConfig.init(did: myDid, seed: nil, cryptoType: nil, cid: nil),
                walletName : myWalletName, walletPassword:myWalletPassword)

            print("Got generated didResult: did = \(didResult.getDid()) , verkey = \(didResult.getVerkey())")
            //Store did somewhere in your application to use it afterwards
        }

        let walletHolder = IndyWalletHolder(
            walletName : myWalletName,
            walletPassword :myWalletPassword,
            didConfig : DidConfig.init(did: myDid, seed: nil, cryptoType: nil, cid: nil)
        )

        let indyWalletConnector = IndyWalletConnector().build(walletHolder: walletHolder)


        let indyLedgerConnectorConfiguration = IndyLedgerConnectorConfiguration(
            genesisFilePath: "./docker_pool_transactions_genesis.txt",
            ipAddress: "192.168.0.116",
            genesisMode: IndyLedgerConnectorConfiguration.GenesisMode.ip,
            generatedGenesysFileName: "genesis.txn",
            retryTimes: 5,
            retryDelayMs: 5000)
        
        let ssiAgentApi = SsiAgentBuilderImpl(walletConnector: indyWalletConnector)
                .withConnectionInitiatorController(connectionInitiatorController: cic)
                .withCredReceiverController(credReceiverController: crc)
                .withCredPresenterController(credPresenterController: cpc)
                .withLedgerConnector(ledgerConnector: IndyLedgerConnector(indyLedgerConnectorConfiguration: indyLedgerConnectorConfiguration))
                .build()
        
               ssiAgentApi.doInit()


       ssiAgentApi.connect(url: "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiR2s4NWZENW1CWGVRc0dlcVpVV0NuUllnTmZ1M1AzdnVTRHQ5N1RHcEduVmsiXSwicmVjaXBpZW50S2V5cyI6WyJBOVNKZ0szakRtYWM2RE43ekp3UXJLSnBhWUtCMzJzcTROQUZGcGJITXlXRCJdLCJAaWQiOiJkNjI4ODU4Ni1kYjEzLTQyY2ItYmZlNy01MDY1NGRhNWE4ZmQiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0=")


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
    func onCompleted(connection: PeerConnection) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onInvitationReceived(connection: PeerConnection, invitation: Invitation) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
   
    
    func onRequestSent(connection: PeerConnection, request: ConnectionRequest) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onResponseReceived(connection: PeerConnection, response: ConnectionResponse) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onAbandoned(connection: PeerConnection, problemReport: ProblemReport) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
        
    }
    
}


class CredentialReceiverControllerImpl: CredReceiverController {
    func onCredentialReceived(connection: PeerConnection, credentialContainer: CredentialContainer) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onDone(connection: PeerConnection, credentialContainer: CredentialContainer) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onOfferReceived(connection: PeerConnection, credentialOfferContainer: CredentialOfferContainer) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onRequestSent(connection: PeerConnection, credentialRequestContainer: CredentialRequestContainer) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
}


class CredPresenterControllerImpl: CredPresenterController {
    func onProblemReportGenerated(connection: PeerConnection, problemReport: ProblemReport) {

    }

    func onDone(connection: PeerConnection) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onRequestReceived(connection: PeerConnection, presentationRequest: PresentationRequestContainer) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    
}
