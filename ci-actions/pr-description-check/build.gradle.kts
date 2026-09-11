plugins {
    id("ratio.script")
    application
}

application {
    mainClass = "ratio.automate.pr.MainKt"
}

dependencies {
    implementation(projects.ciActions.base)
}
