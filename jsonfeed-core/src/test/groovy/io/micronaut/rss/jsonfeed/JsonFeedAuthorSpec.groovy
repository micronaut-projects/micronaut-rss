package io.micronaut.rss.jsonfeed

import groovy.json.JsonSlurper
import io.micronaut.core.beans.BeanIntrospection

class JsonFeedAuthorSpec extends ApplicationContextSpecification {
    void "JsonFeedAuthor is annotated with Introspected"() {
        when:
        BeanIntrospection<JsonFeedAuthor> introspection = BeanIntrospection.getIntrospection(JsonFeedAuthor)

        then:
        noExceptionThrown()
        !introspection.propertyNames.contains("empty")
    }

    void "valid JsonFeedAuthor does not trigger any constraint exception"() {
        when:
        JsonFeedAuthor el = validJsonFeedAuthor()

        then:
        validator.validate(el).isEmpty()
    }

    void "JsonFeedAuthor::toString() does not throw NPE"() {
        when:
        new JsonFeedAuthor().toString()

        then:
        noExceptionThrown()
    }

    void "name is optional"() {
        given:
        JsonFeedAuthor el = validJsonFeedAuthor()

        when:
        el.name = null

        then:
        validator.validate(el).isEmpty()
    }

    void "url is optional"() {
        given:
        JsonFeedAuthor el = validJsonFeedAuthor()

        when:
        el.url = null

        then:
        validator.validate(el).isEmpty()
    }

    void "builder builds object"() {
        when:
        JsonFeedAuthor author = JsonFeedAuthor.builder()
                .name("Sergio del Amo")
                .url("https://sergiodelamo.com")
                .avatar("https://sergiodelamo.com/images/sergiodelamo.png")
                .build()

        then:
        "Sergio del Amo" == author.name
        "https://sergiodelamo.com" == author.url
        "https://sergiodelamo.com/images/sergiodelamo.png" == author.avatar
    }

    void "toMap builds a Map for object"() {
        when:
        Map<String, Object> author = JsonFeedAuthor.builder()
                .name("Sergio del Amo")
                .url("https://sergiodelamo.com")
                .avatar("https://sergiodelamo.com/images/sergiodelamo.png")
                .build()
                .toMap()

        then:
        "Sergio del Amo" == author.name
        "https://sergiodelamo.com" == author.url
        "https://sergiodelamo.com/images/sergiodelamo.png" == author.avatar
    }

    void "json serialization does not include empty property"() {
        given:
        JsonFeedAuthor author = JsonFeedAuthor.builder()
                .name("Sergio del Amo")
                .url("https://sergiodelamo.com")
                .avatar("https://sergiodelamo.com/images/sergiodelamo.png")
                .build()

        when:
        String json = objectMapper.writeValueAsString(author)
        def serialized = new JsonSlurper().parseText(json) as Map<String, Object>

        then:
        !serialized.containsKey("empty")
        serialized.name == "Sergio del Amo"
        serialized.url == "https://sergiodelamo.com"
        serialized.avatar == "https://sergiodelamo.com/images/sergiodelamo.png"
    }

    void "avatar is optional"() {
        given:
        JsonFeedAuthor el = validJsonFeedAuthor()

        when:
        el.avatar = null

        then:
        validator.validate(el).isEmpty()
    }
    static JsonFeedAuthor validJsonFeedAuthor() {
        new JsonFeedAuthor()
    }
}
