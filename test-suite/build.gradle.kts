plugins {
    `java-library`
}
repositories {
    mavenCentral()
}
dependencies {
    testImplementation(project(":micronaut-jsonfeed-core"))
    testAnnotationProcessor(mn.micronaut.inject.java)
    testAnnotationProcessor(mnSerde.micronaut.serde.processor)
    testImplementation(mnSerde.micronaut.serde.jackson)
    testImplementation(mnTest.micronaut.test.junit5)
    testRuntimeOnly(mnTest.junit.jupiter.engine)
    testRuntimeOnly(mnLogging.logback.classic)
}
tasks.withType<Test> {
    useJUnitPlatform()
}
