enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        mavenLocal()

        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenLocal()
        mavenCentral()
    }
}
rootProject.name = "Prometey"

include("sample")
include("prometey-sdui:sdui-frontend")
include("prometey-sdui-extension:sdui-foundation-layout")

include(
    ":prometey-description:description",
    ":prometey-description:description-annotation",
    ":prometey-description:description-compiler-plugin",
    ":prometey-description:description-gradle-plugin"
)

include(
    ":prometey-ast-tree:ast-tree",
    ":prometey-ast-tree:ast-tree-annotation",
    ":prometey-ast-tree:ast-tree-compiler-plugin",
    ":prometey-ast-tree:ast-tree-gradle-plugin"
)

include(
    "prometey-rcc:rcc",
    "prometey-rcc:rcc-backend",
)

include(
    "prometey-rcc:rcc",
    "prometey-rcc:rcc-backend",
)

include(
    ":sample:composeApp",
    ":sample:sample-project"
)
