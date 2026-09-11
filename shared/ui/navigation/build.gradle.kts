plugins {
    id("ratio.feature")
}

android {
    namespace = "com.ratio.navigation"
}

dependencies {
    implementation(projects.shared.base)
    implementation(projects.shared.domain)
    implementation(projects.shared.ui.core)
}
