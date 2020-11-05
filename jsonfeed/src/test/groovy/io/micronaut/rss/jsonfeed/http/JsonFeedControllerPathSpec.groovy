package io.micronaut.rss.jsonfeed.http

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.rss.jsonfeed.JsonFeed
import io.micronaut.rss.jsonfeed.JsonFeedItem
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.reactivestreams.Publisher

import javax.inject.Singleton

class JsonFeedControllerPathSpec extends EmbeddedServerSpecification {

    @Override
    String getSpecName() {
        'JsonFeedControllerPathSpec'
    }

    @Override
    Map<String, Object> getConfiguration() {
        super.configuration + ['jsonfeed.path': 'all']
    }

    void "it is possible to change the url of the JSON Feed Controller"() {
        given:
        expect:
        applicationContext.containsBean(JsonFeedProvider)

        when:
        HttpResponse<?> rsp = client.exchange(HttpRequest.GET('/feeds/all'), String)

        then:
        noExceptionThrown()
        rsp.status() == HttpStatus.OK
        rsp.header("Content-Type") == 'application/json+feed'
    }

    @Requires(property = 'spec.name', value = 'JsonFeedControllerPathSpec')
    @Singleton
    static class ExampleJsonFeedProvider implements JsonFeedProvider {

        @NotNull
        @Override
        Publisher<JsonFeed> feed(@Nullable Integer maxNumberOfItems, @Nullable Integer pageNumber) {
            return Flowable.create(emitter -> {
                emitter.onNext(JsonFeed.builder()
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
                emitter.onComplete();
            }, BackpressureStrategy.ERROR);
        }
    }
}
