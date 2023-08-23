plugins {
    id(Plugins.LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
    id(Plugins.KOTLIN_PARCELIZE)
}

android {
    namespace = AppConfig.FEATURES_HOME_APP_ID
    compileSdk = AppConfig.TARGET_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isJniDebuggable = true
            isRenderscriptDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            isMinifyEnabled = true
            isJniDebuggable = false
            isRenderscriptDebuggable = false
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

    // Core Dependencies
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.ACTIVITY_KTX)
    implementation(Dependencies.TIMBER_LOGGING)
    implementation(Dependencies.PLAY_CORE)

    // Dagger Hilt
    implementation(Dependencies.HILT_ANDROID)
    kapt(Dependencies.HILT_COMPILER)

    // Testing
    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.JUNIT_EXT)
    androidTestImplementation(TestDependencies.ESPRESSO_CORE)
}