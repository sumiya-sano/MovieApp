// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    val hilt_version = "2.44"

    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.dagger.hilt.android") version "$hilt_version" apply false
}
