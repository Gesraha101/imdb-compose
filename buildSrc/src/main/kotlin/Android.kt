
object BuildConfig {
    private const val androidBuildToolsVersion = "7.3.1"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"
}

object AndroidX {
    private const val coreKtxVersion = "1.6.0"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

    private const val appCompatVersion = "1.3.0"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    // Compose
    const val composeVersion = "1.4.0"
    private const val composeToolsVersion = "1.3.3"

    private const val lifeCycleVersion = "2.5.1"
    const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifeCycleVersion"

    private const val lifecycleViewModelComposeVersion = "2.5.1"
    const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleViewModelComposeVersion"

    private const val lifeCycleRuntimeExtensionsVersion = "2.6.0-alpha05"
    const val lifeCycleRuntimeExtensions = "androidx.lifecycle:lifecycle-runtime-compose:$lifeCycleRuntimeExtensionsVersion"

    private const val activityComposeVersion = "1.6.1"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

    private const val composeMaterialVersion = "1.3.1"
    const val composeMaterial = "androidx.compose.material:material:$composeMaterialVersion"

    private const val composeNavVersion = "2.5.3"
    const val composeNav = "androidx.navigation:navigation-compose:$composeNavVersion"

    private const val composeHiltVersion = "1.0.0"
    const val composeHilt = "androidx.hilt:hilt-navigation-compose:$composeHiltVersion"

    private const val layoutVersion = "1.0.1"
    const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:$layoutVersion"

    const val composeUi = "androidx.compose.ui:ui:$composeToolsVersion"

    const val composeUiPreview = "androidx.compose.ui:ui-tooling-preview:$composeToolsVersion"

    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:$composeToolsVersion"

    const val composeUiTools = "androidx.compose.ui:ui-tooling:$composeToolsVersion"

    const val composeUiManifestTest = "androidx.compose.ui:ui-test-manifest:$composeToolsVersion"

    const val composeRuntime = "androidx.compose.runtime:runtime:$composeToolsVersion"
}