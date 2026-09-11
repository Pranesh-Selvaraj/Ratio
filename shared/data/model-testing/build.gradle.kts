plugins {
    id("ratio.feature")
}

android {
    namespace = "com.ratio.data.model.testing"
}

dependencies {
    implementation(projects.shared.data.model)

    implementation(libs.bundles.testing)
}