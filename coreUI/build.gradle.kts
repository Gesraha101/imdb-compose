apply {
    from("$rootDir/build-common-ui.gradle.kts")
}

dependencies {
    "implementation"(project(Modules.core))
}