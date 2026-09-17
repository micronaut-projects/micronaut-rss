package io.micronaut.rss.docs;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Property(name = "spec.name", value = "MockRssFeedProviderTest")
@MicronautTest
class MockRssFeedProviderTest {

    private static final List<String> EXPECTED_GUIDS = List.of(
            "http://liftoff.msfc.nasa.gov/2003/06/03.html#item573",
            "http://liftoff.msfc.nasa.gov/2003/05/30.html#item572",
            "http://liftoff.msfc.nasa.gov/2003/05/27.html#item571",
            "http://liftoff.msfc.nasa.gov/2003/05/20.html#item570");

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void theFeedControllerRendersTheChannelOfTheProvider() {
        HttpResponse<String> response = client.toBlocking().exchange(HttpRequest.GET("/feed"), String.class);

        assertEquals(HttpStatus.OK, response.status());
        assertEquals(MediaType.APPLICATION_XML, response.getContentType().map(MediaType::toString).orElse(null));
        String rss = response.body();
        assertTrue(rss.contains("<title>Liftoff News</title>"));
        for (String guid : EXPECTED_GUIDS) {
            assertTrue(rss.contains("<guid>" + guid + "</guid>"), guid);
        }
    }

    @Test
    void theFeedControllerRendersTheChannelById() {
        String rss = client.toBlocking().retrieve(HttpRequest.GET("/feed/1"));

        assertTrue(rss.contains("<title>Liftoff News</title>"));
        for (String guid : EXPECTED_GUIDS) {
            assertTrue(rss.contains("<guid>" + guid + "</guid>"), guid);
        }
    }

    @Test
    void anUnknownIdIsNotFound() {
        HttpClientResponseException e = assertThrows(HttpClientResponseException.class,
                () -> client.toBlocking().retrieve(HttpRequest.GET("/feed/4")));

        assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
    }
}
