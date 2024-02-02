plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/${Gradles.composeModule}")

android {
    namespace = "com.learndev.onboarding_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.onboardingDomain))
}