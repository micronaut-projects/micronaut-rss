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

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.rss.RssChannel;
import io.micronaut.rss.RssLanguage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class JsonFeed {

    @NotBlank
    @Nonnull
    private String version;

    @NotBlank
    @Nonnull
    private String title;

    @NotBlank
    @Nullable
    private String homePageUrl;

    @Nullable
    private String feedUrl;

    @Nullable
    private String description;

    @Nullable
    private String userComment;

    @Nullable
    private String nextURL;

    @Nullable
    private String icon;

    @Nullable
    private String favicon;

    @Nullable
    private List<JsonFeedAuthor> authors;

    private List<JsonFeedItem> items;

    @Deprecated
    @Nullable
    private String author;

    @Pattern(regexp = RssChannel.RSS_LANGUAGE_CODE_REGEX)
    @Nullable
    private RssLanguage language;

    @Nullable
    private Boolean expired;

    @Nullable
    private List<JsonHub> hubs;

    public static Builder builder() {
        return new Builder();
    }

    /**
     * URL of the version of the format the feed uses
     *
     * @param version of JSON feed
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Returns the URL of the version of the format the feed uses
     *
     * @return version of JSON feed
     */
    public String getVersion() {
        return this.version;
    }


    /**
     * The name of the feed, which will often correspond to the name of the website
     *
     * @return the name of the feed
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * The name of the feed, which will often correspond to the name of the website
     *
     * @param title the name of the feed
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The URL of the resource that the feed describes. This resource may or may not actually be a
     * “home” page, but it should be an HTML page
     *
     * @return The URL of the resource that the feed describes
     */
    public String getHomePageUrl() {
        return this.homePageUrl;
    }

    /**
     * The URL of the resource that the feed describes. This resource may or may not actually be a
     * “home” page, but it should be an HTML page
     *
     * @param homePageUrl The URL of the resource that the feed describes
     */
    public void setHomePageUrl(String homePageUrl) {
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
     * the URL of the feed, and serves as the unique identifier for the feed
     *
     * @return the URL of the feed, and serves as the unique identifier for the feed
     */
    @Nullable
    public String getFeedUrl() {
        return this.feedUrl;
    }

    /**
     * the URL of the feed, and serves as the unique identifier for the feed
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
    public List<JsonFeedAuthor> getAuthors() {
        return authors;
    }

    /**
     * Specifies one or more feed authors. The author object has several members
     *
     * @param authors specifies one or more feed authors
     */
    public void setAuthors(@Nullable List<JsonFeedAuthor> authors) {
        this.authors = authors;
    }

    /**
     * The author of the feed
     *
     * @return author the author of the feed
     */
    @Nullable
    public String getAuthor() {
        return author;
    }

    /**
     * The author of the feed
     *
     * @param author the author of the feed
     */
    @Deprecated
    public void setAuthor(@Nullable String author) {
        this.author = author;
    }

    /**
     * The primary language for the feed in the format specified in RFC 5646.
     *
     * @return the primary language for the feed
     */
    @Nullable
    public RssLanguage getLanguage() {
        return language;
    }

    /**
     * The primary language for the feed in the format specified in RFC 5646.
     *
     * @return the primary language for the feed
     */
    public void setLanguage(@Nullable RssLanguage language) {
        this.language = language;
    }

    /**
     * Says whether or not the feed is finished — that is, whether or not it will ever update again
     *
     * @return expired says whether or not the feed is finished
     */
    @Nullable
    public Boolean getExpired() {
        return expired;
    }

    /**
     * Says whether or not the feed is finished — that is, whether or not it will ever update again
     *
     * @param expired says whether or not the feed is finished
     */
    public void setExpired(@Nullable Boolean expired) {
        this.expired = expired;
    }

    /**
     * Describes endpoints that can be used to subscribe to real-time notifications from the
     * publisher of this feed
     *
     * @return Describes endpoints that can be used to subscribe to real-time notifications
     */
    @Nullable
    public List<JsonHub> getHubs() {
        return hubs;
    }

    /**
     * Describes endpoints that can be used to subscribe to real-time notifications from the
     * publisher of this feed
     *
     * @param hubs Describes endpoints that can be used to subscribe to real-time notifications
     */
    public void setHubs(@Nullable List<JsonHub> hubs) {
        this.hubs = hubs;
    }

    public void addHub(JsonHub hub) {
        if (this.hubs == null) {
            this.hubs = new ArrayList<>();
        }
        this.hubs.add(hub);
    }

    /**
     * The items in the field
     *
     * @return list of items in the feed
     */
    public List<JsonFeedItem> getItems() {
        return items;
    }

    /**
     * @param items list of items in the feed
     */
    public void setItems(List<JsonFeedItem> items) {
        this.items = items;
    }

    public void addAuthor(JsonFeedAuthor author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

    public void addItem(JsonFeedItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    public static final class Builder {

        JsonFeed feed = new JsonFeed();

        private Builder() {
        }

        public JsonFeed build() {
            return this.feed;
        }

        public Builder version(String version) {
            this.feed.setVersion(version);
            return this;
        }

        /**
         * The name of the feed, which will often correspond to the name of the website
         *
         * @param title the name of the feed
         * @return Builder
         */
        public Builder title(String title) {
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
        public Builder homePageUrl(String homePageUrl) {
            this.feed.setHomePageUrl(homePageUrl);
            return this;
        }

        /**
         * the URL of the feed, and serves as the unique identifier for the feed
         *
         * @param feedUrl the URL of the feed, and serves as the unique identifier for the feed
         * @return Builder
         */
        public Builder feedUrl(String feedUrl) {
            this.feed.setFeedUrl(feedUrl);
            return this;
        }

        /**
         * Detail on what the feed is about. A feed reader may display this text.
         *
         * @param description detail on what the feed is about
         * @return Builder
         */
        public Builder description(String description) {
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
        public Builder userComment(String userComment) {
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
        public Builder nextUrl(String nextURL) {
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
        public Builder icon(String icon) {
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
        public Builder favicon(String favicon) {
            this.feed.setFavicon(favicon);
            return this;
        }

        /**
         * Content of the feed
         *
         * @param item content of the feed
         * @return Builder
         */
        public Builder item(JsonFeedItem item) {
            this.feed.addItem(item);
            return this;
        }

        /**
         * Adds an author to the feed
         *
         * @param author An author of the feed
         * @return Builder
         */
        public Builder author(JsonFeedAuthor author) {
            this.feed.addAuthor(author);
            return this;
        }

        /**
         * The author of the feed
         *
         * @param author the author of the feed
         * @return Builder
         */
        @Deprecated
        public Builder author(String author) {
            this.feed.setAuthor(author);
            return this;
        }

        /**
         * The primary language for the feed in the format specified in RFC 5646.
         *
         * @param language the primary language for the feed
         * @return Builder
         */
        public Builder language(RssLanguage language) {
            this.feed.setLanguage(language);
            return this;
        }

        /**
         * Says whether or not the feed is finished — that is, whether or not it will ever update
         * again
         *
         * @param expired says whether or not the feed is finished
         * @return Builder
         */
        public Builder expired(Boolean expired) {
            this.feed.setExpired(expired);
            return this;
        }

        /**
         * Describes endpoints that can be used to subscribe to real-time notifications from the
         * publisher of this feed
         *
         * @param hub Describes endpoints that can be used to subscribe to real-time notifications
         * @return Builder
         */
        public Builder hub(JsonHub hub) {
            this.feed.addHub(hub);
            return this;
        }
    }
}
