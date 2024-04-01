package io.micronaut.rss.jsonfeed.http;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.rss.jsonfeed.JsonFeed;
import io.micronaut.rss.jsonfeed.JsonFeedItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import org.reactivestreams.Publisher;

import jakarta.inject.Singleton;
@Requires(property = "spec.name", value = "JsonFeedControllerSpec")
//tag::class[]
@Singleton
public class ExampleJsonFeedProvider implements JsonFeedProvider {

    @NonNull
    @SingleResult
    @Override
    public Publisher<JsonFeed> feed(@Nullable Integer maxNumberOfItems, @Nullable Integer pageNumber) {
        return Flux.create( emitter -> {
            emitter.next(JsonFeed.builder()
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
                    .build());
            emitter.complete();
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
//end::class[]
