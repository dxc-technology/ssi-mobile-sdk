# Overview 
This is initial POC of multiplatform library which works without inntermediate cloud agent and implements did connection protocol
Currently work is in progress and there are a lot of TODOs in the code 

#iOS

For adding iOS dependencies install and configure cocoapods.
See 
https://kotlinlang.org/docs/mobile/add-dependencies.html#ios-dependencies
and 
https://kotlinlang.org/docs/native-cocoapods.html#install-the-cocoapods-dependency-manager-and-plugin

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

# IOS_connection branch:

## Setup libindy example project on Xcode

* Go to libindy-pod directory, to create xcworkspace file run commands:
```console
pod setup
```
```console
pod install
```
Remember to resolve issues, take a look at lindy-sdk documentation.

* It will download pods and prepare project.
* You can run Xcode from there and run tests, almost all should pass.

## For Kotlin Muliplatform application

* Take libs from Pods after build on Xcode and add them to indlylib folder.

* In repo copy file libindy.a is bigger than 100MB (almost 200MB), I wasn't able to push it to github.

* or do from terminal: after Pods folder will be created please add this file ssi-mobile-sdk/libindy-pod/Pods/libindy.a to indylib folder

  

  <img src="screenshot1.png" alt="screenshot1" style="zoom:50%;" />

  

  <img src="screenshot2.png" alt="screenshot2" style="zoom:50%;" />

## To make c-interop manually run this cmd:

```console
cinterop -def indylib.def -o indylib
```

* Inside the indylib folder.

## Description of indylib.def 

A Brief look:
```console
package = com.indylib
headers = indy_core.h
depends = Foundation
linkerOpts =-framework Security
compilerOpts =-std=c99 -I/Users/kkamyczek/ssi-mobile_final/ssi-mobile-sdk/indylib
staticLibraries = libindy.a liblibzmq.a libssl.a libcrypto.a liblibsodium.a
libraryPaths = /Users/kkamyczek/ssi-mobile_final/ssi-mobile-sdk/indylib
```
Remember to setup your directory structure.
```java
ios {  
    compilations.getByName("main") {
        val indylib by cinterops.creating {
            defFile(project.file("../ssi-mobile-sdk/indylib/indylib.def"))
            }
        }
    }
```
and source set implementation:
```java
sourcesets {
    val iosTest by getting{
        dependencies{
            implementation(files("indylib.klib"))
                }
            }
         }
```


# License and Copyright
All the original code is licensed under the Apache 2.0 License. Please find a copy of the license in the repo.

SPDX-FileCopyrightText: Copyright © 2021 Luxoft


.