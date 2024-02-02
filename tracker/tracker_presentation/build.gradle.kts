plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/${Gradles.composeModule}")

android {
    namespace = "com.learndev.tracker_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.trackerDomain))

    implementation(Coil.coilCompose)
}
