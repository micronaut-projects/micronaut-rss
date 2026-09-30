from typing import Annotated

from jakarta.inject import Inject
from micronaut.context.annotation import Property
from micronaut.http import HttpRequest
from micronaut.http.client import HttpClient
from micronaut.http.client.annotation import Client
from micronaut.test.extensions.junit5.annotation import MicronautTest
from org.junit.jupiter.api import Test


@Property(name="spec.name", value="ItunesPodcastFeedProviderTest")
@MicronautTest
class ItunesPodcastFeedProviderTest:

    client: Annotated[HttpClient, Inject, Client("/")]

    @Test
    def the_feed_controller_renders_the_podcast_with_the_itunes_tags(self) -> None:
        rss = self.client.toBlocking().retrieve(HttpRequest.GET("/feed"))

        assert "<title>Hiking Treks</title>" in rss
        assert "<link>https://www.apple.com/itunes/podcasts/</link>" in rss
        assert "<language>en-us</language>" in rss
        assert "<itunes:author>The Sunset Explorers</itunes:author>" in rss
        assert "<itunes:type>serial</itunes:type>" in rss
        assert "<itunes:name>Sunset Explorers</itunes:name>" in rss
        assert "<itunes:email>mountainscape@icloud.com</itunes:email>" in rss
        assert "<itunes:category text=\"Outdoor\"" in rss
        assert "<itunes:episodeType>trailer</itunes:episodeType>" in rss
        assert "<title>S02 EP04 Mt. Hood, Oregon</title>" in rss
        assert "<itunes:duration>17:04</itunes:duration>" in rss
        assert rss.count("<item>") == 9
