plugins {
    id(Plugins.LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
}

android {
    namespace = AppConfig.FEATURES_FAVORITE_APP_ID
    compileSdk = AppConfig.TARGET_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(path = ":base"))
    implementation(project(path = ":domain"))

    // Core APK
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINTS_LAYOUT)
    implementation(Dependencies.ACTIVITY_KTX)

    // Hilt
    implementation(Dependencies.HILT_ANDROID)
    kapt(Dependencies.HILT_COMPILER)

    // Testing
    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.JUNIT_EXT)
    androidTestImplementation(TestDependencies.ESPRESSO_CORE)
}