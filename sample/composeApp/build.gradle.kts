import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.androidApplication)
	//id("rcc.ast.gradle.plugin") version "0.0.1"
//	id("kotlinmeta.gradle.plugin") version "0.0.1"
	alias(libs.plugins.composeMultiplatform)
	alias(libs.plugins.composeCompiler)
}

kotlin {
	compilerOptions {
		freeCompilerArgs.add("-Xcontext-parameters")
	}
	
	androidTarget {
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_11)
		}
	}
	
	/*listOf(
		iosArm64(),
		iosSimulatorArm64()
	).forEach { iosTarget ->
		iosTarget.binaries.framework {
			baseName = "ComposeApp"
			isStatic = true
		}
	}*/
	
	sourceSets {
		androidMain.dependencies {
			implementation(compose.preview)
			implementation(libs.androidx.activity.compose)
		}
		commonMain.dependencies {
			implementation(libs.ktor.client.core)
			implementation(libs.ktor.client.okhttp)

			implementation(projects.prometeyDescription.description)
			implementation(projects.prometeyDescription.descriptionAnnotation)

			implementation(projects.prometeyRcc.rcc)
			implementation(projects.prometeySdui.sduiFrontend)
			implementation(projects.prometeySduiExtension.sduiFoundationLayout)

			implementation(compose.runtime)
			implementation(compose.foundation)
			implementation(compose.material3)
			implementation(compose.ui)
			implementation(compose.components.resources)
			implementation(compose.components.uiToolingPreview)
			implementation(libs.androidx.lifecycle.viewmodelCompose)
			implementation(libs.androidx.lifecycle.runtimeCompose)
		}
		commonTest.dependencies {
			implementation(libs.kotlin.test)
		}
	}
}

android {
	namespace = "org.prometey.sample.project"
	compileSdk = libs.versions.android.compileSdk.get().toInt()
	
	defaultConfig {
		applicationId = "org.prometey.sample.project"
		minSdk = libs.versions.android.minSdk.get().toInt()
		targetSdk = libs.versions.android.targetSdk.get().toInt()
		versionCode = 1
		versionName = "1.0"
	}
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
