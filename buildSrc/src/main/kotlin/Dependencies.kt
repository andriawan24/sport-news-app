object AppConfig {
    const val MIN_SDK = 21
    const val TARGET_SDK = 33
    const val VERSION_NAME = "0.0.0.1"
    const val VERSION_CODE = 1
    const val APP_ID = "id.andriawan24.sportnews"
    const val DATA_LIBRARY_ID = "id.andriawan24.data"
    const val DOMAIN_LIBRARY_ID = "id.andriawan24.domain"
    const val BASE_LIBRARY_ID = "id.andriawan24.base"
    const val FEATURES_HOME_APP_ID = "id.andriawan24.home"
    const val FEATURES_DETAIL_APP_ID = "id.andriawan24.detail"
    const val FEATURES_FAVORITE_APP_ID = "id.andriawan24.favorites"
    const val FEATURES_ABOUT_APP_ID = "id.andriawan24.about"
}

object Plugins {
    const val APPLICATION = "com.android.application"
    const val LIBRARY = "com.android.library"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val HILT = "com.google.dagger.hilt.android"
    const val DYNAMIC_FEATURES = "com.android.dynamic-feature"
    const val KOTLIN_PARCELIZE = "kotlin-parcelize"
}

object Dependencies {
    const val CORE_KTX = "androidx.core:core-ktx:1.10.1"
    const val APPCOMPAT = "androidx.appcompat:appcompat:1.6.1"
    const val MATERIAL = "com.google.android.material:material:1.9.0"
    const val CONSTRAINTS_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val VIEW_MODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:1.7.2"

    // Play Core
    const val PLAY_CORE = "com.google.android.play:core:1.10.3"

    // Leak Canary
    const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:2.10"

    // Database Chipper
    const val SQL_CHIPPER = "net.zetetic:android-database-sqlcipher:4.4.0"
    const val SQLITE_KTX = "androidx.sqlite:sqlite-ktx:2.3.1"

    // Timber Logging
    const val TIMBER_LOGGING = "com.jakewharton.timber:timber:5.0.1"

    // Dagger Hilt
    const val HILT_ANDROID = "com.google.dagger:hilt-android:2.46.1"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:2.46.1"

    // Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:2.9.0"

    // Room
    const val ROOM = "androidx.room:room-ktx:2.5.1"
    const val ROOM_COMPILER = "androidx.room:room-compiler:2.5.1"

    // OkHttp
    const val OKHTTP_BOM = "com.squareup.okhttp3:okhttp-bom:4.10.0"
    const val OKHTTP_CLIENT = "com.squareup.okhttp3:okhttp"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor"
}

object TestDependencies {
    const val JUNIT = "junit:junit:4.13.2"
    const val JUNIT_EXT = "androidx.test.ext:junit:1.1.5"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.5.1"
}