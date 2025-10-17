import org.gradle.kotlin.dsl.invoke

plugins {
    kotlin("jvm")
    `java-test-fixtures`
    id("com.github.gmazzo.buildconfig")
    idea
    `maven-publish`
}

group = "ast.tree.compiler.plugin"
version = "0.0.1"

sourceSets {
    testFixtures {
        java.setSrcDirs(listOf("test-fixtures"))
    }
    test {
        java.setSrcDirs(listOf("test", "test-gen"))
        resources.setSrcDirs(listOf("testData"))
    }
}

publishing {
    publications {
        create<MavenPublication>("ast") {
            groupId = "ast.tree.compiler.plugin"
            artifactId = "ast.tree.compiler.plugin"
            version = "0.0.1"

            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}

idea {
    module.generatedSourceDirs.add(projectDir.resolve("test-gen"))
}

val annotationsRuntimeClasspath: Configuration by configurations.creating {
    isTransitive = false
}

val prometeyAstTreeRuntimeClasspath: Configuration by configurations.creating {
    isTransitive = false
}

val jetpackComposeUi: Configuration by configurations.creating {
    isTransitive = true
}

val jetpackComposeFoundation: Configuration by configurations.creating {
    isTransitive = true
}

dependencies {
    compileOnly(libs.kotlin.compiler)

    testFixturesApi(libs.kotlin.test.junit5)
    testFixturesApi(libs.kotlin.compiler.internal.test.framework)
    testFixturesApi(kotlin("compiler"))

    annotationsRuntimeClasspath(project(":prometey-ast-tree:ast-tree-annotation"))
    prometeyAstTreeRuntimeClasspath(project(":prometey-ast-tree:ast-tree"))
    jetpackComposeUi(dependencies.implementation("androidx.compose.ui:ui:1.9.3") {})
    jetpackComposeFoundation(dependencies.implementation("androidx.compose.foundation:foundation:1.9.3") {})

    testRuntimeOnly("junit:junit:4.13.2")
    testRuntimeOnly(kotlin("reflect"))
    testRuntimeOnly(kotlin("test"))
    testRuntimeOnly(kotlin("script-runtime"))
    testRuntimeOnly(kotlin("annotations-jvm"))
    testImplementation(libs.kotlin.compile.testing)
}

tasks.test {
    dependsOn(annotationsRuntimeClasspath)
    dependsOn(prometeyAstTreeRuntimeClasspath)
    dependsOn(jetpackComposeUi)
    dependsOn(jetpackComposeFoundation)

    useJUnitPlatform()
    workingDir = rootDir

    systemProperty("annotationsRuntime.classpath", annotationsRuntimeClasspath.asPath)
    systemProperty("prometeyAstTreeRuntime.classpath", prometeyAstTreeRuntimeClasspath.asPath)
    systemProperty("jetpackComposeUi.classpath", jetpackComposeUi.asPath)
    systemProperty("jetpackComposeFoundation.classpath", jetpackComposeFoundation.asPath)

    // Properties required to run the internal test framework.
    setLibraryProperty("org.jetbrains.kotlin.test.kotlin-stdlib", "kotlin-stdlib")
    setLibraryProperty("org.jetbrains.kotlin.test.kotlin-stdlib-jdk8", "kotlin-stdlib-jdk8")
    setLibraryProperty("org.jetbrains.kotlin.test.kotlin-reflect", "kotlin-reflect")
    setLibraryProperty("org.jetbrains.kotlin.test.kotlin-test", "kotlin-test")
    setLibraryProperty("org.jetbrains.kotlin.test.kotlin-script-runtime", "kotlin-script-runtime")
    setLibraryProperty("org.jetbrains.kotlin.test.kotlin-annotations-jvm", "kotlin-annotations-jvm")

    systemProperty("idea.ignore.disabled.plugins", "true")
    systemProperty("idea.home.path", rootDir)
}

kotlin {
    compilerOptions {
        optIn.add("org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi")
        optIn.add("org.jetbrains.kotlin.ir.symbols.UnsafeDuringIrConstructionAPI")
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

val generateTests by tasks.registering(JavaExec::class) {
    inputs.dir(layout.projectDirectory.dir("testData"))
        .withPropertyName("testData")
        .withPathSensitivity(PathSensitivity.RELATIVE)
    outputs.dir(layout.projectDirectory.dir("test-gen"))
        .withPropertyName("generatedTests")

    classpath = sourceSets.testFixtures.get().runtimeClasspath
    mainClass.set("org.prometey.ast.tree.compiler.plugin.GenerateTestsKt")
    workingDir = rootDir
}

tasks.compileTestKotlin {
    dependsOn(generateTests)
}

fun Test.setLibraryProperty(propName: String, jarName: String) {
    val path = project.configurations
        .testRuntimeClasspath.get()
        .files
        .find { """$jarName-\d.*jar""".toRegex().matches(it.name) }
        ?.absolutePath
        ?: return
    systemProperty(propName, path)
}
