val serializationVersion: String = "1.0.1"
val indyVersion: String = "1.8.2"
val jacksonVersion: String= "2.9.7"
val ktorVersion: String = "1.5.1"
val okhttpVersion: String = "3.5.0"
val kotlinxCourutinesVersion = "1.4.2"
val uuidVersion = "0.2.3"
val junitVersion = "4.13"

plugins {
    val kotlinVersion = "1.4.30"
    kotlin("multiplatform") version kotlinVersion
    id("com.android.library")
    /* TODO: Deal with : The 'kotlin-android-extensions' Gradle plugin is deprecated. Please use this migration guide (https://goo.gle/kotlin-android-extensions-deprecation) to start working with View Binding (https://developer.android.com/topic/libraries/view-binding) and the 'kotlin-parcelize' plugin.*/
    id("kotlin-android-extensions")
    kotlin("plugin.serialization") version kotlinVersion
    kotlin("native.cocoapods") version kotlinVersion
    id("maven-publish")
}

group = "com.dxc"
version = "1.0-SNAPSHOT"

repositories {
    google()
    jcenter()
    mavenCentral()
    maven(url = "https://repo.sovrin.org/repository/maven-releases")
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
    android()
    /*
    iosX64("ios") {
        binaries {
            framework {
                baseName = "library"
            }
        }
    }
    */


    ios()
    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"

        // You can change the name of the produced framework.
        // By default, it is the name of the Gradle project.
        frameworkName = "my_framework"

        pod("AFNetworking") {
            version = "~> 4.0.1"
        }
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
                implementation ("com.benasher44:uuid:$uuidVersion")

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
                implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
                implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("net.java.dev.jna:jna:4.5.1")
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
                implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:$junitVersion")
            }
        }
        val iosMain by getting {
            dependencies {
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
}
//TODO: check if this section is needed at all or it should be moved to JVM and Android packages
dependencies {
    implementation("junit:junit:$junitVersion")
}
