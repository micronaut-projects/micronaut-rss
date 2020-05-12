package io.micronaut.rss

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import spock.lang.Shared

class FeedControllerSpec extends EmbeddedServerSpecification {

    @Shared
    List<String> expectedGuids = [
            "http://liftoff.msfc.nasa.gov/2003/06/03.html#item573",
            "http://liftoff.msfc.nasa.gov/2003/05/30.html#item572",
            "http://liftoff.msfc.nasa.gov/2003/05/27.html#item571",
            "http://liftoff.msfc.nasa.gov/2003/05/20.html#item570",
    ]

    void "fetch feed"() {
        expect:
        embeddedServer.applicationContext.containsBean(MockRssFeedProvider)

        when:
        HttpRequest request = HttpRequest.HEAD('/feed')
        HttpResponse httpResponse = client.exchange(request)

        then:
        noExceptionThrown()
        httpResponse.status() == HttpStatus.OK

        when:
        request = HttpRequest.GET('/feed')
        String rsp  = client.retrieve(request, String)

        then:
        noExceptionThrown()

        when:
        Node rss = new XmlParser().parseText(rsp)

        then:
        expectedGuids.each { guid ->
            assert rss.channel.item.find { it.guid.text() == guid }
        }

        when:
        request = HttpRequest.GET('/feed/1')
        rsp  = client.retrieve(request, String)

        then:
        noExceptionThrown()

        when:
        rss = new XmlParser().parseText(rsp)

        then:
        expectedGuids.each { guid ->
            assert rss.channel.item.find { it.guid.text() == guid }
        }

        when:
        request = HttpRequest.HEAD('/feed/1')
        httpResponse = client.exchange(request)

        then:
        noExceptionThrown()
        httpResponse.status() == HttpStatus.OK

        when:
        client.retrieve(HttpRequest.GET('/feed/4'))

        then:
        HttpClientResponseException e = thrown()
        e.response.status() == HttpStatus.NOT_FOUND
    }
}
