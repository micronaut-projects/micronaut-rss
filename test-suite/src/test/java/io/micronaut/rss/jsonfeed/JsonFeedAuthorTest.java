package io.micronaut.rss.jsonfeed;

import io.micronaut.json.JsonMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest(startApplication = false)
class JsonFeedAuthorTest {
    @Test
    void jsonFeedAuthorSerialization(JsonMapper jsonMapper) throws IOException {
        JsonFeedAuthor author = JsonFeedAuthor.builder()
            .name("John Doe")
            .url("mailto:support@mycompany.com")
            .avatar("https://staging.storage.noticeable.io/users/1hvH7RWx9CWe8QCskAHBcsgpbkF2/01h5m04a42c02czp09khyrx3bm-avatar.png")
            .build();
        String json = jsonMapper.writeValueAsString(author);
        assertNotNull(json);
        assertFalse(json.contains("empty"));
    }
}
