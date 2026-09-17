from typing import Annotated

from jakarta.inject import Inject
from micronaut.context.annotation import Property
from micronaut.http import HttpRequest, HttpStatus, MediaType
from micronaut.http.client import HttpClient
from micronaut.http.client.annotation import Client
from micronaut.http.client.exceptions import HttpClientResponseException
from micronaut.test.extensions.junit5.annotation import MicronautTest
from org.junit.jupiter.api import Test

EXPECTED_GUIDS = [
    "http://liftoff.msfc.nasa.gov/2003/06/03.html#item573",
    "http://liftoff.msfc.nasa.gov/2003/05/30.html#item572",
    "http://liftoff.msfc.nasa.gov/2003/05/27.html#item571",
    "http://liftoff.msfc.nasa.gov/2003/05/20.html#item570",
]


@Property(name="spec.name", value="MockRssFeedProviderTest")
@MicronautTest
class MockRssFeedProviderTest:

    client: Annotated[HttpClient, Inject, Client("/")]

    @Test
    def the_feed_controller_renders_the_channel_of_the_provider(self) -> None:
        response = self.client.toBlocking().exchange(HttpRequest.GET("/feed"))

        assert response.status() == HttpStatus.OK
        assert str(response.getContentType().get()) == MediaType.APPLICATION_XML
        rss = self.client.toBlocking().retrieve(HttpRequest.GET("/feed"))
        assert "<title>Liftoff News</title>" in rss
        for guid in EXPECTED_GUIDS:
            assert "<guid>" + guid + "</guid>" in rss

    @Test
    def the_feed_controller_renders_the_channel_by_id(self) -> None:
        rss = self.client.toBlocking().retrieve(HttpRequest.GET("/feed/1"))

        assert "<title>Liftoff News</title>" in rss
        for guid in EXPECTED_GUIDS:
            assert "<guid>" + guid + "</guid>" in rss

    @Test
    def an_unknown_id_is_not_found(self) -> None:
        try:
            self.client.toBlocking().retrieve(HttpRequest.GET("/feed/4"))
        except HttpClientResponseException as e:
            assert e.getStatus() == HttpStatus.NOT_FOUND
        else:
            assert False, "expected a 404 response"
