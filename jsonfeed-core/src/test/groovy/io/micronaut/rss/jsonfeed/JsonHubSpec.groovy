package io.micronaut.rss.jsonfeed

import io.micronaut.core.beans.BeanIntrospection

class JsonHubSpec extends ApplicationContextSpecification {
    void "JsonHub is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(JsonHub)

        then:
        noExceptionThrown()
    }

    void "valid JsonHub does not trigger any constraint exception"() {
        when:
        JsonHub el = validJsonHub()

        then:
        validator.validate(el).isEmpty()
    }

    void "JsonHub::toString() does not throw NPE"() {
        when:
        new JsonHub().toString()

        then:
        noExceptionThrown()
    }

    void "builder builds object"() {
        when:
        JsonHub hub = JsonHub.builder("type", "https://foo.bar").build()

        then:
        "type" == hub.type
        "https://foo.bar" == hub.url
    }

    void "type is required"() {
        given:
        JsonHub el = validJsonHub()

        when:
        el.type = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "url is required"() {
        given:
        JsonHub el = validJsonHub()

        when:
        el.url = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "toMap builds a Map for object"() {
        when:
        Map<String, Object> m = JsonHub.builder()
                .type("type")
                .url("https://foo.bar")
                .build()
                .toMap()

        then:
        "type" == m.type
        "https://foo.bar" == m.url
    }

    static JsonHub validJsonHub() {
        JsonHub.builder()
                .type("type")
                .url("https://foo.bar")
                .build()
    }
}
