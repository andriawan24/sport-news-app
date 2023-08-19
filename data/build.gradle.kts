import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Plugins.LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
}

val apiKey: String = gradleLocalProperties(rootDir).getProperty("API_KEY")

android {
    namespace = AppConfig.DATA_LIBRARY_ID
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

            buildConfigField("String", "apiKey", "\"$apiKey\"")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            isMinifyEnabled = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false

            buildConfigField("String", "apiKey", "\"$apiKey\"")

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
        buildConfig = true
    }
}

dependencies {
    api(project(path = ":domain"))

    // Core Dependencies
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)

    // Dagger Hilt
    implementation(Dependencies.HILT_ANDROID)
    kapt(Dependencies.HILT_COMPILER)

    // Networking
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
    implementation(platform(Dependencies.OKHTTP_BOM))
    implementation(Dependencies.OKHTTP_CLIENT)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)

    // Local
    implementation(Dependencies.ROOM)
    kapt(Dependencies.ROOM_COMPILER)

    // Database Security
    implementation(Dependencies.SQL_CHIPPER)
    implementation(Dependencies.SQLITE_KTX)

    // Testing
    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.JUNIT_EXT)
    androidTestImplementation(TestDependencies.ESPRESSO_CORE)
}