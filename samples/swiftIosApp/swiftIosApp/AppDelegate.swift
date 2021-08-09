//
//  AppDelegate.swift
//  swiftApp
//
//  Created by Krzysztof on 25/04/2021.
//

import UIKit
import ssi_agent


class Logger{
    static var logger: LogcatLogger = LogcatLogger()
    static func logMessageDebug(message: String, tag: String, throwable: KotlinThrowable?){
        DispatchQueue.main.async {
            logger.log(severity: Severity.debug, message: message, tag: tag, throwable: throwable)
        }
    }
}

var ssiAgentApi: SsiAgentApi? = nil

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window : UIWindow?
    
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
          
        
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

                Logger.logMessageDebug(message: "Before creating wallet", tag: "INIT", throwable: nil)
              
                let indyHomeEnv = getEnvironmentVar( "INDY_HOME")
                
                Logger.logMessageDebug(message: "Env INDY_HOME= \(indyHomeEnv)", tag: "INIT", throwable: nil)
                if (!walletManager.isWalletExistsAndOpenable(walletName: myWalletName, walletPassword: myWalletPassword)) {
                    Logger.logMessageDebug(message: "Recreating wallet", tag: "INIT", throwable: nil)
                    walletManager.createWallet(walletName: myWalletName, walletPassword: myWalletPassword, walletCreationStrategy: WalletCreationStrategy.truncateandcreate)}
                
                Logger.logMessageDebug(message: "Before creating did", tag: "INIT", throwable: nil)
                if (!walletManager.isDidExistsInWallet(did: myDid, walletName: myWalletName, walletPassword: myWalletPassword)) {
                    Logger.logMessageDebug(message: "Recreating did", tag: "INIT", throwable: nil)
                    
                    let didResult: CreateAndStoreMyDidResult = walletManager.createDid(
                        didConfig: DidConfig.init(did: myDid, seed: nil, cryptoType: nil, cid: nil),
                        walletName : myWalletName, walletPassword:myWalletPassword)

                    
                    Logger.logMessageDebug(message: "Got generated didResult: did = \(didResult.getDid()) , verkey = \(didResult.getVerkey())", tag: "INIT", throwable: nil)
            
                    
                    //Store did somewhere in your application to use it afterwards
                }
                
                Logger.logMessageDebug(message: "Before creating wallet holder", tag: "INIT", throwable: nil)
    

                
                let walletHolder = IndyWalletHolder(
                    walletName : myWalletName,
                    walletPassword :myWalletPassword,
                    didConfig : DidConfig.init(did: myDid, seed: nil, cryptoType: nil, cid: nil)
                )
                Logger.logMessageDebug(message: "Before creating wallet connector", tag: "INIT", throwable: nil)
                
                let indyWalletConnector = IndyWalletConnector().build(walletHolder: walletHolder)



            let indyLedgerConnector = IndyLedgerConnectorBuilder()
                .withGenesisMode(genesisMode: GenesisMode.sovrinBuildernet)
                .build()
                
                Logger.logMessageDebug(message: "Before creating ssiAgentApi", tag: "INIT", throwable: nil)
         
                
                ssiAgentApi = SsiAgentBuilderImpl(walletConnector: indyWalletConnector)
                        .withConnectionInitiatorController(connectionInitiatorController: cic)
                        .withCredReceiverController(credReceiverController: crc)
                        .withCredPresenterController(credPresenterController: cpc)
                        .withLedgerConnector(ledgerConnector: indyLedgerConnector)
                        .build()
           
            
                Logger.logMessageDebug(message: "Before initialization", tag: "INIT", throwable: nil)
         
                ssiAgentApi.unsafelyUnwrapped.doInit(libraryStateListener:lsl)
                
                Logger.logMessageDebug(message: "After initialize fun called", tag: "INIT", throwable: nil)
                
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
        Logger.logMessageDebug(message: "Listener: Initialization completed", tag: "INIT", throwable: nil)
        
        let connection = ssiAgentApi.unsafelyUnwrapped.connect(url: "wss://lce-agent-dev.lumedic.io/ws?c_i=eyJsYWJlbCI6IkNsb3VkIEFnZW50IiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzczovL2xjZS1hZ2VudC1kZXYubHVtZWRpYy5pby93cyIsInJvdXRpbmdLZXlzIjpbIjVoUDdreEFDQnpGVXJQSmo0VkhzMTdpRGJ0TU1wclZRSlFTVm84dnZzdGdwIl0sInJlY2lwaWVudEtleXMiOlsiOTRtV0tudTY2Q1JNZTVDcFE4bml6cG1TVW50Tk5UWFZvUVp3R3B1Ylo2emciXSwiQGlkIjoiMjczYzUyMDYtZGFlMi00ZGI0LWFiZGQtNWMwZTc1NmViNjU2IiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9", keepConnectionAlive: true)

        
        Logger.logMessageDebug(message: "Listener: ConnectionStarted", tag: "INIT", throwable: nil)
    
        
    }
    
    func initializationFailed()  {
        
        Logger.logMessageDebug(message: "Listener: Initialization failed", tag: "INIT", throwable: nil)
       
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
            
            Logger.logMessageDebug(message: "Getting parked proof requests from wallet", tag: "INIT", throwable: nil)
            
           
            
            let credInfos = ssiAgentApi.unsafelyUnwrapped.getCredentialInfos().map {$0 as! IndyCredInfo}
            let parketPresentationRequestContainers = ssiAgentApi.unsafelyUnwrapped.getParkedPresentationRequests()
            
            Logger.logMessageDebug(message: "Got \(parketPresentationRequestContainers)", tag: "INIT", throwable: nil)
           
            
            parketPresentationRequestContainers.forEach { presentationRequestContainer in
                ssiAgentApi.unsafelyUnwrapped.processParkedPresentationRequest(presentationRequestContainer: presentationRequestContainer, presentationRequestResponseAction: PresentationRequestResponseAction.accept)
            }
           
            credInfos.forEach { credInfo in
                            
                Logger.logMessageDebug(message: "retrieving first cred", tag: "INIT", throwable: nil)
                let cred = ssiAgentApi.unsafelyUnwrapped.getCredentialInfo(localWalletCredId: credInfo.referent)
                
                Logger.logMessageDebug(message: "\(cred)", tag: "INIT", throwable: nil)
            }
            
        
        }
        
        return PresentationRequestResponseAction.park
    }
    
    
}
