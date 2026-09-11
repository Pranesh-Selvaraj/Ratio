plugins {
    id("ratio.feature")
}

android {
    namespace = "com.ratio.ui"
}

dependencies {
    implementation(projects.shared.base)
    implementation(projects.shared.domain)
}