plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion ( 30)
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.dxc.ssi.sample"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        packagingOptions {
            pickFirst("lib/arm64-v8a/libjnidispatch.so")
            pickFirst("lib/armeabi-v7a/libjnidispatch.so")
            pickFirst("lib/x86/libjnidispatch.so")
            exclude("META-INF/AL2.0")
            exclude("META-INF/LGPL2.1")
        }

    }

    buildTypes {
        getByName("release") {
            minifyEnabled ( false)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("com.dxc:kotlin-multiplatform-agent-android:1.0-SNAPSHOT@aar")
    implementation("co.touchlab:stately-isolate:1.1.4-a1")
    implementation ("co.touchlab:stately-iso-collections:1.1.4-a1")
    implementation("org.hyperledger:indy:1.16.0") {
        exclude(group = "net.java.dev.jna", module = "jna")
        exclude(group = "org.slf4j", module = "slf4j-api")
    }
    implementation("net.java.dev.jna:jna:5.8.0@aar")
    implementation("org.slf4j:slf4j-simple:1.7.26")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("io.ktor:ktor-utils:1.5.1")
    implementation("com.squareup.okhttp3:okhttp:3.5.0")



    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.0")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:+")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}