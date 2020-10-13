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
package io.micronaut.rss.jsonfeed;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.io.Writable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.rss.http.FeedController;
import io.micronaut.rss.http.FeedControllerConfigurationProperties;

/**
 * Micronaut {@link io.micronaut.http.annotation.Controller} which exposes an JSON 1.1 Feed.
 */
@Requires(beans = {JsonFeedProvider.class, JsonFeedRenderer.class})
@Controller("${" + FeedControllerConfigurationProperties.PREFIX + ".path:/feed}")
@Requires(property = FeedControllerConfigurationProperties.PREFIX + ".enabled", notEquals = "false")
@Requires(property = FeedControllerConfigurationProperties.PREFIX + ".jsonfeed", value = "true", defaultValue = "false")
@Replaces(FeedController.class)
public class JsonFeedController {

    private final JsonFeedProvider jsonFeedProvider;
    private final JsonFeedRenderer jsonFeedRenderer;
    private final String APPLICATION_JSON_FEED = "application/json+feed";

    /**
     * @param jsonFeedRenderer Provides a bean to render {@link io.micronaut.rss.jsonfeed.JsonFeed}
     */
    public JsonFeedController(JsonFeedProvider jsonFeedProvider,
        JsonFeedRenderer jsonFeedRenderer) {
        this.jsonFeedProvider = jsonFeedProvider;
        this.jsonFeedRenderer = jsonFeedRenderer;
    }

    /**
     * @return Return the default JSON 1.1 provided by {@link io.micronaut.rss.jsonfeed.JsonFeedProvider}
     * and rendered by {@link io.micronaut.rss.jsonfeed.JsonFeedRenderer}.
     */
    @Produces(APPLICATION_JSON_FEED)
    @Get
    public HttpResponse<Writable> index() {
        JsonFeed jsonFeed = jsonFeedProvider.fetch();
        if (jsonFeed == null) {
            return HttpResponse.notFound();
        }
        return HttpResponse.ok(render(jsonFeed));
    }

    private Writable render(JsonFeed jsonFeed) {
        return (writer) -> {
            jsonFeedRenderer.render(writer, jsonFeed);
        };
    }

}
