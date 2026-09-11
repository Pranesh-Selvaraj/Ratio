plugins {
    id("ratio.feature")
    id("ratio.room")
}

android {
    namespace = "com.ratio.data.testing"
}

dependencies {
    implementation(projects.shared.data.core)
}
