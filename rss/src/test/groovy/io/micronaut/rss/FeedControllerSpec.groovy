package io.micronaut.rss

import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class FeedControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    ApplicationContext context = ApplicationContext.run([
            'spec.name': getClass().simpleName
    ])

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = context.getBean(EmbeddedServer).start()

    @Shared
    @AutoCleanup
    RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    void "fetch feed"() {
        expect:
        embeddedServer.applicationContext.containsBean(MockRssFeedProvider)

        when:
        HttpRequest request = HttpRequest.GET('/feed')
        String rsp  = client.toBlocking().retrieve(request)
        def rss = new XmlParser().parseText(rsp)

        then:
        rss.channel.item.find { it.guid.text() == "http://liftoff.msfc.nasa.gov/2003/06/03.html#item573"}
        rss.channel.item.find { it.guid.text() == "http://liftoff.msfc.nasa.gov/2003/05/30.html#item572"}
        rss.channel.item.find { it.guid.text() == "http://liftoff.msfc.nasa.gov/2003/05/27.html#item571"}
        rss.channel.item.find { it.guid.text() == "http://liftoff.msfc.nasa.gov/2003/05/20.html#item570"}

        when:
        request = HttpRequest.GET('/feed/1')
        rsp  = client.toBlocking().retrieve(request)
        rss = new XmlParser().parseText(rsp)

        then:
        rss.channel.item.find { it.guid.text() == "http://liftoff.msfc.nasa.gov/2003/06/03.html#item573"}
        rss.channel.item.find { it.guid.text() == "http://liftoff.msfc.nasa.gov/2003/05/30.html#item572"}
        rss.channel.item.find { it.guid.text() == "http://liftoff.msfc.nasa.gov/2003/05/27.html#item571"}
        rss.channel.item.find { it.guid.text() == "http://liftoff.msfc.nasa.gov/2003/05/20.html#item570"}

        when:
        request = HttpRequest.HEAD('/feed/')
        HttpResponse httpResponse = client.toBlocking().exchange(request)

        then:
        httpResponse.status() == HttpStatus.OK

        when:
        request = HttpRequest.HEAD('/feed/1')
        httpResponse = client.toBlocking().exchange(request)

        then:
        httpResponse.status() == HttpStatus.OK

        when:
        client.toBlocking().retrieve(HttpRequest.GET('/feed/4'))

        then:
        def e = thrown(HttpClientResponseException)
        e.response.status() == HttpStatus.NOT_FOUND
    }

}
