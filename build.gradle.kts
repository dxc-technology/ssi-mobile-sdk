val serializationVersion: String = "1.0.1"
val indyVersion: String = "1.16.0"
val jacksonVersion: String= "2.9.7"
val ktorVersion: String = "1.5.1"
val okhttpVersion: String = "3.5.0"
val kotlinxCourutinesVersion = "1.4.2-native-mt"
val uuidVersion = "0.2.3"
val junitVersion = "4.13"

plugins {
    val kotlinVersion = "1.4.30-M1"
    kotlin("multiplatform") version kotlinVersion
    id("com.android.library")
    /* TODO: Deal with : The 'kotlin-android-extensions' Gradle plugin is deprecated. Please use this migration guide (https://goo.gle/kotlin-android-extensions-deprecation) to start working with View Binding (https://developer.android.com/topic/libraries/view-binding) and the 'kotlin-parcelize' plugin.*/
    id("kotlin-android-extensions")
    kotlin("plugin.serialization") version kotlinVersion
    kotlin("native.cocoapods") version "1.4.31"
    id("maven-publish")
}

group = "com.dxc"
version = "1.0-SNAPSHOT"

publishing {
    repositories {
        mavenLocal()
    }
}
repositories {
    google()
    jcenter()
    mavenCentral()
    maven(url = "https://repo.sovrin.org/repository/maven-releases")
    maven { setUrl("https://dl.bintray.com/kotlin/kotlinx.html/") }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    android {
        publishAllLibraryVariants()
        publishLibraryVariantsGroupedByFlavor = true // This line
    }

    if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true) {
        iosArm64("ios") {
            println("iosArm64")
            compilations.getByName("main") {
                val indylib by cinterops.creating {
                    defFile(project.file("../ssi-mobile-sdk/indylib/indylib.def"))
                    extraOpts("-libraryPath", "$projectDir/indylib")
                    extraOpts("-compiler-options", "-std=c99 -I$projectDir/indylib")
                }
            }
        }
    } else {
        iosX64("ios") {
            println("iosX64")
            compilations.getByName("main") {
                val indylib by cinterops.creating {
                    defFile(project.file("../ssi-mobile-sdk/indylib/indylib.def"))
                    extraOpts("-libraryPath", "$projectDir/indylib")
                    extraOpts("-compiler-options", "-std=c99 -I$projectDir/indylib")
                }
            }
        }
    }

    cocoapods {
        summary = "Kotlin sample project with CocoaPods dependencies"
        homepage = "https://github.com/Kotlin/kotlin-with-cocoapods-sample"
        ios.deploymentTarget = "10.2"
        frameworkName = "ssi_agent"
        podfile = project.file("./samples/swiftIosApp/Podfile")
    }

    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                implementation("io.ktor:ktor-utils:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCourutinesVersion")
                implementation ("co.touchlab:stately-iso-collections:1.1.4-a1")
                //TODO: check if two stately dependencies below are needed, considering that they should be included in the dependency above
                implementation ("co.touchlab:stately-isolate:1.1.4-a1")
                implementation ("co.touchlab:stately-common:1.1.4")
                implementation ("com.benasher44:uuid:$uuidVersion")
                //TODO: check why jdk dependency is added in common module
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("org.hyperledger:indy:$indyVersion") {
                    exclude(group = "net.java.dev.jna", module = "jna")
                    exclude(group = "org.slf4j", module = "slf4j-api")
                }
                implementation("net.java.dev.jna:jna:5.8.0")
                implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
                //TODO: find out a way to get rid of faster xml completely as it is not usable outside of JVM
                implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

                //TODO: this is temporal logging addition. Check idiomatic way to log in multiplatform env
                implementation("org.slf4j:slf4j-api:1.7.30")
                implementation ("org.slf4j:slf4j-log4j12:1.8.0-alpha2")
                implementation ("log4j:log4j:1.2.17")

                //implementation( "com.sun.jna:jna:3.0.9")

                /*
                implementation("ch.qos.logback:logback-classic:1.2.3")
                implementation("ch.qos.logback:logback-core:1.2.3")
                */

            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("org.slf4j:slf4j-simple:1.7.26")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.2.1")
                implementation("org.hyperledger:indy:$indyVersion") {
                    exclude(group = "net.java.dev.jna", module = "jna")
                    exclude(group = "org.slf4j", module = "slf4j-api")
                }
                implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
                implementation("org.slf4j:slf4j-simple:1.7.26")
                implementation("net.java.dev.jna:jna:5.8.0@aar")
                implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("org.slf4j:slf4j-simple:1.7.26")
                implementation("junit:junit:$junitVersion")
                implementation("androidx.test:runner:1.1.0")
                implementation("androidx.test:rules:1.1.0")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(files("indylib.klib"))
            }
        }
        val iosTest by getting {
            dependencies {
                implementation(files("indylib.klib"))
            }
        }
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        //TODO: understand why websockets stop working when changing targetSDKVersion above 27
        targetSdkVersion(27)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //TODO: check which options below are really needed as they were added during development when doing some try and error
        ndk {
            moduleName = "indy"
            abiFilters("x86", "arm64-v8a", "armeabi-v7a")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath = false
            }
        }

        packagingOptions {
            pickFirst("lib/arm64-v8a/libjnidispatch.so")
            pickFirst("lib/armeabi-v7a/libjnidispatch.so")
            pickFirst("lib/x86/libjnidispatch.so")
            exclude("META-INF/AL2.0")
            exclude("META-INF/LGPL2.1")
        }
        //TODO: see if it is needed
        multiDexEnabled = true
    }
}
//TODO: check if this section is needed at all or it should be moved to JVM and Android packages
dependencies {
    implementation("junit:junit:$junitVersion")
}

tasks.register<Copy>("copy") {
    from(layout.buildDirectory.dir("$projectDir/build/classes/kotlin/ios/main/kotlin-multiplatform-agent-cinterop-indylib.klib"))
    into(layout.buildDirectory.dir("$projectDir/samples/swiftIosApp/Pods"))
}
