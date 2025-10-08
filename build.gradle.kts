plugins {
	alias(libs.plugins.androidApplication) apply false
	alias(libs.plugins.androidLibrary) apply false
	alias(libs.plugins.composeMultiplatform) apply false
	alias(libs.plugins.composeCompiler) apply false
	alias(libs.plugins.kotlinMultiplatform) apply false
	alias(libs.plugins.kotlinSerialization) apply false
	alias(libs.plugins.kotlinJvm) apply false
//	id("kotlinmeta.gradle.plugin") version "0.0.1" apply false
//	id("rcc.ast.gradle.plugin") version "0.0.1" apply false
    id("com.github.gmazzo.buildconfig") version "5.6.5"
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.16.3" apply false
}

allprojects {
    group = "org.prometey.compiler.plugin"
    version = "0.1.0-SNAPSHOT"
}
