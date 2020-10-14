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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.rss.RssChannel;
import io.micronaut.rss.RssLanguage;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@JsonSerialize
public final class JsonFeedItem {

    @NotBlank
    @Nonnull
    private String id;

    @Nullable
    private String url;

    @Nullable
    private String externalUrl;

    @Nullable
    private String title;

    @Nullable
    private String contentHtml;

    @Nullable
    private String contentText;

    @Nullable
    private String summary;

    @Nullable
    private String image;

    @Nullable
    private String bannerImage;

    @Nullable
    private ZonedDateTime datePublished;

    @Nullable
    private ZonedDateTime dateModified;

    @Deprecated
    @Nullable
    private String author;

    @Nullable
    private List<JsonFeedAuthor> authors;

    @Nullable
    private List<String> tags;

    @Pattern(regexp = RssChannel.RSS_LANGUAGE_CODE_REGEX)
    @Nullable
    private RssLanguage language;

    @Nullable
    private List<JsonFeedAttachment> attachments;

    /**
     * @param id Id of the feed entry
     * @return A Builder to build a {@link JsonFeedItem}.
     */
    public static Builder builder(String id) {
        return new Builder(id);
    }

    /**
     * Unique id for that item for that feed over time. If an item is ever updated, the id should be
     * unchanged
     *
     * @return unique id of the item
     */
    public String getId() {
        return id;
    }

    /**
     * Unique id for that item for that feed over time. If an item is ever updated, the id should be
     * unchanged
     *
     * @param id unique id of the item
     */
    public void setId(@Nonnull String id) {
        this.id = id;
    }

    /**
     * The URL of the resource described by the item. It’s the permalink.
     *
     * @return the URL of the resource described by the item
     */
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
    public String getContentText() {
        return (contentText);
    }

    /**
     * The text content of the item in the feed.
     *
     * @param contentText the text content of the item in the feed
     */
    public void setContentText(@Nonnull String contentText) {
        this.contentText = contentText;
    }

    /**
     * Plain text sentence or two describing the item.
     *
     * @return plain text sentence or two describing the item
     */
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
    public ZonedDateTime getDatePublished() {
        return datePublished;
    }

    /**
     * Specifies the publishing date in RFC 3339.
     *
     * @param datePublished specifies the publishing date in RFC 3339
     */
    public void setDatePublished(@Nullable ZonedDateTime datePublished) {
        this.datePublished = datePublished;
    }

    /**
     * Specifies the last modified date in RFC 3339.
     *
     * @return specifies the last modified date in RFC 3339
     */
    public ZonedDateTime getDateModified() {
        return (dateModified);
    }

    /**
     * Specifies the last modified date in RFC 3339.
     *
     * @param dateModified specifies the last modified date in RFC 3339
     */
    public void setDateModified(@Nullable ZonedDateTime dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * The author of the feed.
     *
     * @return the author of the feed
     */
    @Deprecated
    public String getAuthor() {
        return (author);
    }

    /**
     * The author of the feed.
     *
     * @param author the author of the feed
     */
    public void setAuthor(@Nullable String author) {
        this.author = author;
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
    public void setAttachments(
        @Nullable List<JsonFeedAttachment> attachments) {
        this.attachments = attachments;
    }

    public void addAuthor(JsonFeedAuthor author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

    public void addAttachment(JsonFeedAttachment attachment) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<>();
        }
        this.attachments.add(attachment);
    }

    public void addTag(String tag) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tag);
    }

    public static final class Builder {

        private final JsonFeedItem feedItem = new JsonFeedItem();

        private Builder(String id) {
            id(id);
        }

        /**
         * Unique id for that item for that feed over time. If an item is ever updated, the id
         * should be unchanged
         *
         * @param id unique id of the item
         * @return the Builder
         */
        public Builder id(String id) {
            feedItem.setId(id);
            return this;
        }

        /**
         * The URL of the resource described by the item. It’s the permalink.
         *
         * @param url the URL of the resource described by the item
         * @return Builder
         */
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
        public Builder datePublished(ZonedDateTime datePublished) {
            feedItem.setDatePublished(datePublished);
            return this;
        }

        /**
         * Specifies the last modified date in RFC 3339.
         *
         * @param dateModified specifies the last modified date in RFC 3339
         * @return Builder
         */
        public Builder dateModified(ZonedDateTime dateModified) {
            feedItem.setDateModified(dateModified);
            return this;
        }

        /**
         * Adds an author to the feed.
         *
         * @param author An author of the feed
         * @return Builder
         */
        public Builder author(JsonFeedAuthor author) {
            feedItem.addAuthor(author);
            return this;
        }

        /**
         * The author of the feed.
         *
         * @param author the author of the feed
         * @return Builder
         */
        @Deprecated
        public Builder author(String author) {
            feedItem.setAuthor(author);
            return this;
        }

        /**
         * Associate a tag with the item.
         *
         * @param tag associate a tag with the item
         * @return Builder
         */
        public Builder tag(String tag) {
            feedItem.addTag(tag);
            return this;
        }

        /**
         * The primary language for the feed in the format specified in RFC 5646.
         *
         * @param language the primary language for the feed
         * @return Builder
         */
        public Builder language(RssLanguage language) {
            feedItem.setLanguage(language);
            return this;
        }

        /**
         * Attachments associated with the item.
         *
         * @param attachment attachments associated with the item
         * @return Builder
         */
        public Builder attachment(JsonFeedAttachment attachment) {
            feedItem.addAttachment(attachment);
            return this;
        }

        public JsonFeedItem build() {
            return feedItem;
        }
    }
}
