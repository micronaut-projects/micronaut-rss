package io.micronaut.rss.itunespodcast

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.rss.RssFeedRenderer
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class PodcastFeedControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    ApplicationContext context = ApplicationContext.run(['spec.name': getClass().simpleName])

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = context.getBean(EmbeddedServer).start()

    @Shared
    @AutoCleanup
    RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    void "fetch feed"() {
        expect:
        embeddedServer.applicationContext.containsBean(DefaultItunesPodcastRenderer)

        when:
        RssFeedRenderer rssFeedRenderer = embeddedServer.applicationContext.getBean(RssFeedRenderer)

        then:
        noExceptionThrown()
        rssFeedRenderer instanceof DefaultItunesPodcastRenderer

        when:
        HttpRequest request = HttpRequest.GET('/feed')
        String rsp  = client.toBlocking().retrieve(request)
        def rss = new XmlParser().parseText(rsp)

        then:
        rss.channel.title.text() == 'Hiking Treks'
        rss.channel.link.text() == 'https://www.apple.com/itunes/podcasts/'
        rss.channel.language.text() == 'en-us'
        rss.channel.copyright.text() == '&#8471; &amp; &#xA9; 2017 John Appleseed'
        rss.channel.'itunes:subtitle'.text() == 'Find your trail. Great hikes and outdoor adventures.'
        rss.channel.'itunes:author'.text() == 'The Sunset Explorers'
        rss.channel.'itunes:summary'.text() == 'Love to get outdoors and discover nature&apos;s treasures? Hiking Treks is the show for you. We review hikes and excursions, review outdoor gear and interview a variety of naturalists and adventurers. Look for new episodes each week.'
        rss.channel.description.text() == 'Love to get outdoors and discover nature&apos;s treasures? Hiking Treks is the show for you. We review hikes and excursions, review outdoor gear and interview a variety of naturalists and adventurers. Look for new episodes each week.'
        rss.channel.'itunes:type'.text() == 'serial'
        rss.channel.'itunes:owner'.'itunes:name'.text() == 'Sunset Explorers'
        rss.channel.'itunes:owner'.'itunes:email'.text() == 'mountainscape@icloud.com'

        rss.channel.'itunes:image'.'@href'.text() == 'http://podcasts.apple.com/resources/example/hiking_treks/images/cover_art.jpg'

        rss.channel.'itunes:category'.'@text'.text() == 'Sports &amp; Recreation'
        rss.channel.'itunes:category'.'itunes:category'.'@text'.text() == 'Outdoor'

        rss.channel.'itunes:explicit'.text() == 'no'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.'itunes:title'.text() == 'Hiking Treks Trailer'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.'itunes:episodeType'.text() == 'trailer'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.'itunes:author'.text() == 'The Sunset Adventurers'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.'itunes:subtitle'.text() == 'The Sunset Explorers share tips, techniques and recommendations for great hikes and adventures around the United States.'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.'itunes:summary'.text() == 'The Sunset Explorers share tips, techniques and recommendations for great hikes and adventures around the United States.'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.description.text() == 'The Sunset Explorers share tips, techniques and recommendations for great hikes and adventures around the United States.'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.'content:encoded'.text() == '<![CDATA[The Sunset Explorers share tips, techniques and recommendations for great hikes and adventures around the United States. Listen on <a href="https://www.apple.com/itunes/podcasts/">Apple Podcasts</a>]]>'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.enclosure.'@length'.text() == '498537'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.enclosure.'@type'.text() == 'audio/mpeg'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.enclosure.'@url'.text() == 'http://example.com/podcasts/everything/AllAboutEverythingEpisode4.mp3'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.guid.text() == 'http://example.com/podcasts/archive/aae20160418.mp3'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.pubDate.text() == 'Tue, 12 Apr 2016 01:15:00 GMT'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.'itunes:duration'.text() == '17:59'
        rss.channel.item.find { it.title.text() == 'Hiking Treks Trailer' }.'itunes:explicit'.text() == 'no'
    }

}
