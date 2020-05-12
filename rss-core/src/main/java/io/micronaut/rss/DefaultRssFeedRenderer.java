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

import edu.umd.cs.findbugs.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.Writer;
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
    private static final String LOWER_THAN = "<";
    private static final String DESCRIPTION = "description";
    private static final String CATEGORY = "category";
    private static final String LANGUAGE = "language";
    private static final String COPYRIGHT = "copyright";
    private static final String PUB_DATE = "pubDate";
    private static final String HEIGHT = "height";
    private static final String WIDTH = "width";
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String IMAGE = "image";
    private static final String URL = "url";
    private static final String SKIP_HOURS = "skipHours";
    private static final String HOUR = "hour";
    private static final String SKIP_DAYS = "skipDays";
    private static final String DAY = "day";
    private static final String TEXT_INPUT = "textInput";
    private static final String NAME = "name";
    private static final String ITEM = "item";
    private static final String RSS = "rss";
    private static final String CHANNEL = "channel";

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
    protected void writeRssItemDescription(XMLStreamWriter sw, RssItem rssItem) {
        rssItem.getDescription().ifPresent(description -> {
            if (shouldWrapDescriptionWithCData(description)) {
                try {
                    sw.writeStartElement(DESCRIPTION);
                    sw.writeCData(description);
                    sw.writeEndElement();
                } catch (XMLStreamException e) {
                    if (LOG.isErrorEnabled()) {
                        LOG.error(e.getMessage());
                    }
                }
            } else {
                writeElement(sw, DESCRIPTION, description);
            }
        });
    }

    /**
     *
     * @param description RSS Item description
     * @return Whether description should be wrapped with <![CDATA[ ]]
     */
    protected boolean shouldWrapDescriptionWithCData(@NonNull String description) {
        return description.contains(LOWER_THAN);
    }

    /**
     *
     * @param sw An XML Stream writer
     * @param rssItem An RSS Item
     */
    protected void writeRssItem(XMLStreamWriter sw, RssItem rssItem) {
        try {
            rssItem.getTitle().ifPresent(title -> writeElement(sw, TITLE, title));
            rssItem.getLink().ifPresent(link -> writeElement(sw, "link", link));
            writeRssItemDescription(sw, rssItem);
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
        writeElement(sw, TITLE, rssChannel.getTitle());
        writeElement(sw, LINK, rssChannel.getLink());
        if (rssChannel.getImage().isPresent()) {
            try {
                sw.writeStartElement(IMAGE);
                RssChannelImage image = rssChannel.getImage().get();
                writeElement(sw, TITLE, image.getTitle());
                writeElement(sw, LINK, image.getLink());
                writeElement(sw, URL, image.getUrl());
                image.getWidth().ifPresent(width -> writeElement(sw, WIDTH, width));
                image.getWidth().ifPresent(height -> writeElement(sw, HEIGHT, height));
                image.getDescription().ifPresent(description -> writeElement(sw, DESCRIPTION, description));
                sw.writeEndElement();
            } catch (XMLStreamException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage());
                }
            }
        }
        writeElement(sw, DESCRIPTION, rssChannel.getDescription());
        rssChannel.getLanguage().ifPresent(language -> writeElement(sw, LANGUAGE, language.getLanguageCode()));
        rssChannel.getCopyright().ifPresent(copyright -> writeElement(sw, COPYRIGHT, copyright));
        rssChannel.getPubDate().ifPresent(pubDate -> writeElement(sw, PUB_DATE, pubDate));
        if (rssChannel.getCategory().isPresent()) {
            for (List<String> categoryList : rssChannel.getCategory().get()) {
                writeCategory(sw, categoryList, CATEGORY);
            }
        }

        if (rssChannel.getSkipHours().isPresent()) {
            try {
            sw.writeStartElement(SKIP_HOURS);
            for (RssSkipHours skipHours : rssChannel.getSkipHours().get()) {
                writeElement(sw, HOUR, String.valueOf(skipHours.getValue()));
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
                sw.writeStartElement(SKIP_DAYS);
                for (RssSkipDays skipDay : rssChannel.getSkipDays().get()) {
                    writeElement(sw, DAY, String.valueOf(skipDay.getDayName()));
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
                sw.writeStartElement(TEXT_INPUT);
                writeElement(sw, TITLE, textInput.getTitle());
                writeElement(sw, NAME, textInput.getName());
                writeElement(sw, LINK, textInput.getLink());
                writeElement(sw, DESCRIPTION, textInput.getDescription());
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
                    sw.writeStartElement(ITEM);
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

    @Override
    public void render(Writer writer, RssChannel rssChannel) {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            try {
                final XMLStreamWriter sw = xmlOutputFactory.createXMLStreamWriter(writer);

                if (sw != null && rssChannel != null) {
                    sw.writeStartDocument("UTF-8", "1.0");
                    sw.writeStartElement(RSS);
                    for (String key : getRssAttributes().keySet()) {
                        sw.writeAttribute(key, getRssAttributes().get(key));
                    }

                    sw.writeStartElement(CHANNEL);
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
    }
}
