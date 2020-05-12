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

package io.micronaut.rss;

import io.micronaut.core.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link RssFeedRenderer}.
 * @author Sergio del Amo
 * @since 1.0
 */
@Singleton
public class DefaultRssFeedRenderer implements RssFeedRenderer {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultRssFeedRenderer.class);

    /**
     *
     * @param sw An XML Stream writer
     * @param localName The XML tag name
     * @param value The XML tag value
     */
    protected void writeElement(XMLStreamWriter sw, String localName, Object value) {
        try {
            sw.writeStartElement(localName);
            if (value instanceof String) {
                sw.writeCharacters((String) value);
            } else if (value instanceof ZonedDateTime) {
                DateTimeFormatter rfc822DateFormat = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z");
                String pubDate = ((ZonedDateTime) value).format(rfc822DateFormat);
                sw.writeCharacters(pubDate);
            }
            sw.writeEndElement();
        } catch (XMLStreamException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error(e.getMessage());
            }
        }
    }

    /**
     *
     * @param sw An XML Stream writer
     * @param rssItem An RSS Item
     */
    protected void writeRssItem(XMLStreamWriter sw, RssItem rssItem) {
        try {
            rssItem.getTitle().ifPresent(title -> writeElement(sw, "title", title));
            rssItem.getLink().ifPresent(link -> writeElement(sw, "link", link));
            rssItem.getDescription().ifPresent(description -> writeElement(sw, "description", description));
            rssItem.getAuthor().ifPresent(author -> writeElement(sw, "author", author));

            if (rssItem.getCategory().isPresent()) {
                for (String category : rssItem.getCategory().get()) {
                    writeElement(sw, "category", category);
                }
            }
            rssItem.getComments().ifPresent(comments -> writeElement(sw, "comments", comments));

            if (rssItem.getEnclosure().isPresent()) {
                RssItemEnclosure enclosure = rssItem.getEnclosure().get();
                sw.writeStartElement("enclosure");
                sw.writeAttribute("length", String.valueOf(enclosure.getLength()));
                sw.writeAttribute("type", enclosure.getType());
                sw.writeAttribute("url", enclosure.getUrl());
                sw.writeEndElement();
            }

            rssItem.getGuid().ifPresent(guid -> writeElement(sw, "guid", guid));

            rssItem.getPubDate().ifPresent(pubDate -> writeElement(sw, "pubDate", pubDate));

            rssItem.getSource().ifPresent(source -> writeElement(sw, "source", source));

        } catch (XMLStreamException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error(e.getMessage());
            }
        }
    }

    /**
     *
     * @return RSS Attributes
     */
    protected Map<String, String> getRssAttributes() {
        Map<String, String> m = new HashMap<>();
        m.put("version", "2.0");
        m.put("xmlns:content", "http://purl.org/rss/1.0/modules/content/");
        return m;
    }

    /**
     *
     * @param sw XML stream writter
     * @param categories the RSS Categories
     * @param tagName the XML tag
     */
    protected void writeCategory(XMLStreamWriter sw, List<String> categories, String tagName) {
        if (!categories.isEmpty()) {
            try {
                String category = categories.get(0);
                sw.writeStartElement(tagName);
                sw.writeAttribute("text", category);
                if (categories.size() > 1) {
                    writeCategory(sw, categories.subList(1, categories.size()), tagName);
                }
                sw.writeEndElement();

            } catch (XMLStreamException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage());
                }
            }
        }

    }

    /**
     *
     * @param sw An XML Stream writer
     * @param rssChannel An RSS Channel
     */
    protected void writeRssChannel(XMLStreamWriter sw, RssChannel rssChannel) {
        writeElement(sw, "title", rssChannel.getTitle());
        writeElement(sw, "link", rssChannel.getLink());
        if (rssChannel.getImage().isPresent()) {
            try {
                sw.writeStartElement("image");
                RssChannelImage image = rssChannel.getImage().get();
                writeElement(sw, "title", image.getTitle());
                writeElement(sw, "link", image.getLink());
                writeElement(sw, "url", image.getUrl());
                image.getWidth().ifPresent(width -> writeElement(sw, "width", width));
                image.getWidth().ifPresent(height -> writeElement(sw, "height", height));
                image.getDescription().ifPresent(description -> writeElement(sw, "description", description));
                sw.writeEndElement();
            } catch (XMLStreamException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage());
                }
            }
        }
        writeElement(sw, "description", rssChannel.getDescription());
        rssChannel.getLanguage().ifPresent(language -> writeElement(sw, "language", language.getLanguageCode()));
        rssChannel.getCopyright().ifPresent(copyright -> writeElement(sw, "copyright", copyright));
        rssChannel.getPubDate().ifPresent(pubDate -> writeElement(sw, "pubDate", pubDate));
        if (rssChannel.getCategory().isPresent()) {
            for (List<String> categoryList : rssChannel.getCategory().get()) {
                writeCategory(sw, categoryList, "category");
            }
        }

        if (rssChannel.getSkipHours().isPresent()) {
            try {
            sw.writeStartElement("skipHours");
            for (RssSkipHours skipHours : rssChannel.getSkipHours().get()) {
                writeElement(sw, "hour", String.valueOf(skipHours.getValue()));
            }
            sw.writeEndElement();
            } catch (XMLStreamException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage());
                }
            }
        }

        if (rssChannel.getSkipDays().isPresent()) {
            try {
                sw.writeStartElement("skipDays");
                for (RssSkipDays skipDay : rssChannel.getSkipDays().get()) {
                    writeElement(sw, "day", String.valueOf(skipDay.getDayName()));
                }
                sw.writeEndElement();
            } catch (XMLStreamException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage());
                }
            }
        }

        if (rssChannel.getTextInput().isPresent()) {
            RssTextInput textInput = rssChannel.getTextInput().get();
            try {
                sw.writeStartElement("textInput");
                writeElement(sw, "title", textInput.getTitle());
                writeElement(sw, "name", textInput.getName());
                writeElement(sw, "link", textInput.getLink());
                writeElement(sw, "description", textInput.getDescription());
                sw.writeEndElement();
            } catch (XMLStreamException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage());
                }
            }
        }

        if (rssChannel.getItem().isPresent()) {
            for (RssItem rssItem : rssChannel.getItem().get()) {
                try {
                    sw.writeStartElement("item");
                    writeRssItem(sw, rssItem);
                    sw.writeEndElement();
                } catch (XMLStreamException e) {
                    if (LOG.isErrorEnabled()) {
                        LOG.error(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     *
     * @param rssChannel The RSS Channel
     * @return The rendered RSS Channel
     */
    @Override
    public Writable render(RssChannel rssChannel) {
        return (writer) -> {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            try {
                final XMLStreamWriter sw = xmlOutputFactory.createXMLStreamWriter(writer);

                if (sw != null && rssChannel != null) {
                    sw.writeStartDocument("UTF-8", "1.0");
                    sw.writeStartElement("rss");
                    for (String key : getRssAttributes().keySet()) {
                        sw.writeAttribute(key, getRssAttributes().get(key));
                    }

                    sw.writeStartElement("channel");
                    writeRssChannel(sw, rssChannel);
                    sw.writeEndElement();

                    sw.writeEndElement();
                    sw.writeEndDocument();
                }
            } catch (XMLStreamException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage());
                }
            }
        };
    }
}
