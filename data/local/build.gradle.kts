apply {
    from("$rootDir/build-common.gradle")
}

dependencies {
    "implementation"(project(Modules.repo))
    "implementation"(Local.datastore)
    "implementation"(Local.datastore_preference)
    "implementation"(Local.room_runtime)
    "implementation"(Local.room_ktx)
    "kapt"(Local.room_compiler)

    "implementation"(Remote.moshi)
    "kapt"(Remote.moshi_kotlin_code_generation)
}