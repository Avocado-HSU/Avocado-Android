plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.avocado_android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.avocado_android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    dataBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // lifeCycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0 ")

    // ViewModel, LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // okHttp
    implementation(libs.okhttp)
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("com.squareup.okhttp3:okhttp-urlconnection:4.12.0")

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // gson
    implementation(libs.gson)

    // Moshi
    implementation ("com.squareup.moshi:moshi-kotlin:1.15.1")
    implementation ("com.squareup.retrofit2:converter-moshi:2.11.0")

    // dataStore
    implementation(libs.androidx.datastore.preferences)
    implementation ("androidx.datastore:datastore:1.1.1")

    // glide
    implementation(libs.glide)
    annotationProcessor (libs.glide.compiler)

    // core-splash
    implementation(libs.androidx.core.splashscreen)

    // navigation
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)

    // ViewPager2
    implementation(libs.androidx.viewpager2)

    // Coroutines
    implementation(libs.kotlinx.coroutines)
}