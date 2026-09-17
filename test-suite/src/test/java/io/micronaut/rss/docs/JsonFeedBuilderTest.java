package io.micronaut.rss.docs;

// tag::imports[]
import io.micronaut.rss.jsonfeed.JsonFeed;
import io.micronaut.rss.jsonfeed.JsonFeedItem;
// end::imports[]
import io.micronaut.json.JsonMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@MicronautTest(startApplication = false)
class JsonFeedBuilderTest {

    @Inject
    JsonMapper jsonMapper;

    @Test
    void theBuilderBuildsAJsonFeed() throws IOException {
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
                .build();
        // end::builder[]

        assertEquals("https://jsonfeed.org/version/1.1", feed.getVersion());
        assertEquals("My Example Feed", feed.getTitle());
        assertEquals("https://example.org/", feed.getHomePageUrl());
        assertEquals("https://example.org/feed.json", feed.getFeedUrl());
        assertEquals(2, feed.getItems().size());
        assertEquals("2", feed.getItems().get(0).getId());
        assertEquals("This is a second item.", feed.getItems().get(0).getContentText());
        assertEquals("https://example.org/second-item", feed.getItems().get(0).getUrl());
        assertEquals("1", feed.getItems().get(1).getId());
        assertEquals("<p>Hello, world!</p>", feed.getItems().get(1).getContentHtml());
        assertEquals("https://example.org/initial-post", feed.getItems().get(1).getUrl());

        // the JSON Feed keys are serialized in snake case
        Map<String, Object> json = jsonMapper.readValue(jsonMapper.writeValueAsString(feed), Map.class);
        assertEquals("https://jsonfeed.org/version/1.1", json.get("version"));
        assertEquals("My Example Feed", json.get("title"));
        assertEquals("https://example.org/", json.get("home_page_url"));
        assertEquals("https://example.org/feed.json", json.get("feed_url"));
        assertFalse(json.containsKey("homePageUrl"));
        List<Map<String, Object>> items = (List<Map<String, Object>>) json.get("items");
        assertEquals(2, items.size());
        assertEquals("2", items.get(0).get("id"));
        assertEquals("This is a second item.", items.get(0).get("content_text"));
        assertEquals("https://example.org/second-item", items.get(0).get("url"));
        assertEquals("1", items.get(1).get("id"));
        assertEquals("<p>Hello, world!</p>", items.get(1).get("content_html"));
        assertEquals("https://example.org/initial-post", items.get(1).get("url"));
    }
}
