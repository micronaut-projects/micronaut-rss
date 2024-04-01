package io.micronaut.rss.jsonfeed.http

import io.micronaut.context.ApplicationContext
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class JsonFeedControllerConfigurationSpec extends Specification {
    @Inject
    ApplicationContext applicationContext

    void "path defaults to /json"() {
        expect:
        applicationContext.getBean(JsonFeedControllerConfiguration).path == '/json'
    }

    void "rootpath defaults to /feeds"() {
        expect:
        applicationContext.getBean(JsonFeedControllerConfiguration).rootPath == '/feeds'
    }
}
