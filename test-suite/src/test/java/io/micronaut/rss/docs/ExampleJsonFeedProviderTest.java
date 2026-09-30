package io.micronaut.rss.docs;

import io.micronaut.context.annotation.Property;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.JsonMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Property(name = "spec.name", value = "ExampleJsonFeedProviderTest")
@MicronautTest
class ExampleJsonFeedProviderTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    JsonMapper jsonMapper;

    @Test
    void theJsonFeedControllerServesTheFeedOfTheProvider() throws IOException {
        HttpResponse<String> response = client.toBlocking().exchange(HttpRequest.GET("/feeds/json"), String.class);

        assertEquals(HttpStatus.OK, response.status());
        assertEquals("application/json+feed", response.header("Content-Type"));
        Map<String, Object> feed = jsonMapper.readValue(response.body(), Argument.mapOf(String.class, Object.class));
        assertEquals("https://jsonfeed.org/version/1.1", feed.get("version"));
        assertEquals("My Example Feed", feed.get("title"));
        assertEquals("https://example.org/", feed.get("home_page_url"));
        assertEquals("https://example.org/feed.json", feed.get("feed_url"));
        List<Map<String, Object>> authors = (List<Map<String, Object>>) feed.get("authors");
        assertEquals("Feed Author", authors.get(0).get("name"));
        assertEquals("mailto:feed@example.org", authors.get(0).get("url"));
        List<Map<String, Object>> items = (List<Map<String, Object>>) feed.get("items");
        assertEquals(2, items.size());
        assertEquals("2", items.get(0).get("id"));
        assertEquals("This is a second item.", items.get(0).get("content_text"));
        assertEquals("https://example.org/second-item", items.get(0).get("url"));
        assertEquals("Item Author", ((List<Map<String, Object>>) items.get(0).get("authors")).get(0).get("name"));
        assertEquals("1", items.get(1).get("id"));
        assertEquals("<p>Hello, world!</p>", items.get(1).get("content_html"));
        assertEquals("https://example.org/initial-post", items.get(1).get("url"));
        assertTrue(!items.get(1).containsKey("content_text"));
    }
}
