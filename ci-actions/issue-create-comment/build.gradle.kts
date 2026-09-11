plugins {
    id("ratio.script")
    application
}

application {
    mainClass = "ratio.automate.issue.create.MainKt"
}

dependencies {
    implementation(projects.ciActions.base)
}
