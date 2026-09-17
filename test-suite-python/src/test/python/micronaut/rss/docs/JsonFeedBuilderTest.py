from typing import Annotated

from jakarta.inject import Inject
from micronaut.json import JsonMapper
from micronaut.test.extensions.junit5.annotation import MicronautTest
from org.junit.jupiter.api import Test

# tag::imports[]
from micronaut.rss.jsonfeed import JsonFeed, JsonFeedItem
# end::imports[]


@MicronautTest(startApplication=False)
class JsonFeedBuilderTest:

    json_mapper: Annotated[JsonMapper, Inject]

    @Test
    def the_builder_builds_a_json_feed(self) -> None:
        # tag::builder[]
        feed = (JsonFeed.builder()
            .version("https://jsonfeed.org/version/1.1")
            .title("My Example Feed")
            .homePageUrl("https://example.org/")
            .feedUrl("https://example.org/feed.json")
            .item(JsonFeedItem.builder()
                .id("2")
                .contentText("This is a second item.")
                .url("https://example.org/second-item")
                .build())
            .item(JsonFeedItem.builder()
                .id("1")
                .contentHtml("<p>Hello, world!</p>")
                .url("https://example.org/initial-post")
                .build())
            .build())
        # end::builder[]

        assert feed.getVersion() == "https://jsonfeed.org/version/1.1"
        assert feed.getTitle() == "My Example Feed"
        assert feed.getHomePageUrl() == "https://example.org/"
        assert feed.getFeedUrl() == "https://example.org/feed.json"
        assert feed.getItems().size() == 2
        assert feed.getItems().get(0).getId() == "2"
        assert feed.getItems().get(0).getContentText() == "This is a second item."
        assert feed.getItems().get(0).getUrl() == "https://example.org/second-item"
        assert feed.getItems().get(1).getId() == "1"
        assert feed.getItems().get(1).getContentHtml() == "<p>Hello, world!</p>"
        assert feed.getItems().get(1).getUrl() == "https://example.org/initial-post"

        # the JSON Feed keys are serialized in snake case
        json = self.json_mapper.writeValueAsString(feed)
        assert '"home_page_url":"https://example.org/"' in json
        assert '"feed_url":"https://example.org/feed.json"' in json
        assert '"content_text":"This is a second item."' in json
        assert '"content_html":"<p>Hello, world!</p>"' in json
        assert "homePageUrl" not in json
