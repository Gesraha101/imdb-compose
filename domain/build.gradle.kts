apply {
    from("$rootDir/build-common.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
}