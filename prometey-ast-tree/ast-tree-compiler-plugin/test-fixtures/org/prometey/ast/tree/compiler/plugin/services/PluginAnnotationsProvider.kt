package org.prometey.ast.tree.compiler.plugin.services

import org.jetbrains.kotlin.cli.jvm.config.addJvmClasspathRoot
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices
import java.io.File

class PluginAnnotationsProvider(
    testServices: TestServices
) : EnvironmentConfigurator(testServices) {

    companion object {
        private val prometeyAstTreeAnnotationRuntimeClasspath =
            System.getProperty("annotationsRuntime.classpath")
                ?.split(File.pathSeparator)?.map(::File)
                ?: error("Unable to get a valid classpath from 'annotationsRuntime.classpath' property")

        private val prometeyAstTreeRuntimeClasspath =
            System.getProperty("prometeyAstTreeRuntime.classpath")
                ?.split(File.pathSeparator)?.map(::File)
                ?: error("Unable to get a valid classpath from 'prometeyAstTreeRuntime.classpath' property")

        private val jetpackComposeUiClasspath =
            System.getProperty("jetpackComposeUi.classpath")
                ?.split(File.pathSeparator)?.map(::File)
                ?: error("Unable to get a valid classpath from 'jetpackComposeUi.classpath' property")

        private val jetpackComposeFoundation =
            System.getProperty("jetpackComposeFoundation.classpath")
                ?.split(File.pathSeparator)?.map(::File)
                ?: error("Unable to get a valid classpath from 'jetpackComposeFoundation.classpath' property")
    }

    override fun configureCompilerConfiguration(
        configuration: CompilerConfiguration,
        module: TestModule,
    ) {
        for (file in prometeyAstTreeRuntimeClasspath) {
            configuration.addJvmClasspathRoot(file)
        }

        for (file in prometeyAstTreeAnnotationRuntimeClasspath) {
            configuration.addJvmClasspathRoot(file)
        }

        for (file in jetpackComposeUiClasspath) {
            configuration.addJvmClasspathRoot(file)
        }

        for (file in jetpackComposeFoundation) {
            configuration.addJvmClasspathRoot(file)
        }
    }
}