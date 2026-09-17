package io.micronaut.rss.docs

import io.micronaut.context.annotation.Requires

// tag::imports[]
import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.rss.jsonfeed.JsonFeed
import io.micronaut.rss.jsonfeed.JsonFeedAuthor
import io.micronaut.rss.jsonfeed.JsonFeedItem
import io.micronaut.rss.jsonfeed.http.JsonFeedProvider
import jakarta.inject.Singleton
import org.jspecify.annotations.NonNull
import org.jspecify.annotations.Nullable
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
// end::imports[]

@Requires(property = "spec.name", value = "ExampleJsonFeedProviderSpec")
// tag::class[]
@Singleton
class ExampleJsonFeedProvider implements JsonFeedProvider {

    @NonNull
    @SingleResult
    @Override
    Publisher<JsonFeed> feed(@Nullable Integer maxNumberOfItems, @Nullable Integer pageNumber) {
        Flux.create({ emitter ->
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
        }, FluxSink.OverflowStrategy.ERROR)
    }
}
// end::class[]
