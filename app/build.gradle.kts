@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.myfirstapp"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.myfirstapp"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0-${System.getenv("VERSION_SHA")}"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.9.0")
    implementation ("org.postgresql:postgresql:42.7.0")
    implementation ("io.github.jan-tennert.supabase:supabase-kt:1.0.0")
    implementation ("io.github.jan-tennert.supabase:storage-kt:1.0.0")
    implementation ("io.ktor:ktor-client-android:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(files("libs\\storage-java-1.0.1.jar"))


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}