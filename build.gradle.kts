plugins {
    id("com.android.application") version "8.1.4" apply false
    kotlin("android") version "1.8.21" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}