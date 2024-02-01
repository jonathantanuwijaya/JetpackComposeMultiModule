plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/${Gradles.compose_module}")

android {
    namespace = "com.learndev.onboarding_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.onboardingDomain))
}