package io.micronaut.rss.jsonfeed

import io.micronaut.core.beans.BeanIntrospection

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

class JsonFeedItemSpec extends ApplicationContextSpecification {
    void "JsonFeedItem is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(JsonFeedItem)

        then:
        noExceptionThrown()
    }

    void "dateModified is formatted as RFC 3339"() {
        given:
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2020, 11, 27, 7, 37, 55),
                ZoneId.of("Europe/Madrid"))

        when:
        JsonFeedItem feed = JsonFeedItem.builder()
                .dateModified(zonedDateTime)
                .build()

        then:
        feed.dateModified == '2020-11-27T07:37:55+01:00'
    }

    void "datePublished is formatted as RFC 3339"() {
        given:
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2020, 11, 27, 7, 37, 55),
                ZoneId.of("Europe/Madrid"))

        when:
        JsonFeedItem feed = JsonFeedItem.builder()
                .datePublished(zonedDateTime)
                .build()

        then:
        feed.datePublished == '2020-11-27T07:37:55+01:00'
    }

    void "valid JsonFeedItem does not trigger any constraint exception"() {
        when:
        JsonFeedItem el = validJsonFeedItem()

        then:
        validator.validate(el).isEmpty()
    }

    void "JsonFeedItem::toString() does not throw NPE"() {
        when:
        new JsonFeedItem().toString()

        then:
        noExceptionThrown()
    }

    void "id is required"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.id = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "url is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.url = null

        then:
        validator.validate(el).isEmpty()
    }

    void "externalUrl is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.externalUrl = null

        then:
        validator.validate(el).isEmpty()
    }

    void "title is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.title = null

        then:
        validator.validate(el).isEmpty()
    }

    void "contentHtml is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.contentHtml = null

        then:
        validator.validate(el).isEmpty()
    }

    void "contentText is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.contentText = null

        then:
        validator.validate(el).isEmpty()
    }

    void "summary is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.summary = null

        then:
        validator.validate(el).isEmpty()
    }

    void "image is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.image = null

        then:
        validator.validate(el).isEmpty()
    }

    void "bannerImage is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.bannerImage = null

        then:
        validator.validate(el).isEmpty()
    }

    void "datePublished is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.datePublished = null

        then:
        validator.validate(el).isEmpty()
    }

    void "dateModified is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.dateModified = null

        then:
        validator.validate(el).isEmpty()
    }

    void "authors is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.authors = null

        then:
        validator.validate(el).isEmpty()
    }

    void "tags is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.tags = null

        then:
        validator.validate(el).isEmpty()
    }

    void "language is optional"() {
        given:
        JsonFeedItem el = validJsonFeedItem()

        when:
        el.language = null

        then:
        validator.validate(el).isEmpty()
    }

    void "builder builds object"() {
        when:
        JsonFeedItem item = JsonFeedItem.builder("2347259")
                .contentText("Cats are neat. \n\nhttps://example.org/cats")
                .url("https://example.org/2347259")
                .build()

        then:
        "Cats are neat. \n\nhttps://example.org/cats" == item.contentText
        "https://example.org/2347259" == item.url
        "2347259" == item.id
    }

    void "snake case is used"() {
        given:
        JsonFeed jsonFeed = JsonFeed.builder("https://jsonfeed.org/version/1",
                "Daring Fireball",
                Collections.singletonList(JsonFeedItem.builder("https://daringfireball.net/linked/2020/11/03/letterman-trump")
                .title("David Letterman on Trump and 2020 Election")
                .datePublished("2020-11-03T22:10:47Z")
                .dateModified("2020-11-03T22:10:48Z")
                .url("https://daringfireball.net/linked/2020/11/03/letterman-trump")
                .externalUrl("https://www.vulture.com/article/david-letterman-2020-election-trump-interview.html")
                .author(JsonFeedAuthor.builder().name("John Gruber").build())
                .contentHtml("\n<p>David Letterman, in an interview with Josef Adalian for Vulture:</p>\n\n<blockquote>\n  <p>I believe he will lose it big, and it will be a relief to every\nliving being in this country, whether they realize it now or not.\nIt certainly will be a relief to me and my family, and I think\ngenerally the population. I’m more confident now than I was then,\nand I was pretty confident then. I was wrong. I don’t think I’ll\nbe wrong this time.</p>\n</blockquote>\n\n<p>That “whether they realize it now or not” bit — I could not agree more. From Letterman’s lips to the election gods’ ears.</p>\n\n<p style=\"padding-top: 1.5em;\"><em>Link: <strong><a href=\"https://www.vulture.com/article/david-letterman-2020-election-trump-interview.html\">vulture.com/article/david-letterman-2020-election-trump…</a></strong></em></p>\n")
                .build()))
        .homePageUrl("https://daringfireball.net/")
        .feedUrl("https://daringfireball.net/feeds/json")
        .author(JsonFeedAuthor.builder()
                .url("https://twitter.com/gruber")
                .name("John Gruber")
                .build())
        .icon("https://daringfireball.net/graphics/apple-touch-icon.png")
        .favicon("https://daringfireball.net/graphics/favicon-64.png")
        .build()

        when:
        String json = objectMapper.writeValueAsString(jsonFeed)

        then:
        !json.contains('externalUrl')
        !json.contains('contentHtml')
        !json.contains('datePublished')
        !json.contains('dateModified')
        json.contains('date_modified')
        json.contains('external_url')
        json.contains('content_html')
    }

    static JsonFeedItem validJsonFeedItem() {
        JsonFeedItem.builder("2347259").build()
    }
}
