package org.prometey.description.gradle.plugin.extensions

import org.gradle.api.file.ConfigurableFileCollection

interface DescriptionExtension {
	val config: ConfigurableFileCollection
}
