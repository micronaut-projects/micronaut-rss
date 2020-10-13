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

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.rss.RssLanguage;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.inject.Singleton;

/**
 * @see: https://jsonfeed.org/version/1.1
 */
@Requires(env = Environment.TEST)
@Requires(property = "spec.name", value = "JsonFeedControllerSpec")
//tag::class[]
@Singleton
public class DefaultJsonFeedProvider implements JsonFeedProvider {
    @Override
    public JsonFeed fetch() {
        return JsonFeed.builder()
            .version("https://jsonfeed.org/version/1.1")
            .userComment("This is a podcast feed. You can add this feed to your podcast client using the following URL: http://therecord.co/feed.json")
            .title("The Record")
            .homePageUrl("http://therecord.co/")
            .feedUrl("http://therecord.co/feed.json")
            .language(RssLanguage.LANG_ENGLISH)
            .hub(JsonHub.builder()
                .type("rssCloud")
                .url("http://test.com")
                .build()
            )
            .author(JsonFeedAuthor.builder()
                .name("Brent Simmons")
                .url("http://example.org/")
                .avatar("https://example.org/avatar.png")
                .build()
            )
            .item(JsonFeedItem.builder("1")
                .contentText("This is a second item.")
                .url("https://example.org/second-item")
                .tag("test")
                .build()
            )
            .item(JsonFeedItem.builder("2")
                .title("Special #1 - Chris Parrish")
                .url("http://therecord.co/chris-parrish")
                .contentText("Chris has worked at Adobe and as a founder of Rogue Sheep, which won an Apple Design Award for Postage. Chris’s new company is Aged & Distilled with Guy English — which shipped Napkin, a Mac app for visual collaboration. Chris is also the co-host of The Record. He lives on Bainbridge Island, a quick ferry ride from Seattle.")
                .contentHtml("Chris has worked at <a href=\"http://adobe.com/\">Adobe</a> and as a founder of Rogue Sheep, which won an Apple Design Award for Postage. Chris’s new company is Aged & Distilled with Guy English — which shipped <a href=\"http://aged-and-distilled.com/napkin/\">Napkin</a>, a Mac app for visual collaboration. Chris is also the co-host of The Record. He lives on <a href=\"http://www.ci.bainbridge-isl.wa.us/\">Bainbridge Island</a>, a quick ferry ride from Seattle.")
                .summary("Brent interviews Chris Parrish, co-host of The Record and one-half of Aged & Distilled.")
                .datePublished(ZonedDateTime.of(LocalDateTime.of(2014, 5, 9, 14, 04, 00), ZoneId.of("GMT")))
                .attachment(JsonFeedAttachment.builder()
                    .url("http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a")
                    .mimeType("audio/x-m4a")
                    .sizeInBytes(89970236L)
                    .durationInSeconds(6629)
                    .build())
                .build())
            .build();
    }
}
//end::class[]
