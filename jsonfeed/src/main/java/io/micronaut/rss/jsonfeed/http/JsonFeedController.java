/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.rss.jsonfeed.http;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.rss.jsonfeed.JsonFeed;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Exposes an endpoint, by default /feeds/json, which return a JSON Feed.
 * @author Sergio del Amo
 * @since 2.2.0
 */
@Requires(beans = JsonFeedProvider.class)
@Requires(property = JsonFeedControllerConfigurationProperties.PREFIX + ".enabled", notEquals = "false")
@Controller("${" + JsonFeedControllerConfigurationProperties.PREFIX + ".root-path:/feeds}")
public class JsonFeedController {

    @SuppressWarnings("WeakerAccess")
    public static final String APPLICATION_JSON_FEED = "application/json+feed";

    private final JsonFeedProvider jsonFeedProvider;

    /**
     *
     * @param jsonFeedProvider JSON Feed Provider
     */
    public JsonFeedController(JsonFeedProvider jsonFeedProvider) {
        this.jsonFeedProvider = jsonFeedProvider;
    }

    /**
     *
     * @param maxNumberOfItems Max number of items in the JSON Feed. Optional.
     * @param pageNumber Requested Page. Optional.
     * @return a JSON Feed.
     */
    @Produces(APPLICATION_JSON_FEED)
    @Get("${" + JsonFeedControllerConfigurationProperties.PREFIX + ".path:/json}{?maxNumberOfItems,pageNumber}")
    public Single<MutableHttpResponse<?>> index(@QueryValue @Nullable Integer maxNumberOfItems,
                                                      @QueryValue @Nullable Integer pageNumber) {
        return Flowable.fromPublisher(jsonFeedProvider.feed(maxNumberOfItems, pageNumber))
                .map(this::okResponse)
                .first(HttpResponse.status(HttpStatus.NOT_FOUND));
    }

    private MutableHttpResponse<?> okResponse(@NonNull JsonFeed jsonFeed) {
        return HttpResponse.ok(jsonFeed);
    }
}
