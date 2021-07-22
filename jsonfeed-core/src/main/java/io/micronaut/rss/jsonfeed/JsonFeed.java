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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * A JSON Feed is a list that may change over time, and the individual items in the list may change..
 * @see <a href="https://jsonfeed.org/version/1.1">JSON Feed 1.1</a>
 * @author Sergio del Amo, mark1626
 * @since 2.2.0
 */
@Introspected
public class JsonFeed {

    public static final String VERSION_JSON_FEED_1 = "https://jsonfeed.org/version/1";
    public static final String VERSION_JSON_FEED_1_1 = "https://jsonfeed.org/version/1.1";

    public static final String KEY_VERSION = "version";
    public static final String KEY_TITLE = "title";
    public static final String KEY_HOME_PAGE_URL = "home_page_url";
    public static final String KEY_FEED_URL = "feed_url";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_USER_COMMENT = "user_comment";
    public static final String KEY_NEXT_URL = "next_url";
    public static final String KEY_ICON = "icon";
    public static final String KEY_FAVICON = "favicon";
    public static final String KEY_AUTHORS = "authors";
    public static final String KEY_ITEMS = "items";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_LANGUAGE = "language";
    public static final String KEY_EXPIRED = "expired";
    public static final String KEY_HUBS = "hubs";

    /**
     * The URL of the version of the format the feed uses.
     */
    @NotBlank
    @NonNull
    private String version;

    /**
     * The name of the feed, which will often correspond to the name of the website (blog, for instance), though not necessarily.
     */
    @NotBlank
    @NonNull
    private String title;

    /**
     * he URL of the resource that the feed describes. This resource may or may not actually be a “home” page, but it should be an HTML page. If a feed is published on the public web, this should be considered as required. But it may not make sense in the case of a file created on a desktop computer, when that file is not shared or is shared only privately.
     */
    @Nullable
    @JsonProperty(KEY_HOME_PAGE_URL)
    private String homePageUrl;

    /**
     * The URL of the feed, and serves as the unique identifier for the feed. As with home_page_url, this should be considered required for feeds on the public web.
     */
    @JsonProperty(KEY_FEED_URL)
    @Nullable
    private String feedUrl;

    /**
     * Provides more detail, beyond the title, on what the feed is about. A feed reader may display this text.
     */
    @Nullable
    private String description;

    /**
     * This is a description of the purpose of the feed. This is for the use of people looking at the raw JSON, and should be ignored by feed readers.
     */
    @JsonProperty(KEY_USER_COMMENT)
    @Nullable
    private String userComment;

    /**
     * The URL of a feed that provides the next n items, where n is determined by the publisher.
     * This allows for pagination, but with the expectation that reader software is not required to use it and probably won’t use it very often
     */
    @JsonProperty(KEY_NEXT_URL)
    @Nullable
    private String nextURL;

    /**
     * The URL of an image for the feed suitable to be used in a timeline, much the way an avatar might be used. It should be square and relatively large — such as 512 x 512 pixels — so that it can be scaled-down and so that it can look good on retina displays. It should use transparency where appropriate, since it may be rendered on a non-white background.
     */
    @Nullable
    private String icon;

    /**
     * The URL of an image for the feed suitable to be used in a source list. It should be square and relatively small, but not smaller than 64 x 64 pixels (so that it can look good on retina displays). As with icon, this image should use transparency where appropriate, since it may be rendered on a non-white background.
     */
    @Nullable
    private String favicon;

    @Nullable
    private List<@Valid JsonFeedAuthor> authors;

    @NonNull
    @NotEmpty
    private List<@Valid JsonFeedItem> items;

    @Pattern(regexp = RssLanguage.LANGUAGE_CODE_PATTERN)
    @Nullable
    private String language;

    @Nullable
    private Boolean expired;

    @Nullable
    private List<@Valid JsonHub> hubs;

    /**
     * @return JSON Feed Builder
     */
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Creates builder with required fields.
     *
     * @param title JSON Feed's title
     * @param items JSON Feed's items
     * @return JSON Feed Builder
     */
    @NonNull
    public static Builder builder(@NonNull String title, @NonNull List<JsonFeedItem> items) {
        return new Builder().title(title).version(VERSION_JSON_FEED_1_1).items(items);
    }

    /**
     *
     * @param version JSON Feed version
     * @param title JSON Feed's title
     * @param items JSON Feed's items
     * @return JSON Feed Builder
     */
    @NonNull
    public static Builder builder(@NonNull String version, @NonNull String title, @NonNull List<JsonFeedItem> items) {
        return new Builder().title(title).version(version).items(items);
    }

    /**
     * URL of the version of the format the feed uses.
     *
     * @param version of JSON feed
     */
    public void setVersion(@NonNull String version) {
        this.version = version;
    }

    /**
     * Returns the URL of the version of the format the feed uses.
     *
     * @return version of JSON feed
     */
    @NonNull
    public String getVersion() {
        return this.version;
    }

    /**
     * The name of the feed, which will often correspond to the name of the website.
     *
     * @return the name of the feed
     */
    @NonNull
    public String getTitle() {
        return this.title;
    }

    /**
     * The name of the feed, which will often correspond to the name of the website.
     *
     * @param title the name of the feed
     */
    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    /**
     * The URL of the resource that the feed describes. This resource may or may not actually be a
     * “home” page, but it should be an HTML page
     *
     * @return The URL of the resource that the feed describes
     */
    @Nullable
    public String getHomePageUrl() {
        return this.homePageUrl;
    }

    /**
     * The URL of the resource that the feed describes. This resource may or may not actually be a
     * “home” page, but it should be an HTML page
     *
     * @param homePageUrl The URL of the resource that the feed describes
     */
    public void setHomePageUrl(@Nullable String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    /**
     * Detail on what the feed is about. A feed reader may display this text.
     *
     * @return detail on what the feed is about
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Detail on what the feed is about. A feed reader may display this text.
     *
     * @param description detail on what the feed is about
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The URL of the feed, and serves as the unique identifier for the feed.
     *
     * @return the URL of the feed, and serves as the unique identifier for the feed
     */
    @Nullable
    public String getFeedUrl() {
        return this.feedUrl;
    }

    /**
     * The URL of the feed, and serves as the unique identifier for the feed.
     *
     * @param feedUrl the URL of the feed, and serves as the unique identifier for the feed
     */
    public void setFeedUrl(@Nullable String feedUrl) {
        this.feedUrl = feedUrl;
    }

    /**
     * Description of the purpose of the feed. This is for the use of people looking at the raw
     * JSON, and should be ignored by feed readers
     *
     * @return description of the purpose of the feed.
     */
    @Nullable
    public String getUserComment() {
        return userComment;
    }

    /**
     * Description of the purpose of the feed. This is for the use of people looking at the raw
     * JSON, and should be ignored by feed readers
     *
     * @param userComment description of the purpose of the feed.
     */
    public void setUserComment(@Nullable String userComment) {
        this.userComment = userComment;
    }

    /**
     * The URL of a feed that provides the next n items, where n is determined by the publisher.
     * This allows for pagination, but with the expectation that reader software is not required to
     * use it and probably won’t use it very often
     *
     * @return the URL of a feed that provides the next n items
     */
    @Nullable
    public String getNextURL() {
        return nextURL;
    }

    /**
     * The URL of a feed that provides the next n items, where n is determined by the publisher.
     * This allows for pagination, but with the expectation that reader software is not required to
     * use it and probably won’t use it very often
     *
     * @param nextURL the URL of a feed that provides the next n items
     */
    public void setNextURL(@Nullable String nextURL) {
        this.nextURL = nextURL;
    }

    /**
     * The URL of an image for the feed suitable to be used in a timeline, much the way an avatar
     * might be used. It should be square and relatively large — such as 512 x 512 pixels
     *
     * @return the URL of an image for the feed suitable to be used in a timeline
     */
    @Nullable
    public String getIcon() {
        return icon;
    }

    /**
     * The URL of an image for the feed suitable to be used in a timeline, much the way an avatar
     * might be used. It should be square and relatively large — such as 512 x 512 pixels
     *
     * @param icon the URL of an image for the feed suitable to be used in a timeline
     */
    public void setIcon(@Nullable String icon) {
        this.icon = icon;
    }

    /**
     * The URL of an image for the feed suitable to be used in a source list. It should be square
     * and relatively small, but not smaller than 64 x 64 pixels
     *
     * @return the URL of an image for the feed suitable to be used in a source list
     */
    @Nullable
    public String getFavicon() {
        return favicon;
    }

    /**
     * The URL of an image for the feed suitable to be used in a source list. It should be square
     * and relatively small, but not smaller than 64 x 64 pixels
     *
     * @param favicon the URL of an image for the feed suitable to be used in a source list
     */
    public void setFavicon(@Nullable String favicon) {
        this.favicon = favicon;
    }

    /**
     * Specifies one or more feed authors. The author object has several members
     *
     * @return specifies one or more feed authors
     */
    public List<@Valid JsonFeedAuthor> getAuthors() {
        return authors;
    }

    /**
     * Specifies one or more feed authors. The author object has several members
     *
     * @param authors specifies one or more feed authors
     */
    public void setAuthors(@Nullable List<@Valid JsonFeedAuthor> authors) {
        this.authors = authors;
    }

    /**
     * The primary language for the feed in the format specified in RFC 5646.
     *
     * @return the primary language for the feed
     */
    @Nullable
    public String getLanguage() {
        return language;
    }

    /**
     * The primary language for the feed in the format specified in RFC 5646.
     *
     * @param language the primary language for the feed
     */
    public void setLanguage(@Nullable String language) {
        this.language = language;
    }

    /**
     * Says whether or not the feed is finished — that is, whether or not it will ever update again.
     *
     * @return expired says whether or not the feed is finished
     */
    @Nullable
    public Boolean getExpired() {
        return expired;
    }

    /**
     * Says whether or not the feed is finished — that is, whether or not it will ever update again.
     *
     * @param expired says whether or not the feed is finished
     */
    public void setExpired(@Nullable Boolean expired) {
        this.expired = expired;
    }

    /**
     * Describes endpoints that can be used to subscribe to real-time notifications from the
     * publisher of this feed.
     *
     * @return Describes endpoints that can be used to subscribe to real-time notifications
     */
    @Nullable
    public List<@Valid JsonHub> getHubs() {
        return hubs;
    }

    /**
     * Describes endpoints that can be used to subscribe to real-time notifications from the
     * publisher of this feed.
     *
     * @param hubs Describes endpoints that can be used to subscribe to real-time notifications
     */
    public void setHubs(@Nullable List<@Valid JsonHub> hubs) {
        this.hubs = hubs;
    }

    /**
     *
     * @param hub Endpoint that can be used to subscribe to real-time notifications from the publisher of this feed.
     */
    public void addHub(@NonNull JsonHub hub) {
        if (this.hubs == null) {
            this.hubs = new ArrayList<>();
        }
        this.hubs.add(hub);
    }

    /**
     * The items in the field.
     *
     * @return list of items in the feed
     */
    @NonNull
    public List<JsonFeedItem> getItems() {
        return items;
    }

    /**
     * The items in the field.
     *
     * @param items list of items in the feed
     */
    public void setItems(@NonNull List<JsonFeedItem> items) {
        this.items = items;
    }

    /**
     *
     * @param author Global JSON Feed's author
     */
    public void addAuthor(@NonNull JsonFeedAuthor author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

    /**
     *
     * @param item JSON Feed's item
     */
    public void addItem(@NonNull JsonFeedItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    /**
     *
     * @return Map representation of Object.
     */
    @NonNull
    public Map<String, Object> toMap() {
        Map<String, Object> m = new HashMap<>();
        if (getVersion() != null) {
            m.put(KEY_VERSION, getVersion());
        }
        if (getTitle() != null) {
            m.put(KEY_TITLE, getTitle());
        }
        if (getHomePageUrl() != null) {
            m.put(KEY_HOME_PAGE_URL, getHomePageUrl());
        }
        if (getFeedUrl() != null) {
            m.put(KEY_FEED_URL, getFeedUrl());
        }
        if (getDescription() != null) {
            m.put(KEY_DESCRIPTION, getDescription());
        }
        if (getUserComment() != null) {
            m.put(KEY_USER_COMMENT, getUserComment());
        }
        if (getNextURL() != null) {
            m.put(KEY_NEXT_URL, getNextURL());
        }
        if (getIcon() != null) {
            m.put(KEY_ICON, getIcon());
        }
        if (getFavicon() != null) {
            m.put(KEY_FAVICON, getFavicon());
        }
        if (getAuthors() != null) {
            m.put(KEY_AUTHORS, getAuthors().stream().map(JsonFeedAuthor::toMap).collect(Collectors.toList()));
        }
        if (getItems() != null) {
            m.put(KEY_ITEMS, getItems().stream().map(JsonFeedItem::toMap).collect(Collectors.toList()));
        }
        if (getLanguage() != null) {
            m.put(KEY_LANGUAGE, getLanguage());
        }
        if (getExpired() != null) {
            m.put(KEY_EXPIRED, getExpired());
        }
        if (getHubs() != null) {
            m.put(KEY_HUBS, getHubs().stream().map(JsonHub::toMap).collect(Collectors.toList()));
        }
        return m;
    }

    /**
     * JSON Feed Builder.
     */
    public static final class Builder {

        private final JsonFeed feed = new JsonFeed();

        /**
         * JSON Feed Builder Constructor.
         */
        private Builder() {
        }

        @NonNull
        public JsonFeed build() {
            return this.feed;
        }

        @NonNull
        public Builder version(@NonNull String version) {
            this.feed.setVersion(version);
            return this;
        }

        /**
         * The name of the feed, which will often correspond to the name of the website.
         *
         * @param title the name of the feed
         * @return Builder
         */
        @NonNull
        public Builder title(@NonNull String title) {
            this.feed.setTitle(title);
            return this;
        }

        /**
         * The URL of the resource that the feed describes. This resource may or may not actually be
         * a “home” page, but it should be an HTML page
         *
         * @param homePageUrl The URL of the resource that the feed describes
         * @return Builder
         */
        @NonNull
        public Builder homePageUrl(@NonNull String homePageUrl) {
            this.feed.setHomePageUrl(homePageUrl);
            return this;
        }

        /**
         * The URL of the feed, and serves as the unique identifier for the feed.
         *
         * @param feedUrl the URL of the feed, and serves as the unique identifier for the feed
         * @return Builder
         */
        @NonNull
        public Builder feedUrl(@NonNull String feedUrl) {
            this.feed.setFeedUrl(feedUrl);
            return this;
        }

        /**
         * Detail on what the feed is about. A feed reader may display this text.
         *
         * @param description detail on what the feed is about
         * @return Builder
         */
        @NonNull
        public Builder description(@NonNull String description) {
            this.feed.setDescription(description);
            return this;
        }

        /**
         * Description of the purpose of the feed. This is for the use of people looking at the raw
         * JSON, and should be ignored by feed readers
         *
         * @param userComment description of the purpose of the feed.
         * @return Builder
         */
        @NonNull
        public Builder userComment(@NonNull String userComment) {
            this.feed.setUserComment(userComment);
            return this;
        }

        /**
         * The URL of a feed that provides the next n items, where n is determined by the publisher.
         * This allows for pagination, but with the expectation that reader software is not required
         * to use it and probably won’t use it very often
         *
         * @param nextURL the URL of a feed that provides the next n items
         * @return Builder
         */
        @NonNull
        public Builder nextUrl(@NonNull String nextURL) {
            this.feed.setNextURL(nextURL);
            return this;
        }

        /**
         * The URL of an image for the feed suitable to be used in a timeline, much the way an
         * avatar might be used. It should be square and relatively large — such as 512 x 512
         * pixels
         *
         * @param icon the URL of an image for the feed suitable to be used in a timeline
         * @return Builder
         */
        @NonNull
        public Builder icon(@NonNull String icon) {
            this.feed.setIcon(icon);
            return this;
        }

        /**
         * The URL of an image for the feed suitable to be used in a source list. It should be
         * square and relatively small, but not smaller than 64 x 64 pixels
         *
         * @param favicon the URL of an image for the feed suitable to be used in a source list
         * @return Builder
         */
        @NonNull
        public Builder favicon(@NonNull String favicon) {
            this.feed.setFavicon(favicon);
            return this;
        }

        /**
         * Content of the feed.
         *
         * @param item content of the feed
         * @return Builder
         */
        @NonNull
        public Builder item(@NonNull JsonFeedItem item) {
            this.feed.addItem(item);
            return this;
        }

        /**
         * Adds an author to the feed.
         *
         * @param author An author of the feed
         * @return Builder
         */
        @NonNull
        public Builder author(@NonNull JsonFeedAuthor author) {
            this.feed.addAuthor(author);
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
            this.feed.setLanguage(language.getLanguageCode());
            return this;
        }

        /**
         * The primary language for the feed in the format specified in RFC 5646.
         *
         * @param language the primary language for the feed
         * @return Builder
         */
        @NonNull
        public Builder language(@NonNull String language) {
            this.feed.setLanguage(language);
            return this;
        }

        /**
         * Says whether or not the feed is finished — that is, whether or not it will ever update
         * again.
         *
         * @param expired says whether or not the feed is finished
         * @return Builder
         */
        @NonNull
        public Builder expired(@NonNull Boolean expired) {
            this.feed.setExpired(expired);
            return this;
        }

        /**
         * Describes endpoints that can be used to subscribe to real-time notifications from the
         * publisher of this feed.
         *
         * @param hub Describes endpoints that can be used to subscribe to real-time notifications
         * @return Builder
         */
        @NonNull
        public Builder hub(@NonNull JsonHub hub) {
            this.feed.addHub(hub);
            return this;
        }

        /**
         *
         * @param items Feed items
         * @return The Builder.
         */
        @NonNull
        public Builder items(@NonNull List<JsonFeedItem> items) {
            this.feed.setItems(items);
            return this;
        }
    }
}
