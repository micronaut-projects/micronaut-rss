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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import io.micronaut.rss.RssLanguage;
import java.io.IOException;
import java.io.Writer;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class DefaultJsonFeedRenderer implements JsonFeedRenderer {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultJsonFeedRenderer.class);
    private static final DateTimeFormatter RFC_822_DATE_FORMAT = DateTimeFormatter
        .ofPattern("EEE, dd MMM yyyy HH:mm:ss z");

    private void writeIfNonNull(JsonGenerator generator, String fieldName, Object fieldValue)
        throws IOException {
        if (Objects.nonNull(fieldValue)) {
            if (fieldValue instanceof String) {
                generator.writeStringField(fieldName, (String) fieldValue);
            } else if (fieldValue instanceof Integer) {
                generator.writeNumberField(fieldName, (Integer) fieldValue);
            } else if (fieldValue instanceof Long) {
                generator.writeNumberField(fieldName, (Long) fieldValue);
            } else if (fieldValue instanceof ZonedDateTime) {
                generator.writeStringField(fieldName,
                    ((ZonedDateTime) fieldValue).format(RFC_822_DATE_FORMAT));
            } else if (fieldValue instanceof RssLanguage) {
                generator.writeStringField(fieldName, ((RssLanguage) fieldValue).getLanguageCode());
            } else {
                throw new RuntimeException("Unknown field type");
            }
        }
    }

    private void writeAuthors(JsonGenerator generator, List<JsonFeedAuthor> authors)
        throws IOException {
        if (Objects.isNull(authors) || authors.isEmpty() || authors.stream()
            .allMatch(JsonFeedAuthor::isEmpty)) {
            return;
        }

        generator.writeArrayFieldStart("authors");
        for (JsonFeedAuthor author : authors) {
            if (!author.isEmpty()) {
                generator.writeStartObject();
                writeIfNonNull(generator, "name", author.getName());
                writeIfNonNull(generator, "url", author.getUrl());
                writeIfNonNull(generator, "avatar", author.getAvatar());
                generator.writeEndObject();
            }
        }
        generator.writeEndArray();
    }

    private void writeAttachments(JsonGenerator generator, List<JsonFeedAttachment> attachments)
        throws IOException {
        if (Objects.isNull(attachments) || attachments.isEmpty()) {
            return;
        }
        generator.writeArrayFieldStart("attachments");
        for (JsonFeedAttachment attachment : attachments) {
            generator.writeStartObject();
            generator.writeStringField("url", attachment.getUrl());
            generator.writeStringField("mime_type", attachment.getMimeType());
            writeIfNonNull(generator, "title", attachment.getTitle());
            writeIfNonNull(generator, "size_in_bytes", attachment.getSizeInBytes());
            writeIfNonNull(generator, "duration_in_seconds", attachment.getDurationInSeconds());
            generator.writeEndObject();
        }
        generator.writeEndArray();
    }

    private void writeItems(JsonGenerator generator, List<JsonFeedItem> items) throws IOException {
        if (Objects.isNull(items) || items.isEmpty()) {
            return;
        }

        generator.writeArrayFieldStart("items");
        for (JsonFeedItem item : items) {
            generator.writeStartObject();
            generator.writeStringField("id", item.getId());
            writeIfNonNull(generator, "url", item.getUrl());
            writeIfNonNull(generator, "external_url", item.getExternalUrl());
            writeIfNonNull(generator, "title", item.getTitle());
            writeIfNonNull(generator, "content_html", item.getContentHtml());
            writeIfNonNull(generator, "content_text", item.getContentText());
            writeIfNonNull(generator, "summary", item.getSummary());
            writeIfNonNull(generator, "image", item.getImage());
            writeIfNonNull(generator, "banner_image", item.getBannerImage());
            writeIfNonNull(generator, "date_published",
                item.getDatePublished());
            writeIfNonNull(generator, "date_modified",
                item.getDateModified());

            writeIfNonNull(generator, "author", item.getAuthor());
            writeAuthors(generator, item.getAuthors());

            List<String> tags = item.getTags();
            if (Objects.nonNull(tags) && !tags.isEmpty()) {
                generator.writeArrayFieldStart("tags");
                for (String tag : tags) {
                    generator.writeString(tag);
                }
                generator.writeEndArray();
            }
            writeIfNonNull(generator, "language", item.getLanguage());
            writeAttachments(generator, item.getAttachments());
            generator.writeEndObject();
        }
        generator.writeEndArray();
    }

    private void writeHubs(JsonGenerator generator, List<JsonHub> hubs) throws IOException {
        if (Objects.isNull(hubs) || hubs.isEmpty()) {
            return;
        }
        generator.writeArrayFieldStart("hubs");
        for (JsonHub hub : hubs) {
            generator.writeStartObject();
            generator.writeStringField("url", hub.getUrl());
            generator.writeStringField("type", hub.getType());
            generator.writeEndObject();
        }
        generator.writeEndArray();
    }

    private void writeChannel(JsonGenerator generator, JsonFeed jsonFeed) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("version", jsonFeed.getVersion());
        generator.writeStringField("title", jsonFeed.getTitle());
        writeIfNonNull(generator, "home_page_url", jsonFeed.getHomePageUrl());
        writeIfNonNull(generator, "feed_url", jsonFeed.getFeedUrl());
        writeIfNonNull(generator, "description", jsonFeed.getDescription());
        writeIfNonNull(generator, "user_comment", jsonFeed.getUserComment());
        writeIfNonNull(generator, "next_url", jsonFeed.getNextURL());
        writeIfNonNull(generator, "icon", jsonFeed.getIcon());
        writeIfNonNull(generator, "favicon", jsonFeed.getFavicon());

        writeAuthors(generator, jsonFeed.getAuthors());
        writeIfNonNull(generator, "author", jsonFeed.getAuthor());

        writeItems(generator, jsonFeed.getItems());

        writeIfNonNull(generator, "language", jsonFeed.getLanguage());
        writeIfNonNull(generator, "expired", jsonFeed.getExpired());

        writeHubs(generator, jsonFeed.getHubs());

        generator.writeEndObject();
    }

    @Override
    public void render(Writer writer, JsonFeed jsonFeed) {
        try {
            JsonFactory jsonFactory = new JsonFactory();
            jsonFactory.configure(Feature.AUTO_CLOSE_TARGET, false);
            JsonGenerator generator = jsonFactory.createGenerator(writer);

            writeChannel(generator, jsonFeed);
            generator.close();
        } catch (Exception e) {
            if (LOG.isErrorEnabled()) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}
