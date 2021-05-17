# Overview

This is initial POC of multiplatform library which works without inntermediate cloud agent and implements did connection
protocol Currently work is in progress and there are a lot of TODOs in the code

# Self Sovereign Identity Mobile SDK

A new Mobile SDK to use with Self Sovereign Identity (SSI) applications is available as an open-source project from
Luxoft DXC. The SSI Mobile SDK provides libraries and tools to accelerate integration of digital wallet capabilities
into existing mobile apps.

With Mobile SDK developers can leverage secure communication protocols and exchange Verifiable Credentials in mobile
solutions. Digital wallets are a key component in the evolving SSI standards and ecosystems. Mobile SDK is based on
Hyperledger Indy, stores Verifiable Credentials directly on the device, giving users to full control over how, and with
whom, their information is shared.

Building SSI capabilities into your mobile applications helps facilitates managing, compliance, liability, and security,
for Identity-based use cases such as:

* Single sign-on across systems without username or passwords
* Proof of qualifications or accreditations such as diplomas
* Legal consent without face-to-face interaction
* In-app payments or asset transfers The SSI Mobile SDK enables developers to use zero-knowledge proofs empower their
  users to share personal information while preserving confidentiality and privacy. By open sourcing this project, under
  the Apache 2.0 license, Luxoft & DXC encourages standard approaches across internal projects and benefits from the
  transparency of technical peer-review from the community.

SSI mobile SDK provides features of [Cordentity](https://github.com/hyperledger-labs/cordentity) for mobile Operating
Systems.

# Android usage

Important! Currently we do not have defined artifact location , so in the example below I will use mavenLocal
repository.

## Build parent project and publish android artifact to mavenLocal repo

//TODO: remove this section and have permanent location for an artifact

1. Build artifact and publish it to maven local
   ```
   gradlew :publishAndroidPublicationToMavenLocal
   ```
2. Ensureit appeared there under folder "~/.m2/repository/com/dxc/kotlin-multiplatform-agent-android/1.0-SNAPSHOT"

## Add dependencies to gradle

Add repositories to your gradle build

```kotlin
 repositories {
    mavenLocal()
    google()
    jcenter()
    maven(url = "https://repo.sovrin.org/repository/maven-releases")
}
```

Add packaging options below in android defaultConfig

```kotlin
android {
    ...
    defaultConfig {
        ...
        packagingOptions {
            pickFirst("lib/arm64-v8a/libjnidispatch.so")
            pickFirst("lib/armeabi-v7a/libjnidispatch.so")
            pickFirst("lib/x86/libjnidispatch.so")
            exclude("META-INF/AL2.0")
            exclude("META-INF/LGPL2.1")
        }

    }


```

Add following dependencies

```kotlin
dependencies {
    implementation("com.dxc:kotlin-multiplatform-agent-android:1.0-SNAPSHOT@aar")
    implementation("co.touchlab:stately-isolate:1.1.4-a1")
    implementation("co.touchlab:stately-iso-collections:1.1.4-a1")
    implementation("org.hyperledger:indy:1.16.0") {
        exclude(group = "net.java.dev.jna", module = "jna")
        exclude(group = "org.slf4j", module = "slf4j-api")
    }
    implementation("net.java.dev.jna:jna:5.8.0@aar")
    implementation("org.slf4j:slf4j-simple:1.7.26")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("io.ktor:ktor-utils:1.5.1")
    implementation("com.squareup.okhttp3:okhttp:3.5.0")
}
```

## Add required permissions to android app

Add to AndroidManifest.xml <uses-permission> tags and 
android:usesCleartextTraffic="true",
android:requestLegacyExternalStorage="true"  as below.

```xml

<manifest>
    ...
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <application
    ...
    android:usesCleartextTraffic="true"
    android:requestLegacyExternalStorage="true"
    >
</manifest>
```

android:usesCleartextTraffic="true"

request those permissions in runtime

## Library initialization

```kotlin
val indyLedgerConnectorConfiguration = IndyLedgerConnectorConfiguration(
    genesisMode = IndyLedgerConnectorConfiguration.GenesisMode.IP,
    ipAddress = "192.168.0.117")

ssiAgentApi = SsiAgentBuilderImpl()
    .withEnvironment(EnvironmentImpl(this))
    .withConnectionInitiatorController(ConnectionInitiatorControllerImpl())
    .withCredReceiverController(CredReceiverControllerImpl())
    .withCredPresenterController(CredPresenterControllerImpl())
    .withLedgerConnector(IndyLedgerConnector(indyLedgerConnectorConfiguration))
    .build()

ssiAgentApi.init()

agentInitialized = true
println("Initialized SSI Agent")
```

## Use library for connection

```kotlin
val issuerInvitationUrl =
    "ws://192.168.0.117:7000/ws?c_i=eyJsYWJlbCI6Iklzc3VlciIsImltYWdlVXJsIjpudWxsLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovLzE5Mi4xNjguMC4xMTc6NzAwMC93cyIsInJvdXRpbmdLZXlzIjpbIkdBTGhhOUxIZGs1b1hhRWJab2NDUFhZMnRWaGNnaDZtOE5zdmRCUFp3UjlYIl0sInJlY2lwaWVudEtleXMiOlsiNG14Q3VMSFFmYWg4YXdmMjJld1JLS1dwQTZCUktOQTgzRDVxR1V0UGt4TjUiXSwiQGlkIjoiZDM5OWY2NGMtMGFkNi00MzNiLTk5MTgtZWFiNzAyZGNmNGI0IiwiQHR5cGUiOiJkaWQ6c292OkJ6Q2JzTlloTXJqSGlxWkRUVUFTSGc7c3BlYy9jb25uZWN0aW9ucy8xLjAvaW52aXRhdGlvbiJ9"

val verifierInvitationUrl =
    "ws://192.168.0.117:9000/ws?c_i=eyJsYWJlbCI6IlZlcmlmaWVyIiwiaW1hZ2VVcmwiOm51bGwsInNlcnZpY2VFbmRwb2ludCI6IndzOi8vMTkyLjE2OC4wLjExNzo5MDAwL3dzIiwicm91dGluZ0tleXMiOlsiOWpRS2JneFRqUGRYR05EVURqc05UWWY5N2FXUXpGODZGV3NRdDJwTXdITUsiXSwicmVjaXBpZW50S2V5cyI6WyJHb2hpenlTMUNTV0FyeEdIcDc2Q3hNeXZqZmMxVzdVanhGdTdxeEhKSkp3ciJdLCJAaWQiOiI1MTQwNDgyMi0zYmRlLTRmYTEtOWU2Ny03NTAwZTJiZGJlMzEiLCJAdHlwZSI6ImRpZDpzb3Y6QnpDYnNOWWhNcmpIaXFaRFRVQVNIZztzcGVjL2Nvbm5lY3Rpb25zLzEuMC9pbnZpdGF0aW9uIn0="


ssiAgentApi.connect(issuerInvitationUrl)
ssiAgentApi.connect(verifierInvitationUrl)
```

# iOS usage

## Instruction for kotlin multiplatform library developer to build the library

1. Go to **libindy-pod** library (this folder content is copy-paste
   from [indy repo](https://github.com/hyperledger/indy-sdk/tree/master/wrappers/ios/libindy-pod). So in future if indy
   has updates we might need to refresh this dir.
   (libzmq module was changed to libzmq-pw due to issues with libzmq)
2. To create xcworkspace file run commands:

```console
pod setup
pod install
```

It will download pods and prepare project. TODO: add solution for common issues (like something is not built). For now
just search in google when you encounter problems

3. Open the workspace in Xcode (tested with Xcode 12.4 on MacOS BigSur)) and build the project TODO: use commandline
   tools for building xcode project
4. When project is built copy libraries (libindy.a liblibzmq.a libssl.a libcrypto.a liblibsodium.a) from products folder
   into indylib folder

TODO: automate those steps
<img src="screenshot1.png" alt="screenshot1" style="zoom:50%;" />

  <img src="screenshot2.png" alt="screenshot2" style="zoom:50%;" />
5. Execute 
'''console
./gradlew build
'''
and then un-Ignore the SsiAgentApiImplTest from iosX64 module and try running it

## Instruction for kotlin multiplatform library user to use the library in swift app

The current instruction explains how to use swift example app. TODO: make some build artifact (podspec, podfile?) so
that client user could use as a dependency

1. Example ios app is located in samples/swiftIosApp
2. This app contains Podfile wich would add proper dependencies to ios app
3. Execute ```./gradlew clean build``` from root repo folder. After this build is completed, gradle will automatically
   make "pod install" in samples/swiftIosApp folder
4. Open samples/swiftIosApp workspace in Xcode. Set *Validate workspace* to true in project build settings
5. Build Xcode project
6. For testing purpose replace "invitationUrl" value in AppDelegate to actual fresh invitation form
7. Run the app in xcode. Emulator is supposed to be started and on application start the connection will be established
   with remote agent
8. Example of swift code to establish connection

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

For adding iOS dependencies install and configure cocoapods. See
https://kotlinlang.org/docs/mobile/add-dependencies.html#ios-dependencies
and
https://kotlinlang.org/docs/native-cocoapods.html#install-the-cocoapods-dependency-manager-and-plugin

# License and Copyright

All the original code is licensed under the Apache 2.0 License. Please find a copy of the license in the repo.

SPDX-FileCopyrightText: Copyright © 2021 Luxoft

.