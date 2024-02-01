plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/${Gradles.baseModule}")

android {
    namespace = "com.learndev.onboarding_domain"
}

dependencies {
    implementation(project(Modules.core))
}