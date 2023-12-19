@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.myfirstapp"
    compileSdk = 34
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
    buildFeatures {
        viewBinding = true
    }

}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("com.google.android.material:material:1.11.0") // Material Components for Android library

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment:2.7.5")
    implementation("androidx.navigation:navigation-ui:2.7.5")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    implementation ("org.postgresql:postgresql:42.7.0")
    implementation ("io.github.jan-tennert.supabase:supabase-kt:1.0.0")
    implementation ("io.github.jan-tennert.supabase:storage-kt:1.0.0")
    implementation ("io.ktor:ktor-client-android:1.0.0")
    implementation("com.google.code.gson:gson:2.6.2")
    implementation(files("libs\\storage-java-1.0.1.jar"))
    implementation("com.squareup.retrofit2:retrofit:2.6.2")
    implementation("com.squareup.retrofit2:converter-gson:2.1.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.4.1")

//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.8.0-RC")

    implementation ("com.squareup.picasso:picasso:2.71828")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")

    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") }