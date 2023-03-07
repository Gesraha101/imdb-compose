apply {
    from("$rootDir/build-common.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.domain))

    "implementation"(Remote.moshi)
    "kapt"(Remote.moshi_kotlin_code_generation)
}