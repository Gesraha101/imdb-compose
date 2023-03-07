buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildConfig.androidBuildTools)
        classpath(KotlinPlugins.kotlinGradlePlugin)
        classpath(Hilt.hiltAndroidPlugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}