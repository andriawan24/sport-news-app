pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Football Wiki"
include(":app")
include(":data")
include(":domain")
include(":base")
include(":features:home")
include(":features:detail")
include(":features:about")
include(":features:favorites")
