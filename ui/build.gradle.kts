apply {
    from("$rootDir/build-common-ui.gradle.kts")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUI))
    "implementation"(project(Modules.domain))
}