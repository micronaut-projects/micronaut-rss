package io.micronaut.rss.docs

import groovy.xml.XmlParser
import io.micronaut.context.annotation.Property
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Shared
import spock.lang.Specification

@Property(name = "spec.name", value = "MockRssFeedProviderSpec")
@MicronautTest
class MockRssFeedProviderSpec extends Specification {

    @Shared
    List<String> expectedGuids = [
            "http://liftoff.msfc.nasa.gov/2003/06/03.html#item573",
            "http://liftoff.msfc.nasa.gov/2003/05/30.html#item572",
            "http://liftoff.msfc.nasa.gov/2003/05/27.html#item571",
            "http://liftoff.msfc.nasa.gov/2003/05/20.html#item570",
    ]

    @Inject
    @Client("/")
    HttpClient client

    void "the feed controller renders the channel of the provider"() {
        when:
        HttpResponse<String> response = client.toBlocking().exchange(HttpRequest.GET("/feed"), String)

        then:
        response.status() == HttpStatus.OK
        response.contentType.get().toString() == MediaType.APPLICATION_XML

        when:
        Node rss = new XmlParser().parseText(response.body())

        then:
        rss.channel.title.text() == "Liftoff News"
        expectedGuids.every { guid -> rss.channel.item.find { it.guid.text() == guid } }
    }

    void "the feed controller renders the channel by id"() {
        when:
        Node rss = new XmlParser().parseText(client.toBlocking().retrieve(HttpRequest.GET("/feed/1")))

        then:
        rss.channel.title.text() == "Liftoff News"
        expectedGuids.every { guid -> rss.channel.item.find { it.guid.text() == guid } }
    }

    void "an unknown id is not found"() {
        when:
        client.toBlocking().retrieve(HttpRequest.GET("/feed/4"))

        then:
        HttpClientResponseException e = thrown()
        e.status == HttpStatus.NOT_FOUND
    }
}
