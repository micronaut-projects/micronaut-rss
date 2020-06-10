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
package io.micronaut.rss.http;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.io.Writable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.rss.RssChannel;
import io.micronaut.rss.RssFeedProvider;
import io.micronaut.rss.RssFeedRenderer;

import java.io.Serializable;

/**
 *
 * Micronaut {@link io.micronaut.http.annotation.Controller} which exposes an RSS 2.0 Feed.
 *
 * @author Sergio del Amo
 * @since 1.0
 */
@Requires(beans = {RssFeedProvider.class, RssFeedRenderer.class})
@Controller("${" + FeedControllerConfigurationProperties.PREFIX + ".path:/feed}")
@Requires(property = FeedControllerConfigurationProperties.PREFIX + ".enabled", notEquals = "false")
public class FeedController {

    private final RssFeedProvider rssFeedProvider;
    private final RssFeedRenderer rssFeedRenderer;

    /**
     *
     * @param rssFeedProvider A bean which returns RSS 2.0 feeds.
     * @param rssFeedRenderer Provides a bean to render {@link io.micronaut.rss.RssChannel}
     */
    public FeedController(RssFeedProvider rssFeedProvider,
                          RssFeedRenderer rssFeedRenderer) {
        this.rssFeedProvider = rssFeedProvider;
        this.rssFeedRenderer = rssFeedRenderer;
    }

    /**
     *
     * @return Return the default RSS 2.0 provided by {@link io.micronaut.rss.RssFeedProvider} and rendered by {@link io.micronaut.rss.RssFeedRenderer}.
     */
    @Produces(MediaType.APPLICATION_XML)
    @Get
    public HttpResponse<Writable> index() {
        RssChannel rssChannel = rssFeedProvider.fetch();
        if (rssChannel == null) {
            return HttpResponse.notFound();
        }
        return HttpResponse.ok(render(rssChannel));
    }

    /**
     *
     * @param id RSS guid
     * @return Return the RSS 2.0 identified by the path parameter provided by {@link io.micronaut.rss.RssFeedProvider} and rendered by {@link io.micronaut.rss.RssFeedRenderer}.
     */
    @Produces(MediaType.APPLICATION_XML)
    @Get("/{id}")
    public HttpResponse<Writable> find(Serializable id) {
        RssChannel rssChannel = rssFeedProvider.fetchById(id);
        if (rssChannel == null) {
            return HttpResponse.notFound();
        }
        return HttpResponse.ok(render(rssChannel));
    }

    private Writable render(RssChannel rssChannel) {
        return (writer) -> {
            rssFeedRenderer.render(writer, rssChannel);
        };
    }

}
