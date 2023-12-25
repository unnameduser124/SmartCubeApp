import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val tnoodle_lib = "com.github.thewca:tnoodle-lib:${Versions.tnoodle_lib}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val lifecycle_runtime_ktx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_runtime_ktx}"
    const val activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose}"
    const val compose_bom = "androidx.compose:compose-bom:${Versions.compose_bom}"
    const val compose_ui = "androidx.compose.ui:ui"
    const val compose_ui_graphics = "androidx.compose.ui:ui-graphics"
    const val compose_ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview"
    const val compose_material3 = "androidx.compose.material3:material3:${Versions.compose_material3}"
    const val junit = "junit:junit:${Versions.junit}"
    const val test_ext_junit = "androidx.test.ext:junit:${Versions.test_ext_junit}"
    const val compose_ui_test_junit4 =
        "androidx.compose.ui:ui-test-junit4:${Versions.compose_ui_test_junit4}"
    const val test_espresso_core = "androidx.test.espresso:espresso-core:${Versions.test_espresso_core}"
    const val compose_ui_test_manifest = "androidx.compose.ui:ui-test-manifest"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling"
}

fun DependencyHandler.compose(){
    implementation(Dependencies.activity_compose)
    implementationPlatform(Dependencies.compose_bom)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_ui_graphics)
    implementation(Dependencies.compose_ui_tooling_preview)
    implementation(Dependencies.compose_material3)
    debugImplementation(Dependencies.compose_ui_tooling)
    debugImplementation(Dependencies.compose_ui_test_manifest)
}

fun DependencyHandler.androidTest(){
    androidTestImplementation(Dependencies.test_espresso_core)
    androidTestImplementation(Dependencies.test_ext_junit)
    androidTestImplementation(Dependencies.compose_ui_test_junit4)
    androidTestImplementationPlatform(Dependencies.compose_bom)
}

fun DependencyHandler.test(){
    testImplementation(Dependencies.junit)
}

fun DependencyHandler.tnoodle(){
    implementation(Dependencies.tnoodle_lib)
}

fun DependencyHandler.core(){
    implementation(Dependencies.core_ktx)
    implementation(Dependencies.lifecycle_runtime_ktx)
}

fun DependencyHandler.bluetoothModule(){
    implementation(project(":cube-bluetooth"))
}

fun DependencyHandler.detectionModule(){
    implementation(project(":cube-detection"))
}

fun DependencyHandler.databaseModule(){
    implementation(project(":cube-database"))
}

fun DependencyHandler.cubeModule(){
    implementation(project(":cube-cube"))
}

fun DependencyHandler.uiModule(){
    implementation(project(":cube-UI"))
}

fun DependencyHandler.testModule(){
    implementation(project(":tests"))
}

fun DependencyHandler.appModule(){
    implementation(project(":app"))
}

fun DependencyHandler.globalModule(){
    implementation(project(":cube-global"))
}