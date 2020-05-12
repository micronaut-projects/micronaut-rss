/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.micronaut.rss.itunespodcast;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.rss.RssChannel;
import io.micronaut.rss.DefaultRssFeedRenderer;
import io.micronaut.rss.RssItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.List;
import java.util.Map;

/**
 * {@link io.micronaut.rss.RssFeedRenderer} implementation for generating iTunes Podcast RSS 2.0 Feed.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Replaces(DefaultRssFeedRenderer.class)
@Singleton
public class DefaultItunesPodcastRenderer extends DefaultRssFeedRenderer {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultItunesPodcastRenderer.class);

    @Override
    protected Map<String, String> getRssAttributes() {
        Map<String, String> m = super.getRssAttributes();
        m.put("xmlns:itunes", "http://www.itunes.com/dtds/podcast-1.0.dtd");
        return m;
    }

    @Override
    protected void writeRssChannel(XMLStreamWriter sw, RssChannel rssChannel) {
        super.writeRssChannel(sw, rssChannel);
        if (rssChannel instanceof ItunesPodcast) {
            ItunesPodcast itunesPodcast = (ItunesPodcast) rssChannel;

            itunesPodcast.getSummary().ifPresent(summary -> writeElement(sw, "itunes:summary", summary));
            itunesPodcast.getAuthor().ifPresent(author -> writeElement(sw, "itunes:author", author));
            itunesPodcast.getSubtitle().ifPresent(subtitle -> writeElement(sw, "itunes:subtitle", subtitle));
            itunesPodcast.getType().ifPresent(type -> writeElement(sw, "itunes:type", type.toString().toLowerCase()));

            try {
                sw.writeStartElement("itunes:owner");
                writeElement(sw, "itunes:name", itunesPodcast.getOwner().getName());
                writeElement(sw, "itunes:email", itunesPodcast.getOwner().getEmail());
                sw.writeEndElement();
            } catch (XMLStreamException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage());
                }
            }

            if (itunesPodcast.getImage().isPresent()) {
                String imageUrl = itunesPodcast.getImage().get().getUrl();
                writeItunesImage(sw, imageUrl);
            }

            if (itunesPodcast.getCategory().isPresent()) {
                for (List<String> categoryList : itunesPodcast.getCategory().get()) {
                    writeCategory(sw, categoryList, "itunes:category");
                }
            }
            itunesPodcast.getKeywords()
                    .stream()
                    .reduce((a, b) -> a + ", " + b).ifPresent(keywords -> writeElement(sw, "itunes:keywords", keywords));

            writeElement(sw, "itunes:explicit", itunesPodcast.isExplict() ? "yes" : "no");
            writeElement(sw, "itunes:block", itunesPodcast.shouldBlock() ? "yes" : "no");
        }
    }

    /**
     * Writes the image url in an itunes:image tag.
     * @param sw XML straem writer
     * @param imageUrl the Image URL
     */
    protected void writeItunesImage(XMLStreamWriter sw, String imageUrl) {
        try {
            sw.writeStartElement("itunes:image");
            sw.writeAttribute("href", imageUrl);
            sw.writeEndElement();
        } catch (XMLStreamException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error(e.getMessage());
            }
        }
    }

    @Override
    protected void writeRssItem(XMLStreamWriter sw, RssItem rssItem) {
        super.writeRssItem(sw, rssItem);
        if (rssItem instanceof ItunesPodcastEpisode) {
            ItunesPodcastEpisode itunesPodcastEpisode = (ItunesPodcastEpisode) rssItem;

            itunesPodcastEpisode.getEpisodeType().ifPresent(episodeType -> writeElement(sw, "itunes:episodeType", episodeType.toString().toLowerCase()));

            itunesPodcastEpisode.getTitle().ifPresent(title -> writeElement(sw, "itunes:title", title));
            itunesPodcastEpisode.getSubtitle().ifPresent(subtitle -> writeElement(sw, "itunes:subtitle", subtitle));
            itunesPodcastEpisode.getAuthor().ifPresent(author -> writeElement(sw, "itunes:author", author));
            itunesPodcastEpisode.getSummary().ifPresent(summary -> writeElement(sw, "itunes:summary", summary));
            itunesPodcastEpisode.getContentEncoded().ifPresent(contentEncode -> {
                writeElement(sw, "content:encoded", contentEncode);
            });

            itunesPodcastEpisode.getDuration().ifPresent(author -> writeElement(sw, "itunes:duration", author));
            itunesPodcastEpisode.getEpisode().ifPresent(episode -> writeElement(sw, "itunes:episode", String.valueOf(episode)));
            itunesPodcastEpisode.getSeason().ifPresent(season -> writeElement(sw, "itunes:season", String.valueOf(season)));
            //itunesPodcastEpisode.getImage().ifPresent(image -> writeItunesImage(sw, image));
            writeElement(sw, "itunes:explicit", itunesPodcastEpisode.isExplict() ? "yes" : "no");

        }
    }
}
