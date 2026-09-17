import json
from typing import Annotated

from jakarta.inject import Inject
from micronaut.context.annotation import Property
from micronaut.http import HttpRequest, HttpStatus
from micronaut.http.client import HttpClient
from micronaut.http.client.annotation import Client
from micronaut.test.extensions.junit5.annotation import MicronautTest
from org.junit.jupiter.api import Test


@Property(name="spec.name", value="ExampleJsonFeedProviderTest")
@MicronautTest
class ExampleJsonFeedProviderTest:

    client: Annotated[HttpClient, Inject, Client("/")]

    @Test
    def the_json_feed_controller_serves_the_feed_of_the_provider(self) -> None:
        response = self.client.toBlocking().exchange(HttpRequest.GET("/feeds/json"))

        assert response.status() == HttpStatus.OK
        assert response.header("Content-Type") == "application/json+feed"
        feed = json.loads(self.client.toBlocking().retrieve(HttpRequest.GET("/feeds/json")))
        assert feed["version"] == "https://jsonfeed.org/version/1.1"
        assert feed["title"] == "My Example Feed"
        assert feed["home_page_url"] == "https://example.org/"
        assert feed["feed_url"] == "https://example.org/feed.json"
        assert feed["authors"][0]["name"] == "Feed Author"
        assert feed["authors"][0]["url"] == "mailto:feed@example.org"
        items = feed["items"]
        assert len(items) == 2
        assert items[0]["id"] == "2"
        assert items[0]["content_text"] == "This is a second item."
        assert items[0]["url"] == "https://example.org/second-item"
        assert items[0]["authors"][0]["name"] == "Item Author"
        assert items[1]["id"] == "1"
        assert items[1]["content_html"] == "<p>Hello, world!</p>"
        assert items[1]["url"] == "https://example.org/initial-post"
        assert "content_text" not in items[1]
