package io.micronaut.rss.jsonfeed.http

import io.micronaut.context.annotation.Property
import io.micronaut.context.annotation.Requires
import io.micronaut.core.annotation.NonNull
import io.micronaut.core.annotation.Nullable
import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.rss.jsonfeed.JsonFeed
import io.micronaut.rss.jsonfeed.JsonFeedItem
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import reactor.core.publisher.FluxSink
import reactor.core.publisher.Flux
import org.reactivestreams.Publisher
import jakarta.inject.Singleton
import spock.lang.Specification

@Property(name = "spec.name", value = "JsonFeedControllerPathSpec")
@Property(name = "jsonfeed.path", value = "all")
@MicronautTest
class JsonFeedControllerPathSpec extends Specification {
    @Inject
    @Client("/")
    HttpClient httpClient

    void "it is possible to change the url of the JSON Feed Controller"() {
        when:
        HttpResponse<?> rsp = httpClient.toBlocking().exchange(HttpRequest.GET('/feeds/all'), String)

        then:
        noExceptionThrown()
        rsp.status() == HttpStatus.OK
        rsp.header("Content-Type") == 'application/json+feed'
    }

    @Requires(property = 'spec.name', value = 'JsonFeedControllerPathSpec')
    @Singleton
    static class ExampleJsonFeedProvider implements JsonFeedProvider {

        @NonNull
        @SingleResult
        @Override
        Publisher<JsonFeed> feed(@Nullable Integer maxNumberOfItems, @Nullable Integer pageNumber) {
            return Flux.create(emitter -> {
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
                        .build())
                emitter.complete()
            }, FluxSink.OverflowStrategy.ERROR)
        }
    }
}
