//
//  AppDelegate.swift
//  swiftApp
//
//  Created by Krzysztof on 25/04/2021.
//

import UIKit
import ssi_agent
import os.log

var ssiAgentApi: SsiAgentApi? = nil

class OSLogLogger: ssi_agent.KermitLogger {
    override func isLoggable(severity: KermitSeverity) -> Bool {
        OSLog.default.isEnabled(type: getSeverity(severity: severity))
    }
   
    override func log(severity: KermitSeverity, message: String, tag: String, throwable: KotlinThrowable?) {
        os_log("%@", log: OSLog(subsystem: tag ?? "default", category: tag ?? "default"), type: getSeverity(severity: severity), message)
        if let realThrowable = throwable {
            os_log("%@", log: OSLog(subsystem: tag ?? "default", category: tag ?? "default"), type: getSeverity(severity: severity), realThrowable.message ?? realThrowable.description)
        }
    }
   
    private func getSeverity(severity: KermitSeverity) -> OSLogType {
        switch severity {
            case KermitSeverity.verbose: return OSLogType.info
            case KermitSeverity.debug: return OSLogType.debug
            case KermitSeverity.info: return OSLogType.info
            case KermitSeverity.warn: return OSLogType.debug
            case KermitSeverity.error: return OSLogType.error
            case KermitSeverity.assert: return OSLogType.fault
            default: return OSLogType.default
        }
    }
}

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window : UIWindow?
    

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    
        let kermit = Kermit(logger: OSLogLogger())
        kermit.i(withMessage: {"\n"})
        kermit.i(withMessage: {"loaded"})
        
        let cic = ConnectionInitiatorControllerImpl()
        let crc = CredentialReceiverControllerImpl()
        let cpc = CredPresenterControllerImpl()
        let lsl = LibraryStateListenerImpl()

        
    
            let myWalletName = "newWalletName5"
            let myWalletPassword = "newWalletPassword"
            let myDid = "4PCVFCeZbKXyvgjCedbXDx"
        
       // ToBeReworked.init().enableIndyLog()
        
        

        DispatchQueue.global().async {
          
        
            let group = DispatchGroup()
            
            group.enter()
            
            DispatchQueue.main.async {
                EnvironmentUtils().doInitEnvironment(environment:EnvironmentImpl())
                group.leave()
            }
          
           
       
            group.notify(queue: .main) {
                
                let walletManager = IndyWalletManager.Companion()

                print("Before creating wallet")
                
              
                let indyHomeEnv = getEnvironmentVar( "INDY_HOME")
                print("Env INDY_HOME=", indyHomeEnv)
                
                if (!walletManager.isWalletExistsAndOpenable(walletName: myWalletName, walletPassword: myWalletPassword)) {
                    print("Recreating wallet")
                    walletManager.createWallet(walletName: myWalletName, walletPassword: myWalletPassword, walletCreationStrategy: WalletCreationStrategy.truncateandcreate)}

                print("Before creating did")
                
                if (!walletManager.isDidExistsInWallet(did: myDid, walletName: myWalletName, walletPassword: myWalletPassword)) {
                    print("Recreating did")
                    let didResult: CreateAndStoreMyDidResult = walletManager.createDid(
                        didConfig: DidConfig.init(did: myDid, seed: nil, cryptoType: nil, cid: nil),
                        walletName : myWalletName, walletPassword:myWalletPassword)

                    print("Got generated didResult: did = \(didResult.getDid()) , verkey = \(didResult.getVerkey())")
                    //Store did somewhere in your application to use it afterwards
                }

                print("Before creating wallet holder")
                
                

                
                let walletHolder = IndyWalletHolder(
                    walletName : myWalletName,
                    walletPassword :myWalletPassword,
                    didConfig : DidConfig.init(did: myDid, seed: nil, cryptoType: nil, cid: nil)
                )
                print("Before creating wallet connector")
                
                let indyWalletConnector = IndyWalletConnector().build(walletHolder: walletHolder)



            let indyLedgerConnector = IndyLedgerConnectorBuilder()
                .withGenesisMode(genesisMode: GenesisMode.sovrinBuildernet)
                .build()
                
                print("Before creating ssiAgentApi")
                
                ssiAgentApi = SsiAgentBuilderImpl(walletConnector: indyWalletConnector)
                        .withConnectionInitiatorController(connectionInitiatorController: cic)
                        .withCredReceiverController(credReceiverController: crc)
                        .withCredPresenterController(credPresenterController: cpc)
                        .withLedgerConnector(ledgerConnector: indyLedgerConnector)
                        .build()
           
            
        
                print("Before initialization")
                ssiAgentApi.unsafelyUnwrapped.doInit(libraryStateListener:lsl)
                print("After initialize fun called")
                
                
            }
            
           
        }
        
     //   sleep(10000)
      //  return true

        
      
       // sleep(10000)
        return true
        /*
        ssiAgentApi.abandonConnection(connection: connection, force: true, notifyPeerBeforeAbandoning : false)
        ssiAgentApi.shutdown(force: true)
        // Override point for customization after application launch.
    
                    if #available(iOS 13, *) {
                        // do only pure app launch stuff, not interface stuff
                    } else {
                        self.window = UIWindow()
                        let vc = ViewController()
                        self.window!.rootViewController = vc
                        self.window!.makeKeyAndVisible()
                        self.window!.backgroundColor = .red
                    }
                    return true
         */
    }

     
}

func getEnvironmentVar(_ name: String) -> String? {
    guard let rawValue = getenv(name) else { return nil }
    return String(utf8String: rawValue)
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
    
    func onAbandoned(connection: PeerConnection, problemReport: ProblemReport?) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
        
    }
    
}


class LibraryStateListenerImpl : LibraryStateListener {
    func initializationCompleted()  {
       print("Listener: Initialization completed")
        
        let connection = ssiAgentApi.unsafelyUnwrapped.connect(url: "wss://lce-agent-dev.lumedic.io/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzczovL2xjZS1hZ2VudC1kZXYubHVtZWRpYy5pby93cyIsInJvdXRpbmdLZXlzIjpbIjVoUDdreEFDQnpGVXJQSmo0VkhzMTdpRGJ0TU1wclZRSlFTVm84dnZzdGdwIl0sInJlY2lwaWVudEtleXMiOlsiMnVWSE5tWEducmt1TFR2WnNHYXFIRjlGMUU1aEZiMVFLdFF2b3hCakZzb2UiXSwiQGlkIjoiNTZhODZjY2ItNjE4YS00ZDkzLTlmM2EtYzI0MDA1NDgxZjhjIiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9", keepConnectionAlive: true)

        
    }
    
    func initializationFailed()  {
        print("Listener: Initialization failed")
    }
}

class CredentialReceiverControllerImpl: CredReceiverController {
    func onProblemReport(connection: PeerConnection, problemReport: ProblemReport) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onCredentialReceived(connection: PeerConnection, credentialContainer: CredentialContainer) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onDone(connection: PeerConnection, credentialContainer: CredentialContainer) -> CallbackResult {
        
       /*
        DispatchQueue.global().async {
            Sleeper().sleep(value: 5000)
            
            print("Getting credentials from wallet")
            let credInfos = ssiAgentApi.unsafelyUnwrapped.getCredentialInfos().map {$0 as! IndyCredInfo}
            
            print("Got")
            
            print(credInfos)
            
            credInfos.forEach { credInfo in
                print("retrieving first cred")
                let cred = ssiAgentApi.unsafelyUnwrapped.getCredentialInfo(localWalletCredId: credInfo.referent)
                
                print(cred)
            }
            
        
        }
        */
        return CallbackResult(canProceedFurther: true)
    }
    
    func onOfferReceived(connection: PeerConnection, credentialOfferContainer: CredentialOfferContainer) -> OfferResponseAction {
        
       
        
        return OfferResponseAction.accept
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
    
    func onRequestReceived(connection: PeerConnection,
                           presentationRequestContainer: PresentationRequestContainer) -> PresentationRequestResponseAction {
        
        DispatchQueue.global().async {
            Sleeper().sleep(value: 10000)
            
            print("Getting parked proof requests from wallet")
            let credInfos = ssiAgentApi.unsafelyUnwrapped.getCredentialInfos().map {$0 as! IndyCredInfo}
            let parketPresentationRequestContainers = ssiAgentApi.unsafelyUnwrapped.getParkedPresentationRequests()
            
            print("Got", parketPresentationRequestContainers)
            
            parketPresentationRequestContainers.forEach { presentationRequestContainer in
                ssiAgentApi.unsafelyUnwrapped.processParkedPresentationRequest(presentationRequestContainer: presentationRequestContainer, presentationRequestResponseAction: PresentationRequestResponseAction.accept)
            }
           
            credInfos.forEach { credInfo in
                print("retrieving first cred")
                let cred = ssiAgentApi.unsafelyUnwrapped.getCredentialInfo(localWalletCredId: credInfo.referent)
                
                print(cred)
            }
            
        
        }
        
        return PresentationRequestResponseAction.park
    }
    
    
}
