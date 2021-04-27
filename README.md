# Overview 
This is initial POC of multiplatform library which works without inntermediate cloud agent and implements did connection protocol
Currently work is in progress and there are a lot of TODOs in the code 


# Self Sovereign Identity Mobile SDK
A new Mobile SDK to use with Self Sovereign Identity (SSI) applications is available as an open-source project from Luxoft DXC. The SSI Mobile SDK provides libraries and tools to accelerate integration of digital wallet capabilities into existing mobile apps.

With Mobile SDK developers can leverage secure communication protocols and exchange Verifiable Credentials in mobile solutions.
Digital wallets are a key component in the evolving SSI standards and ecosystems. Mobile SDK is based on Hyperledger Indy, stores Verifiable Credentials directly on the device, giving users to full control over how, and with whom, their information is shared.

Building SSI capabilities into your mobile applications helps facilitates managing, compliance, liability, and security, for Identity-based use cases such as:
* Single sign-on across systems without username or passwords
* Proof of qualifications or accreditations such as diplomas
* Legal consent without face-to-face interaction
* In-app payments or asset transfers
The SSI Mobile SDK enables developers to use zero-knowledge proofs empower their users to share personal information while preserving confidentiality and privacy.
By open sourcing this project, under the Apache 2.0 license, Luxoft & DXC encourages standard approaches across internal projects and benefits from the transparency of technical peer-review from the community.

SSI mobile SDK provides features of [Cordentity](https://github.com/hyperledger-labs/cordentity) for mobile Operating Systems.


# iOS usage

## Instruction for kotlin multiplatform library developer to build the library

1. Go to **libindy-pod** library (this folder content is copy-paste from [indy repo](https://github.com/hyperledger/indy-sdk/tree/master/wrappers/ios/libindy-pod). So in future if indy has updates we might need to refresh this dir.
   (libzmq module was changed to libzmq-pw due to issues with libzmq)
2. To create xcworkspace file run commands:
```console
pod setup
pod install
```
It will download pods and prepare project.
TODO: add solution for common issues (like something is not built). For now just search in google when you encounter problems
3. Open the workspace in Xcode (tested with Xcode 12.4 on MacOS BigSur)) and build the project TODO: use commandline tools for building xcode project
4. When project is built copy libraries (libindy.a liblibzmq.a libssl.a libcrypto.a liblibsodium.a) from products folder into indylib folder

TODO: automate those steps
   <img src="screenshot1.png" alt="screenshot1" style="zoom:50%;" />

  <img src="screenshot2.png" alt="screenshot2" style="zoom:50%;" />
5. Execute 
'''console
./gradlew build
'''
and then un-Ignore the SsiAgentApiImplTest from iosX64 module and try running it


## Instruction for kotlin multiplatform library user to use the library in swift app

The current instruction explains how to use swift example app. 
TODO: make some build artifact (podspec, podfile?) so that client user could use as a dependency

1. Example ios app is located in samples/swiftIosApp
2. This app contains Podfile wich would add proper dependencies to ios app
3. Execute ```./gradlew clean build``` from root repo folder. After this build is completed, gradle will automatically make "pod install" in samples/swiftIosApp folder
4. Open samples/swiftIosApp workspace in Xcode. Set *Validate workspace* to true in project build settings
5. Build Xcode project
6. For testing purpose replace "invitationUrl" value in AppDelegate to actual fresh invitation form 
7. Run the app in xcode. Emulator is supposed to be started and on application start the connection will be established with remote agent
8.  Example of swift code to establish connection
```swift
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
               let invitation = "ws://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTc6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIkRtMkhFRWNlWXo4cnJ1QTVMQWh0Y3B0WVFYVmN0N3V2NUVpNUxHTjdoY2h1Il0sInJlY2lwaWVudEtleXMiOlsiNmpDRGk4YW9iS1Z5WllpRkM0YWgxNmtzcDhFYWVKRlpEY3Vwc29mTGdTeWgiXSwiQGlkIjoiMDRlNjNmY2MtNzk2Yy00YTUwLWI4NzEtOTMxZjRiOGJiYzY5IiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9="
               
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


```


## Some documentation references
For adding iOS dependencies install and configure cocoapods.
See
https://kotlinlang.org/docs/mobile/add-dependencies.html#ios-dependencies
and
https://kotlinlang.org/docs/native-cocoapods.html#install-the-cocoapods-dependency-manager-and-plugin


# License and Copyright
All the original code is licensed under the Apache 2.0 License. Please find a copy of the license in the repo.

SPDX-FileCopyrightText: Copyright © 2021 Luxoft


.