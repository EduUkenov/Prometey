plugins {
    alias(libs.plugins.kotlinJvm)
    id("ast.tree.gradle.plugin") version "0.0.1"
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
}

dependencies {
    implementation(projects.prometeyAstTree.astTreeAnnotation)
    implementation(projects.prometeyAstTree.astTree)

    implementation(libs.jetbrains.compose.runtime)
    implementation(libs.jetbrains.compose.foundation)
    implementation(libs.jetbrains.compose.ui)
    implementation(libs.jetbrains.compose.material3)
}