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

package io.micronaut.rss.itunespodcast;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.rss.RssChannel;
import io.micronaut.rss.RssChannelImage;
import io.micronaut.rss.RssFeedProvider;
import io.micronaut.rss.RssItemEnclosure;
import io.micronaut.rss.RssLanguage;

import javax.inject.Singleton;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

/**
 * @see: https://help.apple.com/itc/podcasts_connect/#/itcbaf351599
 */
@Requires(env = Environment.TEST)
@Requires(property = "spec.name", value = "PodcastFeedControllerSpec")
//tag::class[]
@Singleton
public class DefaultRssFeedProvider implements RssFeedProvider {

    @Override
    public RssChannel fetch() {
        return ItunesPodcast.builder()
                .title("Hiking Treks")
                .link("https://www.apple.com/itunes/podcasts/")
                .language(RssLanguage.LANG_ENGLISH_UNITED_STATES)
                .copyright("&#8471; &amp; &#xA9; 2017 John Appleseed")
                .description("Love to get outdoors and discover nature&apos;s treasures? Hiking Treks is the show for you. We review hikes and excursions, review outdoor gear and interview a variety of naturalists and adventurers. Look for new episodes each week.")
                .subtitle("Find your trail. Great hikes and outdoor adventures.")
                .author("The Sunset Explorers")
                .subtitle("Find your trail. Great hikes and outdoor adventures.")
                .type(ItunesPodcastType.SERIAL)
                .owner(ItunesPodcastOwner.builder()
                        .name("Sunset Explorers")
                        .email("mountainscape@icloud.com")
                        .build())
                .image(RssChannelImage
                        .builder("Hiking Treks", "http://podcasts.apple.com/resources/example/hiking_treks/images/cover_art.jpg", "https://www.apple.com/itunes/podcasts/")
                        .build())
                .summary("Love to get outdoors and discover nature&apos;s treasures? Hiking Treks is the show for you. We review hikes and excursions, review outdoor gear and interview a variety of naturalists and adventurers. Look for new episodes each week.")
                .category(Arrays.asList(ItunesPodcastCategory.SPORTS_AND_RECREATION_OUTDOOR.getCategories()))
                .explict(false)
                .item(ItunesPodcastEpisode.builder("Hiking Treks Trailer")
                        .episodeType(ItunesPodcastEpisodeType.TRAILER)
                        .author("The Sunset Adventurers")
                        .subtitle("The Sunset Explorers share tips, techniques and recommendations for great hikes and adventures around the United States.")
                        .summary("The Sunset Explorers share tips, techniques and recommendations for great hikes and adventures around the United States.")
                        .description("The Sunset Explorers share tips, techniques and recommendations for great hikes and adventures around the United States.")
                        .contentEncoded("<![CDATA[The Sunset Explorers share tips, techniques and recommendations for great hikes and adventures around the United States. Listen on <a href=\"https://www.apple.com/itunes/podcasts/\">Apple Podcasts</a>]]>")
                        .enclosure(RssItemEnclosure.builder()
                                .length(498537)
                                .type("audio/mpeg")
                                .url("http://example.com/podcasts/everything/AllAboutEverythingEpisode4.mp3")
                                .build())
                        .guid("http://example.com/podcasts/archive/aae20160418.mp3")
                        .pubDate(ZonedDateTime.of(LocalDateTime.of(2016, 4, 12, 1, 15, 0), ZoneId.of("GMT")))
                        .duration("17:59")
                        .explicit(false)
                        .build())
                .item(ItunesPodcastEpisode.builder("Mt. Hood, Oregon")
                        .episodeType(ItunesPodcastEpisodeType.FULL)
                        .episode(4)
                        .season(2)
                        .title("S02 EP04 Mt. Hood, Oregon")
                        .author("The Sunset Explorers")
                        .subtitle("Tips for trekking around the tallest mountain in Oregon")
                        .summary("Tips for trekking around the tallest mountain in Oregon")
                        .description("Tips for trekking around the tallest mountain in Oregon")
                        .contentEncoded("Tips for trekking around the tallest mountain in Oregon")
                        .enclosure(RssItemEnclosure.builder()
                                .length(8727310)
                                .type("audio/x-m4a")
                                .url("http://example.com/podcasts/everything/mthood.m4a")
                                .build())
                        .guid("http://example.com/podcasts/archive/aae20170606.m4a")
                        .pubDate(ZonedDateTime.of(LocalDateTime.of(2016, 6, 06, 12, 0, 0), ZoneId.of("GMT")))
                        .duration("17:04")
                        .explicit(false)
                        .build())
                .item(ItunesPodcastEpisode.builder("Bouldering Around Boulder")
                        .episodeType(ItunesPodcastEpisodeType.FULL)
                        .episode(3)
                        .season(2)
                        .title("S02 EP03 Bouldering Around Boulder")
                        .author("The Sunset Adventurers")
                        .subtitle("We explore fun walks to climbing areas about the beautiful Colorado city of Boulder.")
                        .summary("We explore fun walks to climbing areas about the beautiful Colorado city of Boulder.")
                        .description("We explore fun walks to climbing areas about the beautiful Colorado city of Boulder.")
                        .image("http://example.com/podcasts/everything/AllAboutEverything/Episode2.jpg")
                        .contentEncoded("href=\"http://example.com/podcasts/everything/\"")
                        .enclosure(RssItemEnclosure.builder()
                                .length(5650889)
                                .type("video/mp4")
                                .url("http://example.com/podcasts/boulder.mp4")
                                .build())
                        .guid("http://example.com/podcasts/archive/aae20170530.mp4")
                        .pubDate(ZonedDateTime.of(LocalDateTime.of(2017, 5, 30, 13, 0, 0), ZoneId.of("GMT")))
                        .duration("06:27")
                        .explicit(false)
                        .build())
                .item(ItunesPodcastEpisode.builder("Caribou Mountain, Maine")
                        .episodeType(ItunesPodcastEpisodeType.FULL)
                        .episode(2)
                        .season(2)
                        .title("S02 EP02 Caribou Mountain, Maine")
                        .author("The Sunset Adventurers")
                        .subtitle("Put your fitness to the test with this invigorating hill climb.")
                        .summary("Put your fitness to the test with this invigorating hill climb.")
                        .description("Put your fitness to the test with this invigorating hill climb.")
                        .image("http://example.com/podcasts/everything/AllAboutEverything/Episode3.jpg")
                        .contentEncoded("href=\"http://example.com/podcasts/everything/\"")
                        .enclosure(RssItemEnclosure.builder()
                                .length(5650889)
                                .type("audio/x-m4v")
                                .url("http://example.com/podcasts/everything/caribou.m4v")
                                .build())
                        .guid("http://example.com/podcasts/archive/aae20170523.m4v")
                        .pubDate(ZonedDateTime.of(LocalDateTime.of(2017, 5, 23, 02, 0, 0), ZoneId.of("GMT")))
                        .duration("04:34")
                        .explicit(false)
                        .build())
                .item(ItunesPodcastEpisode.builder("Stawamus Chief")
                        .episodeType(ItunesPodcastEpisodeType.FULL)
                        .episode(1)
                        .season(2)
                        .title("S02 EP01 Stawamus Chief")
                        .author("The Sunset Adventurers")
                        .subtitle("We tackle Stawamus Chief outside of Vancouver, BC and you should too!")
                        .summary("We tackle Stawamus Chief outside of Vancouver, BC and you should too!")
                        .description("We tackle Stawamus Chief outside of Vancouver, BC and you should too!")
                        .contentEncoded("We tackle Stawamus Chief outside of Vancouver, BC and you should too!")
                        .enclosure(RssItemEnclosure.builder()
                                .length(498537)
                                .type("audio/mpeg")
                                .url("http://example.com/podcasts/everything/AllAboutEverythingEpisode4.mp3")
                                .build())
                        .guid("http://example.com/podcasts/archive/aae20170516.mp3")
                        .pubDate(ZonedDateTime.of(LocalDateTime.of(2017, 5, 16, 02, 0, 0), ZoneId.of("GMT")))
                        .duration("13:24")
                        .explicit(false)
                        .build())
                .item(ItunesPodcastEpisode.builder("Kuliouou Ridge Trail")
                        .episodeType(ItunesPodcastEpisodeType.FULL)
                        .episode(4)
                        .season(1)
                        .title("S01 EP04 Kuliouou Ridge Trail")
                        .author("The Sunset Adventurers")
                        .subtitle("Oahu, Hawaii, has some picturesque hikes and this is one of the best!")
                        .summary("Oahu, Hawaii, has some picturesque hikes and this is one of the best!")
                        .description("Oahu, Hawaii, has some picturesque hikes and this is one of the best!")
                        .contentEncoded("Oahu, Hawaii, has some picturesque hikes and this is one of the best!")
                        .enclosure(RssItemEnclosure.builder()
                                .length(498537)
                                .type("audio/mpeg")
                                .url("http://example.com/podcasts/everything/AllAboutEverythingEpisode4.mp3")
                                .build())
                        .guid("http://example.com/podcasts/archive/aae20160509.mp3")
                        .pubDate(ZonedDateTime.of(LocalDateTime.of(2017, 5, 10, 01, 15, 0), ZoneId.of("GMT")))
                        .duration("15:29")
                        .explicit(false)
                        .build())
                .item(ItunesPodcastEpisode.builder("Blood Mountain Loop")
                        .episodeType(ItunesPodcastEpisodeType.FULL)
                        .episode(3)
                        .season(1)
                        .title("S01 EP03 Blood Mountain Loop")
                        .author("The Sunset Adventurers")
                        .subtitle("Hiking the Appalachian Trail and Freeman Trail in Georgia")
                        .summary("Hiking the Appalachian Trail and Freeman Trail in Georgia")
                        .description("Hiking the Appalachian Trail and Freeman Trail in Georgia")
                        .contentEncoded("Hiking the Appalachian Trail and Freeman Trail in Georgia")
                        .enclosure(RssItemEnclosure.builder()
                                .length(498537)
                                .type("audio/mpeg")
                                .url("http://example.com/podcasts/everything/AllAboutEverythingEpisode4.mp3")
                                .build())
                        .guid("http://example.com/podcasts/archive/aae20160502.mp3")
                        .pubDate(ZonedDateTime.of(LocalDateTime.of(2017, 5, 3, 01, 15, 0), ZoneId.of("GMT")))
                        .duration("24:59")
                        .explicit(false)
                        .build())
                .item(ItunesPodcastEpisode.builder("Garden of the Gods Wilderness")
                        .episodeType(ItunesPodcastEpisodeType.FULL)
                        .episode(2)
                        .season(1)
                        .title("S01 EP02 Garden of the Gods Wilderness")
                        .author("The Sunset Adventurers")
                        .subtitle("Wilderness Area Garden of the Gods in Illinois is a delightful spot for an extended hike.")
                        .summary("Wilderness Area Garden of the Gods in Illinois is a delightful spot for an extended hike.")
                        .description("Wilderness Area Garden of the Gods in Illinois is a delightful spot for an extended hike.")
                        .contentEncoded("Wilderness Area Garden of the Gods in Illinois is a delightful spot for an extended hike.")
                        .enclosure(RssItemEnclosure.builder()
                                .length(498537)
                                .type("audio/mpeg")
                                .url("http://example.com/podcasts/everything/AllAboutEverythingEpisode4.mp3")
                                .build())
                        .guid("http://example.com/podcasts/archive/aae20160425.mp3")
                        .pubDate(ZonedDateTime.of(LocalDateTime.of(2017, 4, 26, 01, 15, 0), ZoneId.of("GMT")))
                        .duration("13:59")
                        .explicit(false)
                        .build())
                .item(ItunesPodcastEpisode.builder("Upper Priest Lake Trail to Continental Creek Trail")
                        .episodeType(ItunesPodcastEpisodeType.FULL)
                        .episode(1)
                        .season(1)
                        .title("S01 EP01 Upper Priest Lake Trail to Continental Creek Trail")
                        .author("The Sunset Adventurers")
                        .subtitle("We check out this powerfully scenic hike following the river in the Idaho Panhandle National Forests.")
                        .summary("We check out this powerfully scenic hike following the river in the Idaho Panhandle National Forests.")
                        .description("We check out this powerfully scenic hike following the river in the Idaho Panhandle National Forests.")
                        .contentEncoded("We check out this powerfully scenic hike following the river in the Idaho Panhandle National Forests.")
                        .enclosure(RssItemEnclosure.builder()
                                .length(498537)
                                .type("audio/mpeg")
                                .url("http://example.com/podcasts/everything/AllAboutEverythingEpisode4.mp3")
                                .build())
                        .guid("http://example.com/podcasts/archive/aae20160418a.mp3")
                        .pubDate(ZonedDateTime.of(LocalDateTime.of(2017, 4, 19, 01, 15, 0), ZoneId.of("GMT")))
                        .duration("23:59")
                        .explicit(false)
                        .contentEncoded("The Sunset Explorers share tips, techniques and recommendations for great hikes and adventures around the United States. Listen on <a href=\"https://www.apple.com/itunes/podcasts/\">Apple Podcasts</a>")
                        .build()).build();
    }

    @Override
    public RssChannel fetchById(Serializable id) {
        return null;
    }
}
//end::class[]