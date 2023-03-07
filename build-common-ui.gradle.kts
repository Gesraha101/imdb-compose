apply {
    from("$rootDir/build-common.gradle")
}

dependencies {
    "implementation"(AndroidX.lifeCycleRuntime)
    "implementation"(AndroidX.activityCompose)
    "implementation"(AndroidX.composeMaterial)
    "implementation"(AndroidX.composeNav)
    "implementation"(AndroidX.composeHilt)
    "implementation"(AndroidX.composeUi)
    "implementation"(AndroidX.composeUiPreview)
    "androidTestImplementation"(AndroidX.composeUiTest)
    "debugImplementation"(AndroidX.composeUiTools)
    "debugImplementation"(AndroidX.composeUiManifestTest)
    "implementation"(AndroidX.composeConstraintLayout)
    "implementation"(AndroidX.lifeCycleRuntimeExtensions)
    "implementation"(AndroidX.lifecycleViewModelCompose)

    "implementation"(Remote.retrofit)
    "implementation"(ThirdParty.coil)
}