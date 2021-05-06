//
//  AppDelegate.swift
//  swiftApp
//
//  Created by Krzysztof on 25/04/2021.
//

import UIKit
import ssi_agent

class ConnectionInitiatorControllerImpl: ConnectionInitiatorController
{
    func onCompleted(connection: Connection_) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onInvitationReceived(connection: Connection_, endpoint: String, invitation: Invitation) -> CallbackResult {
    
        return CallbackResult(canProceedFurther: true)
    }
    
    func onRequestSent(connection: Connection_, request: ConnectionRequest) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
    func onResponseReceived(connection: Connection_, response: ConnectionResponse) -> CallbackResult {
        return CallbackResult(canProceedFurther: true)
    }
    
}

@main
class AppDelegate: UIResponder, UIApplicationDelegate {



    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        let cic = ConnectionInitiatorControllerImpl()
               let ssiAgentApi = SsiAgentBuilderImpl().withConnectionInitiatorController(connectionInitiatorController: cic).build()
               ssiAgentApi.doInit()
               let invitation =
                "ws://192.168.0.104:9000/ws?c_i=eyJsYWJlbCI6IlZlcmlmaWVyIiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjEwNDo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiRDJFVVVvNkJlYUIyWkZQSFpDdjdad2Rjc2RCNG5jY2lVOFl3eE5Ralh3d20iXSwicmVjaXBpZW50S2V5cyI6WyJEaHZnWmhNbWRWYk1xWVY5c0tjNDI5cHA5U2NWWUpqdGptSjNLMnBaRDR4ZCJdLCJAaWQiOiJkOTc1ZjRkZC01ZjdiLTRmM2UtODg2NC0xMTYyODI1NGNmZmMiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="
               
               ssiAgentApi.connect(url: invitation)
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

