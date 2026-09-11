plugins {
    id("ratio.feature")
    id("ratio.room")
    id("ratio.integration.testing")
}

android {
    namespace = "com.ratio.data"
}

dependencies {
    implementation(projects.shared.base)
    api(projects.shared.data.model)

    api(libs.datastore)
    implementation(libs.bundles.ktor)

    testImplementation(projects.shared.data.modelTesting)
    androidTestImplementation(libs.bundles.integration.testing)
}
