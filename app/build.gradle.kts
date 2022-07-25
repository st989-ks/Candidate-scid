plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id ("kotlin-parcelize")
}

android {

    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = Android.appId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/proguard-rules.pro"
            )
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"

        @Suppress("SuspiciousCollectionReassignment")
        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }
    kapt {
        correctErrorTypes = true
        useBuildCache = true
    }
}

dependencies {
    // Core
    implementation(AndroidX.Core.core)

    // Appcompat
    implementation(AndroidX.Appcompat.appcompat)

    //Google
    implementation(Google.Material.material)

    // Lifecycle
    implementation(AndroidX.Lifecycle.livedata)
    implementation(AndroidX.Lifecycle.viewmodel)

    // Activity
    implementation(AndroidX.Activity.activity)

    // Hilt
    implementation(HiltDagger.android)
    kapt(HiltDagger.compiler)

    // Kotlin
    implementation(KotlinX.Coroutines.core)
    implementation(KotlinX.Coroutines.android)

    //Jetpack Paging
    implementation(JetpackPaging.paging)

    // Navigation
    implementation(Navigation.navigationFragment)
    implementation(Navigation.navigationUi)

    //Retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.retrofitConverterGson)
    implementation(Retrofit.retrofitConverterScalars)
    implementation(Retrofit.interceptor)

    // Test
    testImplementation(Test.Junit.junit)
    androidTestImplementation(Test.Espresso.espresso)
    androidTestImplementation(Test.Androidx.junitEtx)
}