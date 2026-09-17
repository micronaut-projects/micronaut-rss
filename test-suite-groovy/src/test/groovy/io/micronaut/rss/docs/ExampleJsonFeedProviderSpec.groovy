package io.micronaut.rss.docs

import groovy.json.JsonSlurper
import io.micronaut.context.annotation.Property
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@Property(name = "spec.name", value = "ExampleJsonFeedProviderSpec")
@MicronautTest
class ExampleJsonFeedProviderSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    void "the JSON feed controller serves the feed of the provider"() {
        when:
        HttpResponse<String> response = client.toBlocking().exchange(HttpRequest.GET("/feeds/json"), String)

        then:
        response.status() == HttpStatus.OK
        response.header("Content-Type") == "application/json+feed"

        when:
        def feed = new JsonSlurper().parseText(response.body())

        then:
        feed.version == "https://jsonfeed.org/version/1.1"
        feed.title == "My Example Feed"
        feed.home_page_url == "https://example.org/"
        feed.feed_url == "https://example.org/feed.json"
        feed.authors[0].name == "Feed Author"
        feed.authors[0].url == "mailto:feed@example.org"
        feed.items.size() == 2
        feed.items[0].id == "2"
        feed.items[0].content_text == "This is a second item."
        feed.items[0].url == "https://example.org/second-item"
        feed.items[0].authors[0].name == "Item Author"
        feed.items[1].id == "1"
        feed.items[1].content_html == "<p>Hello, world!</p>"
        feed.items[1].url == "https://example.org/initial-post"
        !feed.items[1].containsKey("content_text")
    }
}
