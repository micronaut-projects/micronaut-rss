package io.micronaut.rss.docs

// tag::imports[]
import io.micronaut.rss.jsonfeed.JsonFeed
import io.micronaut.rss.jsonfeed.JsonFeedItem
// end::imports[]
import io.micronaut.json.JsonMapper
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

@MicronautTest(startApplication = false)
class JsonFeedBuilderTest {

    @Inject
    lateinit var jsonMapper: JsonMapper

    @Test
    fun theBuilderBuildsAJsonFeed() {
        // tag::builder[]
        val feed = JsonFeed.builder()
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

        assertEquals("https://jsonfeed.org/version/1.1", feed.version)
        assertEquals("My Example Feed", feed.title)
        assertEquals("https://example.org/", feed.homePageUrl)
        assertEquals("https://example.org/feed.json", feed.feedUrl)
        assertEquals(2, feed.items.size)
        assertEquals("2", feed.items[0].id)
        assertEquals("This is a second item.", feed.items[0].contentText)
        assertEquals("https://example.org/second-item", feed.items[0].url)
        assertEquals("1", feed.items[1].id)
        assertEquals("<p>Hello, world!</p>", feed.items[1].contentHtml)
        assertEquals("https://example.org/initial-post", feed.items[1].url)

        // the JSON Feed keys are serialized in snake case
        @Suppress("UNCHECKED_CAST")
        val json = jsonMapper.readValue(jsonMapper.writeValueAsString(feed), Map::class.java) as Map<String, Any>
        assertEquals("https://jsonfeed.org/version/1.1", json["version"])
        assertEquals("My Example Feed", json["title"])
        assertEquals("https://example.org/", json["home_page_url"])
        assertEquals("https://example.org/feed.json", json["feed_url"])
        assertFalse(json.containsKey("homePageUrl"))
        @Suppress("UNCHECKED_CAST")
        val items = json["items"] as List<Map<String, Any>>
        assertEquals(2, items.size)
        assertEquals("2", items[0]["id"])
        assertEquals("This is a second item.", items[0]["content_text"])
        assertEquals("https://example.org/second-item", items[0]["url"])
        assertEquals("1", items[1]["id"])
        assertEquals("<p>Hello, world!</p>", items[1]["content_html"])
        assertEquals("https://example.org/initial-post", items[1]["url"])
    }
}
