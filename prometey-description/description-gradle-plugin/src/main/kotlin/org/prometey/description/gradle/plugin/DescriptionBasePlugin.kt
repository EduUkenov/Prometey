package org.prometey.description.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.prometey.description.gradle.plugin.extensions.DescriptionExtension

class DescriptionBasePlugin : Plugin<Project> {
	override fun apply(target: Project) {
		val extension = target.extensions.create("", DescriptionExtension::class.java)
	}
}