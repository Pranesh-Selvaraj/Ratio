plugins {
    id("ratio.widget")
}

android {
    namespace = "com.ratio.widget"
}

dependencies {
    implementation(projects.shared.base)
    implementation(projects.shared.domain)
}
