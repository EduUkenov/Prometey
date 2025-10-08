plugins {
	kotlin("jvm")
	id("java-gradle-plugin")
	`maven-publish`
}

group = "kotlinmeta.gradle.plugin"
version = "0.0.1"

gradlePlugin {
	plugins {
		create("kotlinmeta") {
			id = "kotlinmeta.gradle.plugin"
			implementationClass = "org.prometey.description.gradle.plugin.DescriptionCompilerGradlePlugin"
		}
	}
}

dependencies {
	implementation(kotlin("gradle-plugin-api"))
}
