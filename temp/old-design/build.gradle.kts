plugins {
    id("ratio.feature")
}

android {
    namespace = "com.ratio.design"
}

dependencies {
    implementation(projects.shared.base)
    implementation(projects.shared.ui.core)

    implementation(projects.shared.domain)
}