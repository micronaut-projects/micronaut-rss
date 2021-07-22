package io.micronaut.rss.jsonfeed

import io.micronaut.core.beans.BeanIntrospection

class JsonFeedAttachmentSpec extends ApplicationContextSpecification {
    void "JsonFeedAttachment is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(JsonFeedAttachment)

        then:
        noExceptionThrown()
    }

    void "valid JsonFeedAttachment does not trigger any constraint exception"() {
        when:
        JsonFeedAttachment el = validJsonFeedAttachment()

        then:
        validator.validate(el).isEmpty()
    }

    void "JsonFeedAttachment::toString() does not throw NPE"() {
        when:
        JsonFeedAttachment.builder("https://micronaut.io", "application/json")
                .build()
                .toString()

        then:
        noExceptionThrown()
    }

    void "mimeType is required"() {
        given:
        JsonFeedAttachment el = validJsonFeedAttachment()

        when:
        el.mimeType = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "url is required"() {
        given:
        JsonFeedAttachment el = validJsonFeedAttachment()

        when:
        el.url = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "title is optional"() {
        given:
        JsonFeedAttachment el = validJsonFeedAttachment()

        when:
        el.title = null

        then:
        validator.validate(el).isEmpty()
    }

    void "sizeInBytes is optional"() {
        given:
        JsonFeedAttachment el = validJsonFeedAttachment()

        when:
        el.sizeInBytes = null

        then:
        validator.validate(el).isEmpty()
    }

    void "durationInSeconds is optional"() {
        given:
        JsonFeedAttachment el = validJsonFeedAttachment()

        when:
        el.durationInSeconds = null

        then:
        validator.validate(el).isEmpty()
    }

    void "snake case is used for Json serialization"() {
        given:
        JsonFeedAttachment el = JsonFeedAttachment.builder("http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a", "audio/x-m4a")
                .sizeInBytes(89970236L)
                .durationInSeconds(6629)
                .build()

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('mimeType')
        !json.contains('sizeInBytes')
        !json.contains('durationInSeconds')
        json.contains('url')
        json.contains('mime_type')
        json.contains('size_in_bytes')
        json.contains('duration_in_seconds')
    }

    void "toMap returns a Map representation"() {
        when:
        Map<String, Object> m = JsonFeedAttachment.builder()
                .url("http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a")
                .mimeType("audio/x-m4a")
                .sizeInBytes(89970236L)
                .durationInSeconds(6629)
                .build()
                .toMap()

        then:
        m.url == "http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a"
        m['mime_type'] == "audio/x-m4a"
        m['size_in_bytes'] == 89970236L
        m['duration_in_seconds'] == 6629
    }

    void "builder builds object"() {
        when:
        JsonFeedAttachment attachment = JsonFeedAttachment.builder("http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a", "audio/x-m4a")
                .sizeInBytes(89970236L)
                .durationInSeconds(6629)
                .build()

        then:
        "http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a" == attachment.url
        "audio/x-m4a" == attachment.mimeType
        89970236 == attachment.sizeInBytes
        6629 == attachment.durationInSeconds
    }

    static JsonFeedAttachment validJsonFeedAttachment() {
        JsonFeedAttachment.builder("http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a", "audio/x-m4a").build()
    }
}
