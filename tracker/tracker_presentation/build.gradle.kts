plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/${Gradles.compose_module}")

android {
    namespace = "com.learndev.tracker_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.trackerDomain))

    implementation(Coil.coilCompose)
}
