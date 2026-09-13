plugins {
    id("ratio.feature")
}

android {
    namespace = "com.ratio.contributors"
}

dependencies {
    implementation(projects.shared.base)
    implementation(projects.shared.domain)
    implementation(projects.shared.ui.core)
    implementation(projects.shared.ui.navigation)

    implementation(libs.bundles.ktor)

}
