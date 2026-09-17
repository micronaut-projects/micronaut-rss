from micronaut.context.annotation import Requires

# tag::imports[]
from jakarta.inject import Singleton
from micronaut.core.async_.annotation import SingleResult
from micronaut.rss.jsonfeed import JsonFeed, JsonFeedAuthor, JsonFeedItem
from micronaut.rss.jsonfeed.http import JsonFeedProvider
from org.reactivestreams import Publisher
from reactor.core.publisher import Flux, FluxSink
# end::imports[]


@Requires(property="spec.name", value="ExampleJsonFeedProviderTest")
# tag::class[]
@Singleton
class ExampleJsonFeedProvider(JsonFeedProvider):

    @SingleResult
    def feed(self, max_number_of_items: int | None, page_number: int | None) -> Publisher[JsonFeed]:
        def emit(emitter):
            emitter.next(JsonFeed.builder()
                .version("https://jsonfeed.org/version/1.1")
                .title("My Example Feed")
                .homePageUrl("https://example.org/")
                .feedUrl("https://example.org/feed.json")
                .author(JsonFeedAuthor.builder()
                    .name("Feed Author")
                    .url("mailto:feed@example.org")
                    .build())
                .item(JsonFeedItem.builder()
                    .id("2")
                    .contentText("This is a second item.")
                    .author(JsonFeedAuthor.builder()
                        .name("Item Author")
                        .build())
                    .url("https://example.org/second-item")
                    .build())
                .item(JsonFeedItem.builder()
                    .id("1")
                    .contentHtml("<p>Hello, world!</p>")
                    .url("https://example.org/initial-post")
                    .build())
                .build())
            emitter.complete()

        return Flux.create(emit, FluxSink.OverflowStrategy.ERROR)
# end::class[]
