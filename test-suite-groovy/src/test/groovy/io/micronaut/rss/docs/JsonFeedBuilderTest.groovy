package io.micronaut.rss.docs

// tag::imports[]
import io.micronaut.rss.jsonfeed.JsonFeed
import io.micronaut.rss.jsonfeed.JsonFeedItem
// end::imports[]
import groovy.json.JsonSlurper
import io.micronaut.json.JsonMapper
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest(startApplication = false)
class JsonFeedBuilderTest extends Specification {

    @Inject
    JsonMapper jsonMapper

    void "the builder builds a JSON feed"() {
        when:
        // tag::builder[]
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
        // end::builder[]

        then:
        feed.version == "https://jsonfeed.org/version/1.1"
        feed.title == "My Example Feed"
        feed.homePageUrl == "https://example.org/"
        feed.feedUrl == "https://example.org/feed.json"
        feed.items.size() == 2
        feed.items[0].id == "2"
        feed.items[0].contentText == "This is a second item."
        feed.items[0].url == "https://example.org/second-item"
        feed.items[1].id == "1"
        feed.items[1].contentHtml == "<p>Hello, world!</p>"
        feed.items[1].url == "https://example.org/initial-post"

        when: "the JSON Feed keys are serialized in snake case"
        def json = new JsonSlurper().parseText(jsonMapper.writeValueAsString(feed))

        then:
        json.version == "https://jsonfeed.org/version/1.1"
        json.title == "My Example Feed"
        json.home_page_url == "https://example.org/"
        json.feed_url == "https://example.org/feed.json"
        !json.containsKey("homePageUrl")
        json.items.size() == 2
        json.items[0].id == "2"
        json.items[0].content_text == "This is a second item."
        json.items[0].url == "https://example.org/second-item"
        json.items[1].id == "1"
        json.items[1].content_html == "<p>Hello, world!</p>"
        json.items[1].url == "https://example.org/initial-post"
    }
}
