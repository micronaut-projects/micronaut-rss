package io.micronaut.rss.jsonfeed.http

import io.micronaut.context.ApplicationContext
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
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import org.reactivestreams.Publisher

import jakarta.inject.Singleton
import spock.lang.Specification

@Property(name = 'spec.name', value = 'JsonFeedControllerParamsSpec')
@MicronautTest
class JsonFeedControllerParamsSpec extends Specification {
    @Inject
    ApplicationContext applicationContext

    @Inject
    @Client("/")
    HttpClient httpClient

    void "/feeds/json passes query values parameters to JSON Feed Provider"() {
        given:
        expect:
        applicationContext.containsBean(JsonFeedProvider)

        when:
        HttpResponse<?> rsp = httpClient.toBlocking().exchange(HttpRequest.GET('/feeds/json?maxNumberOfItems=50&pageNumber=0'), String)

        then:
        noExceptionThrown()
        rsp.status() == HttpStatus.OK
        rsp.header("Content-Type") == 'application/json+feed'
        rsp.getBody(String).isPresent()

        when:
        ExampleJsonFeedProvider exampleJsonFeedProvider = applicationContext.getBean(ExampleJsonFeedProvider)

        then:
        exampleJsonFeedProvider.pageNumber == 0
        exampleJsonFeedProvider.maxNumberOfItems == 50
    }

    @Requires(property = 'spec.name', value = 'JsonFeedControllerParamsSpec')
    @Singleton
    static class ExampleJsonFeedProvider implements JsonFeedProvider {

        @Nullable
        Integer maxNumberOfItems

        @Nullable
        Integer pageNumber

        @NonNull
        @SingleResult
        @Override
        Publisher<JsonFeed> feed(@Nullable Integer maxNumberOfItems, @Nullable Integer pageNumber) {
            this.maxNumberOfItems = maxNumberOfItems
            this.pageNumber = pageNumber
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
                        .build());
                emitter.complete()
            }, FluxSink.OverflowStrategy.ERROR)
        }
    }
}
