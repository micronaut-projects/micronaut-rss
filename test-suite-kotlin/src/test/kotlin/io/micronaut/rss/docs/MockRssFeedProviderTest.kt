package io.micronaut.rss.docs

import io.micronaut.context.annotation.Property
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@Property(name = "spec.name", value = "MockRssFeedProviderTest")
@MicronautTest
class MockRssFeedProviderTest {

    private val expectedGuids = listOf(
        "http://liftoff.msfc.nasa.gov/2003/06/03.html#item573",
        "http://liftoff.msfc.nasa.gov/2003/05/30.html#item572",
        "http://liftoff.msfc.nasa.gov/2003/05/27.html#item571",
        "http://liftoff.msfc.nasa.gov/2003/05/20.html#item570")

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun theFeedControllerRendersTheChannelOfTheProvider() {
        val response = client.toBlocking().exchange(HttpRequest.GET<Any>("/feed"), String::class.java)

        assertEquals(HttpStatus.OK, response.status())
        assertEquals(MediaType.APPLICATION_XML, response.contentType.map { it.toString() }.orElse(null))
        val rss = response.body()!!
        assertTrue(rss.contains("<title>Liftoff News</title>"))
        for (guid in expectedGuids) {
            assertTrue(rss.contains("<guid>$guid</guid>"), guid)
        }
    }

    @Test
    fun theFeedControllerRendersTheChannelById() {
        val rss = client.toBlocking().retrieve(HttpRequest.GET<Any>("/feed/1"))

        assertTrue(rss.contains("<title>Liftoff News</title>"))
        for (guid in expectedGuids) {
            assertTrue(rss.contains("<guid>$guid</guid>"), guid)
        }
    }

    @Test
    fun anUnknownIdIsNotFound() {
        val e = assertThrows(HttpClientResponseException::class.java) {
            client.toBlocking().retrieve(HttpRequest.GET<Any>("/feed/4"))
        }

        assertEquals(HttpStatus.NOT_FOUND, e.status)
    }
}
