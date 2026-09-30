package io.micronaut.rss.docs

import groovy.xml.XmlParser
import io.micronaut.context.annotation.Property
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@Property(name = "spec.name", value = "ItunesPodcastFeedProviderSpec")
@MicronautTest
class ItunesPodcastFeedProviderSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    void "the feed controller renders the podcast with the itunes tags"() {
        when:
        Node rss = new XmlParser().parseText(client.toBlocking().retrieve(HttpRequest.GET("/feed")))

        then:
        rss.channel.title.text() == "Hiking Treks"
        rss.channel.link.text() == "https://www.apple.com/itunes/podcasts/"
        rss.channel.language.text() == "en-us"
        rss.channel."itunes:author".text() == "The Sunset Explorers"
        rss.channel."itunes:type".text() == "serial"
        rss.channel."itunes:owner"."itunes:name".text() == "Sunset Explorers"
        rss.channel."itunes:owner"."itunes:email".text() == "mountainscape@icloud.com"
        rss.channel."itunes:category"."itunes:category"."@text".text() == "Outdoor"
        rss.channel.item.size() == 9
        rss.channel.item[0]."itunes:episodeType".text() == "trailer"
        rss.channel.item[1].title.text() == "S02 EP04 Mt. Hood, Oregon"
        rss.channel.item[1]."itunes:duration".text() == "17:04"
    }
}
