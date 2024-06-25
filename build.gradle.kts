// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id(libs.plugins.hilt.get().pluginId) version libs.versions.hilt apply false
    id(libs.plugins.safeargs.plugin.get().pluginId) version libs.versions.jetpack.navigation apply false
}