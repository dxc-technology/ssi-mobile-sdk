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
               let invitation = "ws://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTc6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIkRtMkhFRWNlWXo4cnJ1QTVMQWh0Y3B0WVFYVmN0N3V2NUVpNUxHTjdoY2h1Il0sInJlY2lwaWVudEtleXMiOlsiRWVNOHlRWjJUd3ZwWWVGTEt0NHdod1VIQWhMcDhqTVhqQkVqNWNMb3pDN2MiXSwiQGlkIjoiOTNjOTY1ODctZTYzOC00ZDgwLWJkMDgtNGE5M2I1NDdlZGMxIiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"
               
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

