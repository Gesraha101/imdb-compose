apply {
    from("$rootDir/build-common.gradle")
}

plugins { id("com.android.library") }

android {
    buildTypes {
        all {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "IMDB_API_KEY", "\"180fbf6feae4b58eb16832712a69dfe8\"")
        }
    }
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.repo))

    "implementation"(Remote.retrofit)
    "implementation"(Remote.retrofit_interceptor)
    "implementation"(Remote.moshi)
    "implementation"(Remote.moshi_converter)
    "kapt"(Remote.moshi_kotlin_code_generation)
}