 plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/${Gradles.composeModule}")

android {
    namespace = "com.learndev.core_ui"
}

dependencies {

}