package io.micronaut.rss.jsonfeed

import groovy.json.JsonSlurper
import io.micronaut.core.beans.BeanIntrospection

class JsonFeedSpec extends ApplicationContextSpecification {
    void "JsonFeed is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(JsonFeed)

        then:
        noExceptionThrown()
    }

    void "valid JsonFeed does not trigger any constraint exception"() {
        when:
        JsonFeed el = validJsonFeed()

        then:
        validator.validate(el).isEmpty()
    }

    void "JsonFeed::toString() does not throw NPE"() {
        when:
        new JsonFeed().toString()

        then:
        noExceptionThrown()
    }

    void "snake case is used for Json serialization"() {
        given:
        JsonFeed el = JsonFeed.builder()
                .title("Brent Simmons’s Microblog")
                .homePageUrl("https://example.org/")
                .feedUrl("https://example.org/feed.json")
                .userComment("Brent Simmons’s Microblog")
                .nextUrl("https://example.org/feed/next.json")
                .build()

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('feedUrl')
        !json.contains('userComment')
        !json.contains('nextUrl')
        !json.contains('homePageUrl')
        json.contains('home_page_url')
        json.contains('feed_url')
        json.contains('user_comment')
        json.contains('next_url')
    }

    void "builder builds object"() {
        when:
        String expected = '''
//tag::json[]
{
    "version": "https://jsonfeed.org/version/1.1",
    "title": "My Example Feed",
    "home_page_url": "https://example.org/",
    "feed_url": "https://example.org/feed.json",
    "items": [
        {
            "id": "2",
            "content_text": "This is a second item.",
            "url": "https://example.org/second-item"
        },
        {
            "id": "1",
            "content_html": "<p>Hello, world!</p>",
            "url": "https://example.org/initial-post"
       }
    ]
}        
//end::json[]
'''
        //tag::builder[]
        JsonFeed feed = JsonFeed.builder()
                .version("https://jsonfeed.org/version/1.1")
                .title("My Example Feed")
                .homePageUrl("https://example.org/")
                .feedUrl("https://example.org/feed.json")
                .item(JsonFeedItem.builder()
                        .id("2")
                        .contentText("This is a second item.")
                        .url("https://example.org/second-item")
                        .build())
                .item(JsonFeedItem.builder()
                        .id("1")
                        .contentHtml("<p>Hello, world!</p>")
                        .url("https://example.org/initial-post")
                        .build())
                .build()
        //end::builder[]
        then:
        "https://jsonfeed.org/version/1.1" == feed.version
        "My Example Feed" == feed.title
        "https://example.org/" == feed.homePageUrl
        "https://example.org/feed.json" == feed.feedUrl
        feed.items
        2 == feed.items.size()
        "2" == feed.items[0].id
        "This is a second item." == feed.items[0].contentText
        "https://example.org/second-item" == feed.items[0].url

        "1" == feed.items[1].id
        "<p>Hello, world!</p>" == feed.items[1].contentHtml
        "https://example.org/initial-post" == feed.items[1].url

        when:
        expected = expected.replaceAll("//end::json\\[]", "").replaceAll("//tag::json\\[]", "")
        def m = new JsonSlurper().parseText(expected)

        then:
        m['version'] == feed.version
        m['title'] == feed.title
        m['home_page_url'] == feed.homePageUrl
        m['feed_url'] ==  feed.feedUrl
        m['items']
        m['items'].size() == feed.items.size()
        m['items'][0].id == feed.items[0].id
        m['items'][0]['content_text'] == feed.items[0].contentText
        m['items'][0]['url'] == feed.items[0].url

        m['items'][1].id == feed.items[1].id
        m['items'][1]['content_text'] == feed.items[1].contentText
        m['items'][1]['url'] == feed.items[1].url
    }

    void "version is required"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.version = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "title is required"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.title = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "homePageUrl is optional"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.homePageUrl = null

        then:
        validator.validate(el).isEmpty()
    }

    void "feedUrl is optional"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.feedUrl = null

        then:
        validator.validate(el).isEmpty()
    }

    void "description is optional"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.description = null

        then:
        validator.validate(el).isEmpty()
    }

    void "userComment is optional"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.userComment = null

        then:
        validator.validate(el).isEmpty()
    }

    void "nextURL is optional"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.nextURL = null

        then:
        validator.validate(el).isEmpty()
    }

    void "icon is optional"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.icon = null

        then:
        validator.validate(el).isEmpty()
    }

    void "favicon is optional"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.favicon = null

        then:
        validator.validate(el).isEmpty()
    }

    void "authors is optional"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.authors = null

        then:
        validator.validate(el).isEmpty()
    }

    void "language is optional"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.language = null

        then:
        validator.validate(el).isEmpty()
    }

    void "expired is optional"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.expired = null

        then:
        validator.validate(el).isEmpty()
    }

    void "hubs is optional"() {
        given:
        JsonFeed el = validJsonFeed()

        when:
        el.hubs = null

        then:
        validator.validate(el).isEmpty()
    }

    static JsonFeed validJsonFeed() {
        JsonFeed.builder("My Example Feed", Collections.singletonList(JsonFeedItem
                .builder("1")
                .contentHtml( "<p>Hello, world!</p>")
                .url("https://example.org/initial-post")
                .build())
        ).build()
    }
}
