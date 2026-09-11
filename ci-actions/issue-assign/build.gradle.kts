plugins {
    id("ratio.script")
    application
}

application {
    mainClass = "ratio.automate.issue.MainKt"
}

dependencies {
    implementation(projects.ciActions.base)
}
