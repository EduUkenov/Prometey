package org.prometey.description.compiler.plugin.services

import org.jetbrains.kotlin.cli.jvm.config.addJvmClasspathRoot
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices
import java.io.File

class PluginAnnotationsProvider(testServices: TestServices) : EnvironmentConfigurator(testServices) {
	companion object {
		private val prometeyDescriptionDescriptionRuntimeClasspath =
			System.getProperty("prometeyDescriptionDescriptionRuntime.classpath")
				?.split(File.pathSeparator)?.map(::File)
				?: error("Unable to get a valid classpath from 'annotationsRuntime.classpath' property")
	}
	
	override fun configureCompilerConfiguration(
		configuration: CompilerConfiguration,
		module: TestModule,
	) {
		for (file in prometeyDescriptionDescriptionRuntimeClasspath) {
			configuration.addJvmClasspathRoot(file)
		}
		
	}
}