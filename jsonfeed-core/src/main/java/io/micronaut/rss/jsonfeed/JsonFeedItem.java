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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.rss.language.RssLanguage;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Object in a JSON Feed.
 * @see <a href="https://jsonfeed.org/version/1.1">JSON Feed 1.1</a>
 * @author Sergio del Amo, mark1626
 * @since 2.2.0
 */
@Introspected
public class JsonFeedItem {
    public static final String KEY_ID = "id";
    public static final String KEY_URL = "url";
    public static final String KEY_EXTERNAL_URL = "external_url";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT_HTML = "content_html";
    public static final String KEY_CONTENT_TEXT = "content_text";
    public static final String KEY_SUMMARY = "summary";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_BANNER_IMAGE = "banner_image";
    public static final String KEY_DATE_PUBLISHED = "date_published";
    public static final String KEY_DATE_MODIFIED = "date_modified";
    public static final String KEY_AUTHORS = "authors";
    public static final String KEY_TAGS = "tags";
    public static final String KEY_LANGUAGE = "language";
    public static final String KEY_ATTACHMENTS = "attachments";

    @NotBlank
    @NonNull
    private String id;

    @Nullable
    private String url;

    @JsonProperty(KEY_EXTERNAL_URL)
    @Nullable
    private String externalUrl;

    @Nullable
    private String title;

    @Nullable
    @JsonProperty(KEY_CONTENT_HTML)
    private String contentHtml;

    @JsonProperty(KEY_CONTENT_TEXT)
    @Nullable
    private String contentText;

    @Nullable
    private String summary;

    @Nullable
    private String image;

    @JsonProperty(KEY_BANNER_IMAGE)
    @Nullable
    private String bannerImage;

    /**
     * Date in RFC 3339 format.
     */
    @Nullable
    @JsonProperty(KEY_DATE_PUBLISHED)
    private String datePublished;

    /**
     * Date in RFC 3339 format.
     */
    @Nullable
    @JsonProperty(KEY_DATE_MODIFIED)
    private String dateModified;

    @Nullable
    private List<@Valid JsonFeedAuthor> authors;

    @Nullable
    private List<String> tags;

    @Nullable
    private RssLanguage language;

    @Nullable
    private List<JsonFeedAttachment> attachments;

    public JsonFeedItem() {
    }

    /**
     * @return A Builder to build a {@link JsonFeedItem}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * @param id Id of the feed entry
     * @return A Builder to build a {@link JsonFeedItem}.
     */
    public static Builder builder(String id) {
        return new Builder().id(id);
    }

    /**
     * Unique id for that item for that feed over time. If an item is ever updated, the id should be
     * unchanged
     *
     * @return unique id of the item
     */
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Unique id for that item for that feed over time. If an item is ever updated, the id should be
     * unchanged
     *
     * @param id unique id of the item
     */
    public void setId(@NonNull String id) {
        this.id = id;
    }

    /**
     * The URL of the resource described by the item. It’s the permalink.
     *
     * @return the URL of the resource described by the item
     */
    @Nullable
    public String getUrl() {
        return (url);
    }

    /**
     * The URL of the resource described by the item. It’s the permalink.
     *
     * @param url the URL of the resource described by the item
     */
    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    /**
     * The URL of a page elsewhere. This is especially useful for linkblogs
     *
     * @return the URL of a page
     */
    @Nullable
    public String getExternalUrl() {
        return externalUrl;
    }

    /**
     * The URL of a page elsewhere. This is especially useful for linkblogs
     *
     * @param externalUrl the URL of a page
     */
    public void setExternalUrl(@Nullable String externalUrl) {
        this.externalUrl = externalUrl;
    }


    /**
     * Title of the item in feed.
     *
     * @return title of the item in feed
     */
    @Nullable
    public String getTitle() {
        return title;
    }

    /**
     * Title of the item in feed.
     *
     * @param title title of the item in feed
     */
    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    /**
     * The html content of the item in the feed.
     *
     * @return the html content of the item in the feed
     */
    @Nullable
    public String getContentHtml() {
        return (contentHtml);
    }

    /**
     * The html content of the item in the feed.
     *
     * @param contentHtml the html content of the item in the feed
     */
    public void setContentHtml(@Nullable String contentHtml) {
        this.contentHtml = contentHtml;
    }

    /**
     * The text content of the item in the feed.
     *
     * @return the text content of the item in the feed
     */
    @Nullable
    public String getContentText() {
        return (contentText);
    }

    /**
     * The text content of the item in the feed.
     *
     * @param contentText the text content of the item in the feed
     */
    public void setContentText(@Nullable String contentText) {
        this.contentText = contentText;
    }

    /**
     * Plain text sentence or two describing the item.
     *
     * @return plain text sentence or two describing the item
     */
    @Nullable
    public String getSummary() {
        return (summary);
    }

    /**
     * Plain text sentence or two describing the item.
     *
     * @param summary plain text sentence or two describing the item
     */
    public void setSummary(@Nullable String summary) {
        this.summary = summary;
    }

    /**
     * The URL of the main image for the item.
     *
     * @return the URL of the main image for the item
     */
    @Nullable
    public String getImage() {
        return (image);
    }

    /**
     * The URL of the main image for the item.
     *
     * @param image the URL of the main image for the item
     */
    public void setImage(@Nullable String image) {
        this.image = image;
    }

    /**
     * The URL of an image to use as a banner.
     *
     * @return the URL of an image to use as a banner
     */
    @Nullable
    public String getBannerImage() {
        return (bannerImage);
    }

    /**
     * The URL of an image to use as a banner.
     *
     * @param bannerImage the URL of an image to use as a banner
     */
    public void setBannerImage(@Nullable String bannerImage) {
        this.bannerImage = bannerImage;
    }

    /**
     * Specifies the publishing date in RFC 3339.
     *
     * @return specifies the publishing date in RFC 3339
     */
    @Nullable
    public String getDatePublished() {
        return datePublished;
    }

    /**
     * Specifies the publishing date in RFC 3339.
     *
     * @param datePublished specifies the publishing date in RFC 3339
     */
    public void setDatePublished(@Nullable String datePublished) {
        this.datePublished = datePublished;
    }

    /**
     * Specifies the last modified date in RFC 3339.
     *
     * @return specifies the last modified date in RFC 3339
     */
    @Nullable
    public String getDateModified() {
        return (dateModified);
    }

    /**
     * Specifies the last modified date in RFC 3339.
     *
     * @param dateModified specifies the last modified date in RFC 3339
     */
    public void setDateModified(@Nullable String dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * List of authors of the item.
     *
     * @return list of authors of the item
     */
    @Nullable
    public List<JsonFeedAuthor> getAuthors() {
        return authors;
    }

    /**
     * List of authors of the item.
     *
     * @param authors list of authors of the item
     */
    public void setAuthors(@Nullable List<JsonFeedAuthor> authors) {
        this.authors = authors;
    }

    /**
     * Tags associated with the item.
     *
     * @return tags associated with the item
     */
    @Nullable
    public List<String> getTags() {
        return tags;
    }

    /**
     * Tags associated with the item.
     *
     * @param tags associated tags with the item
     */
    public void setTags(@Nullable List<String> tags) {
        this.tags = tags;
    }

    /**
     * The primary language for the item in the format specified in RFC 5646.
     *
     * @return the primary language for the feed
     */
    @Nullable
    public RssLanguage getLanguage() {
        return language;
    }

    /**
     * The primary language for the item in the format specified in RFC 5646.
     *
     * @param language the primary language for the feed
     */
    public void setLanguage(@Nullable RssLanguage language) {
        this.language = language;
    }

    /**
     * Attachments associated with the item.
     *
     * @return attachments associated with the item
     */
    @Nullable
    public List<JsonFeedAttachment> getAttachments() {
        return attachments;
    }

    /**
     * Attachments associated with the item.
     *
     * @param attachments attachments associated with the item
     */
    public void setAttachments(@Nullable List<JsonFeedAttachment> attachments) {
        this.attachments = attachments;
    }

    /**
     *
     * @param author JSON Feed Item's Author
     */
    public void addAuthor(@NonNull JsonFeedAuthor author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

    /**
     *
     * @param attachment JSON Feed Item's Attachment
     */
    public void addAttachment(JsonFeedAttachment attachment) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<>();
        }
        this.attachments.add(attachment);
    }

    /**
     *
     * @param tag JSON Feed Item's tag
     */
    public void addTag(@NonNull String tag) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tag);
    }

    /**
     *
     * @return Map representation of object.
     */
    @NonNull
    public Map<String, Object> toMap() {
        Map<String, Object> m = new HashMap<>();
        if (getId() != null) {
            m.put(KEY_ID, getId());
        }
        if (getUrl() != null) {
            m.put(KEY_URL, getUrl());
        }
        if (getExternalUrl() != null) {
            m.put(KEY_EXTERNAL_URL, getExternalUrl());
        }
        if (getTitle() != null) {
            m.put(KEY_TITLE, getTitle());
        }
        if (getContentHtml() != null) {
            m.put(KEY_CONTENT_HTML, getContentHtml());
        }
        if (getContentText() != null) {
            m.put(KEY_CONTENT_TEXT, getContentText());
        }
        if (getSummary() != null) {
            m.put(KEY_SUMMARY, getSummary());
        }
        if (getImage() != null) {
            m.put(KEY_IMAGE, getImage());
        }
        if (getBannerImage() != null) {
            m.put(KEY_BANNER_IMAGE, getBannerImage());
        }
        if (getDatePublished() != null) {
            m.put(KEY_DATE_PUBLISHED, getDatePublished());
        }
        if (getDateModified() != null) {
            m.put(KEY_DATE_MODIFIED, getDateModified());
        }
        if (getAuthors() != null) {
            m.put(KEY_AUTHORS, getAuthors().stream().map(JsonFeedAuthor::toMap).collect(Collectors.toList()));
        }
        if (getTags() != null) {
            m.put(KEY_TAGS, getTags());
        }
        if (getLanguage() != null) {
            m.put(KEY_LANGUAGE, getLanguage().getLanguageCode());
        }
        if (getAttachments() != null) {
            m.put(KEY_ATTACHMENTS, getAttachments().stream().map(JsonFeedAttachment::toMap).collect(Collectors.toList()));
        }
        return m;
    }

    /**
     * JSON Feed Item Builder.
     */
    public static final class Builder {
        private static final String RFC_822_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";
        private static final DateTimeFormatter RFC_822_DATE_FORMAT = DateTimeFormatter.ofPattern(RFC_822_PATTERN);

        private final JsonFeedItem feedItem = new JsonFeedItem();

        /**
         * JSON Feed Item Builder Constructor.
         */
        private Builder() {
        }

        /**
         *
         * @param id unique for the item for that feed over time.
         * @return The Builder
         */
        @NonNull
        public Builder id(@NonNull String id) {
            this.feedItem.setId(id);
            return this;
        }

        /**
         * The URL of the resource described by the item. It’s the permalink.
         *
         * @param url the URL of the resource described by the item
         * @return Builder
         */
        @NonNull
        public Builder url(String url) {
            feedItem.setUrl(url);
            return this;
        }

        /**
         * The URL of a page elsewhere. This is especially useful for linkblogs
         *
         * @param externalUrl the URL of a page
         * @return Builder
         */
        @NonNull
        public Builder externalUrl(String externalUrl) {
            feedItem.setExternalUrl(externalUrl);
            return this;
        }

        /**
         * Title of the item in feed.
         *
         * @param title title of the item in feed
         * @return Builder
         */
        @NonNull
        public Builder title(String title) {
            feedItem.setTitle(title);
            return this;
        }

        /**
         * The html content of the item in the feed.
         *
         * @param contentHtml the html content of the item in the feed
         * @return Builder
         */
        @NonNull
        public Builder contentHtml(String contentHtml) {
            feedItem.setContentHtml(contentHtml);
            return this;
        }

        /**
         * The text content of the item in the feed.
         *
         * @param contentText the text content of the item in the feed
         * @return Builder
         */
        @NonNull
        public Builder contentText(String contentText) {
            feedItem.setContentText(contentText);
            return this;
        }

        /**
         * Plain text sentence or two describing the item.
         *
         * @param summary plain text sentence or two describing the item
         * @return Builder
         */
        @NonNull
        public Builder summary(String summary) {
            feedItem.setSummary(summary);
            return this;
        }

        /**
         * The URL of the main image for the item.
         *
         * @param image the URL of the main image for the item
         * @return Builder
         */
        @NonNull
        public Builder image(String image) {
            feedItem.setImage(image);
            return this;
        }

        /**
         * The URL of an image to use as a banner.
         *
         * @param bannerImage the URL of an image to use as a banner
         * @return Builder
         */
        @NonNull
        public Builder bannerImage(String bannerImage) {
            feedItem.setBannerImage(bannerImage);
            return this;
        }

        /**
         * Specifies the publishing date in RFC 3339.
         *
         * @param datePublished specifies the publishing date in RFC 3339
         * @return Builder
         */
        @NonNull
        public Builder datePublished(ZonedDateTime datePublished) {
            feedItem.setDatePublished(RFC_822_DATE_FORMAT.format(datePublished));
            return this;
        }

        /**
         * Specifies the publishing date in RFC 3339.
         *
         * @param datePublished specifies the publishing date in RFC 3339
         * @return Builder
         */
        @NonNull
        public Builder datePublished(String datePublished) {
            feedItem.setDatePublished(datePublished);
            return this;
        }

        /**
         * Specifies the last modified date in RFC 3339.
         *
         * @param dateModified specifies the last modified date in RFC 3339
         * @return Builder
         */
        @NonNull
        public Builder dateModified(String dateModified) {
            feedItem.setDateModified(dateModified);
            return this;
        }

        /**
         * Specifies the last modified date in RFC 3339.
         *
         * @param dateModified specifies the last modified date in RFC 3339
         * @return Builder
         */
        @NonNull
        public Builder dateModified(ZonedDateTime dateModified) {
            feedItem.setDateModified(RFC_822_DATE_FORMAT.format(dateModified));
            return this;
        }

        /**
         * Adds an author to the feed.
         *
         * @param author An author of the feed
         * @return Builder
         */
        @NonNull
        public Builder author(JsonFeedAuthor author) {
            feedItem.addAuthor(author);
            return this;
        }

        /**
         * Associate a tag with the item.
         *
         * @param tag associate a tag with the item
         * @return Builder
         */
        @NonNull
        public Builder tag(@NonNull String tag) {
            feedItem.addTag(tag);
            return this;
        }

        /**
         * The primary language for the feed in the format specified in RFC 5646.
         *
         * @param language the primary language for the feed
         * @return Builder
         */
        @NonNull
        public Builder language(@NonNull RssLanguage language) {
            feedItem.setLanguage(language);
            return this;
        }

        /**
         * Attachments associated with the item.
         *
         * @param attachment attachments associated with the item
         * @return Builder
         */
        @NonNull
        public Builder attachment(@NonNull JsonFeedAttachment attachment) {
            feedItem.addAttachment(attachment);
            return this;
        }

        @NonNull
        public JsonFeedItem build() {
            return feedItem;
        }
    }
}
