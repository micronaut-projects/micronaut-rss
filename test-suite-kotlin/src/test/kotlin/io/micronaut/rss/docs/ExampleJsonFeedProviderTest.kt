package io.micronaut.rss.docs

import io.micronaut.context.annotation.Property
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.json.JsonMapper
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

@Property(name = "spec.name", value = "ExampleJsonFeedProviderTest")
@MicronautTest
class ExampleJsonFeedProviderTest {

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Inject
    lateinit var jsonMapper: JsonMapper

    @Test
    fun theJsonFeedControllerServesTheFeedOfTheProvider() {
        val response = client.toBlocking().exchange(HttpRequest.GET<Any>("/feeds/json"), String::class.java)

        assertEquals(HttpStatus.OK, response.status())
        assertEquals("application/json+feed", response.header("Content-Type"))
        val feed = jsonMapper.readValue(response.body()!!, Argument.mapOf(String::class.java, Any::class.java))!!
        assertEquals("https://jsonfeed.org/version/1.1", feed["version"])
        assertEquals("My Example Feed", feed["title"])
        assertEquals("https://example.org/", feed["home_page_url"])
        assertEquals("https://example.org/feed.json", feed["feed_url"])
        @Suppress("UNCHECKED_CAST")
        val authors = feed["authors"] as List<Map<String, Any>>
        assertEquals("Feed Author", authors[0]["name"])
        assertEquals("mailto:feed@example.org", authors[0]["url"])
        @Suppress("UNCHECKED_CAST")
        val items = feed["items"] as List<Map<String, Any>>
        assertEquals(2, items.size)
        assertEquals("2", items[0]["id"])
        assertEquals("This is a second item.", items[0]["content_text"])
        assertEquals("https://example.org/second-item", items[0]["url"])
        @Suppress("UNCHECKED_CAST")
        assertEquals("Item Author", (items[0]["authors"] as List<Map<String, Any>>)[0]["name"])
        assertEquals("1", items[1]["id"])
        assertEquals("<p>Hello, world!</p>", items[1]["content_html"])
        assertEquals("https://example.org/initial-post", items[1]["url"])
        assertFalse(items[1].containsKey("content_text"))
    }
}
