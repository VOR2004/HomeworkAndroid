plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
}

android {
    namespace = "ru.ucheba.hw1"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.ucheba.hw1"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }

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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

dependencies {
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.jetbrains.kotlinx.serialization.json)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.glide)
    implementation(libs.androidx.security.crypto)
    implementation(libs.coil.compose)
    implementation(libs.androidx.material3)
    implementation(libs.github.viewbindingpropertydelegate.noreflection)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui.android)
    testImplementation(libs.junit)
    ksp(libs.androidx.room.compiler)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}