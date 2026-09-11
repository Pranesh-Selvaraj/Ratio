plugins {
    id("ratio.script")
    application
}

application {
    mainClass = "ratio.automate.compose.stability.MainKt"
}

dependencies {
    implementation(projects.ciActions.base)
}
