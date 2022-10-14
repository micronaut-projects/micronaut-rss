plugins {
    id("io.micronaut.build.internal.rss-module")
}

dependencies {
    api(mn.micronaut.http)
    api(mn.micronaut.inject)
    api(projects.jsonfeedCore)
    implementation(mn.reactor)
    testAnnotationProcessor(mn.micronaut.inject.java)
    testImplementation(mn.groovy.json)
    testImplementation(mn.micronaut.http.client)
    testImplementation(mn.micronaut.http.server.netty)
    testImplementation(mn.snakeyaml)
}
