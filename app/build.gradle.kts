plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    // Define the namespace (make sure your AGP version is 7.0.0 or higher)
    namespace = "com.paruluniversity.innoventures_precisionfarming"

    compileSdk = 35

    defaultConfig {
        // Define the application ID and SDK versions
        applicationId = "com.paruluniversity.innoventures_precisionfarming"
        minSdk = 26  // Set to a reasonable level for compatibility
        targetSdk = 35  // Match the compileSdk

        versionCode = 1
        versionName = "1.0"

        // Define the test instrumentation runner
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            // Disable minify for the release build (Proguard/R8)
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        // Set Java version compatibility
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        // Set JVM target for Kotlin
        jvmTarget = "1.8"
    }

    buildFeatures {
        // Enable viewBinding
        viewBinding = true
    }
}

dependencies {
    // Add required dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Firebase RealTime Database

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))

    // Add the dependency for the Realtime Database library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-database")


}

