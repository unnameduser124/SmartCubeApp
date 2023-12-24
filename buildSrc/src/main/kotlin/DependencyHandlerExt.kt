import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: String){
    add("implementation", dependency)
}

fun DependencyHandler.implementation(projectDependency: Dependency){
    add("implementation", projectDependency)
}

fun DependencyHandler.androidTestImplementation(dependency: String){
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.testImplementation(dependency: String){
    add("testImplementation", dependency)
}

fun DependencyHandler.debugImplementation(dependency: String){
    add("debugImplementation", dependency)
}

fun DependencyHandler.implementationPlatform(dependency: String){
    platform(dependency)
}

fun DependencyHandler.androidTestImplementationPlatform(dependency: String){
    platform(dependency)
}