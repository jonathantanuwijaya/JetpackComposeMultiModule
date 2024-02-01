plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/${Gradles.baseModule}")

android {
    namespace = "com.learndev.tracker_domain"
}

dependencies {
    implementation(project(Modules.core))
}
