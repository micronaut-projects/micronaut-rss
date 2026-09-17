package io.micronaut.rss.docs

import io.micronaut.context.annotation.Property
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@Property(name = "spec.name", value = "ItunesPodcastFeedProviderTest")
@MicronautTest
class ItunesPodcastFeedProviderTest {

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun theFeedControllerRendersThePodcastWithTheItunesTags() {
        val rss = client.toBlocking().retrieve(HttpRequest.GET<Any>("/feed"))

        assertTrue(rss.contains("<title>Hiking Treks</title>"))
        assertTrue(rss.contains("<link>https://www.apple.com/itunes/podcasts/</link>"))
        assertTrue(rss.contains("<language>en-us</language>"))
        assertTrue(rss.contains("<itunes:author>The Sunset Explorers</itunes:author>"))
        assertTrue(rss.contains("<itunes:type>serial</itunes:type>"))
        assertTrue(rss.contains("<itunes:name>Sunset Explorers</itunes:name>"))
        assertTrue(rss.contains("<itunes:email>mountainscape@icloud.com</itunes:email>"))
        assertTrue(rss.contains("<itunes:category text=\"Outdoor\""))
        assertTrue(rss.contains("<itunes:episodeType>trailer</itunes:episodeType>"))
        assertTrue(rss.contains("<title>S02 EP04 Mt. Hood, Oregon</title>"))
        assertTrue(rss.contains("<itunes:duration>17:04</itunes:duration>"))
        assertEquals(9, rss.split("<item>").size - 1)
    }
}
