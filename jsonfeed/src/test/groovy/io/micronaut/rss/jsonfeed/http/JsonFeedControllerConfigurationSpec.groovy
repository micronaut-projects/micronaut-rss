package io.micronaut.rss.jsonfeed.http

class JsonFeedControllerConfigurationSpec extends ApplicationContextSpecification {

    void "path defaults to /json"() {
        expect:
        applicationContext.getBean(JsonFeedControllerConfiguration).path == '/json'
    }

    void "rootpath defaults to /feeds"() {
        expect:
        applicationContext.getBean(JsonFeedControllerConfiguration).rootPath == '/feeds'
    }
}
