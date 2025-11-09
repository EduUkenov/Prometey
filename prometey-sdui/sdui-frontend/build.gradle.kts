import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.kotlinSerialization)
	alias(libs.plugins.composeCompiler)
	alias(libs.plugins.composeMultiplatform)
}

kotlin {
	compilerOptions {
		freeCompilerArgs.add("-Xcontext-parameters")
	}
	
	androidTarget {
		@OptIn(ExperimentalKotlinGradlePluginApi::class)
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_11)
		}
	}
	
	jvm()
	
	/*listOf(
		iosX64(),
		iosArm64(),
		iosSimulatorArm64()
	).forEach { iosTarget ->
		iosTarget.binaries.framework {
			baseName = "ComposeApp"
			isStatic = true
		}
	}*/
	
	sourceSets {
		commonMain.dependencies {
			implementation(libs.androidx.collection)
			implementation(libs.jetbrains.collections.immutable)

			implementation(libs.jetbrains.compose.runtime)
			implementation(libs.jetbrains.compose.foundation)
			implementation(libs.jetbrains.compose.ui)
			implementation(libs.jetbrains.compose.material3)
			implementation(libs.jetbrains.compose.ui.tooling.preview)
			implementation(libs.kotlinxSerializationJson)

			implementation(projects.prometeyRcc.rcc)
			implementation(projects.prometeyDescription.description)
			implementation(projects.prometeyDescription.descriptionAnnotation)
		}
	}
}

android {
	namespace = "org.prometey.project"
	compileSdk = libs.versions.android.compileSdk.get().toInt()
	
	/*defaultConfig {
		applicationId = "org.prometey.project"
		minSdk = libs.versions.android.minSdk.get().toInt()
		targetSdk = libs.versions.android.targetSdk.get().toInt()
		versionCode = 1
		versionName = "1.0"
	}*/
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
}

dependencies {
    debugImplementation(compose.uiTooling)
}

