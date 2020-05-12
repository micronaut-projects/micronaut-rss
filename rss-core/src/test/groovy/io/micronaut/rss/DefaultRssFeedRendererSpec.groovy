package io.micronaut.rss

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

class DefaultRssFeedRendererSpec extends Specification {

    @Subject
    @Shared
    DefaultRssFeedRenderer rssFeedRenderer = new DefaultRssFeedRenderer()


    void "description with html is escaped with with CData"() {
        given:
        RssChannel rssChannel = RssChannel.builder("Liftoff News", "http://liftoff.msfc.nasa.gov/", "Liftoff to Space Exploration.")
                .item(RssItem.builder()
                .description("This is <b>bold</b>.").build()).build()

        when:
        StringWriter writer = new StringWriter()
        rssFeedRenderer.render(writer, rssChannel)
        String feed = writer.toString()

        then:
        feed
        feed.contains('<description><![CDATA[This is <b>bold</b>.]]></description>')
    }

}
