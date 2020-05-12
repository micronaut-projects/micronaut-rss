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

import edu.umd.cs.findbugs.annotations.Nullable;
import java.time.ZonedDateTime;
import java.util.List;

import java.util.Optional;

/**
 * @see <a href="https://cyber.harvard.edu/rss/rss.html#hrelementsOfLtitemgt">Rss Item</a>
 *
 * @author Sergio del Amo
 * @since 1.0
 */
public class RssItem {

    @Nullable
    private String title;

    @Nullable
    private String link;

    @Nullable
    private String description;

    @Nullable
    private String author;

    @Nullable
    private List<String> category;

    @Nullable
    private String comments;

    @Nullable
    private RssItemEnclosure enclosure;

    @Nullable
    private ZonedDateTime pubDate;

    @Nullable
    private String guid;

    @Nullable
    private String source;

    /**
     * The title of the item.
     * @return The title of the item
     */
    public Optional<String> getTitle() {
        if (title == null) {
            return Optional.empty();
        }
        return Optional.of(title);
    }

    /**
     * The URL of the item.
     * @return The URL of the item.
     */
    public Optional<String> getLink() {
        if (link == null) {
            return Optional.empty();
        }
        return Optional.of(link);
    }

    /**
     * The item synopsis.
     * @return The item synopsis.
     */
    public Optional<String> getDescription() {
        if (description == null) {
            return Optional.empty();
        }
        return Optional.of(description);
    }

    /**
     * Email address of the author of the item.
     * @return Email address of the author of the item.
     */
    public Optional<String> getAuthor() {
        if (author == null) {
            return Optional.empty();
        }
        return Optional.of(author);
    }

    /**
     * Includes the item in one or more categories.
     * @return A list of one or more categories where the item is included.
     */
    public Optional<List<String>> getCategory() {
        if (category == null) {
            return Optional.empty();
        }
        return Optional.of(category);
    }

    /**
     * URL of a page for comments relating to the item.
     * @return URL of a page for comments relating to the item.
     */
    public Optional<String> getComments() {
        if (comments == null) {
            return Optional.empty();
        }
        return Optional.of(comments);
    }

    /**
     * Describes a media object that is attached to the item.
     * @return Describes a media object that is attached to the item.
     */
    public Optional<RssItemEnclosure> getEnclosure() {
        if (enclosure == null) {
            return Optional.empty();
        }
        return Optional.of(enclosure);
    }

    /**
     * A string that uniquely identifies the item.
     * @return A string that uniquely identifies the item.
     */
    public Optional<String> getGuid() {
        if (guid == null) {
            return Optional.empty();
        }
        return Optional.of(guid);
    }

    /**
     * Indicates when the item was published.
     * @return A string indicating when the item was published.
     */
    public Optional<ZonedDateTime> getPubDate() {
        if (pubDate == null) {
            return Optional.empty();
        }
        return Optional.of(pubDate);
    }

    /**
     * The RSS channel that the item came from.
     * @return A string that uniquely identifies the item.
     */
    public Optional<String> getSource() {
        if (source == null) {
            return Optional.empty();
        }
        return Optional.of(source);
    }

    /**
     *
     * @param title The title of the item.
     */
    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    /**
     *
     * @param link The URL of the item.
     */
    public void setLink(@Nullable String link) {
        this.link = link;
    }

    /**
     *
     * @param description The item synopsis.
     */
    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    /**
     *
     * @param author mail address
     */
    public void setAuthor(@Nullable String author) {
        this.author = author;
    }

    /**
     *
     * @param category List of categories to include the item.
     */
    public void setCategory(@Nullable List<String> category) {
        this.category = category;
    }

    /**
     *
     * @param comments URL of a page for comments relating to the item.
     */
    public void setComments(@Nullable String comments) {
        this.comments = comments;
    }

    /**
     *
     * @param enclosure A media object that is attached to the item.
     */
    public void setEnclosure(@Nullable RssItemEnclosure enclosure) {
        this.enclosure = enclosure;
    }

    /**
     *
     * @param pubDate Date when the item was published.
     */
    public void setPubDate(@Nullable ZonedDateTime pubDate) {
        this.pubDate = pubDate;
    }

    /**
     *
     * @param guid A string that uniquely identifies the item.
     */
    public void setGuid(@Nullable String guid) {
        this.guid = guid;
    }

    /**
     *
     * @param source The RSS channel that the item came from.
     */
    public void setSource(@Nullable String source) {
        this.source = source;
    }

    /**
     * @return A Builder to {@link RssItem}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * A RSS Item Channel.
     */
    public static final class Builder {

        private RssItem rssItem = new RssItem();

        private Builder() {

        }

        /**
         *
         * @param title The title of the item.
         * @return {@link RssItem.Builder}
         */
        public Builder title(String title) {
            rssItem.setTitle(title);
            return this;
        }

        /**
         *
         * @param link The URL of the item.
         * @return {@link RssItem.Builder}
         */
        public Builder link(String link) {
            rssItem.setLink(link);
            return this;
        }

        /**
         *
         * @param description The item synopsis.
         * @return {@link RssItem.Builder}
         */
        public Builder description(String description) {
            rssItem.setDescription(description);
            return this;
        }

        /**
         *
         * @param author Email address of the author of the item.
         * @return {@link RssItem.Builder}
         */
        public Builder author(String author) {
            rssItem.setAuthor(author);
            return this;
        }

        /**
         *
         * @param category A list of categories.
         * @return {@link RssItem.Builder}
         */
        public Builder category(List<String> category) {
            rssItem.setCategory(category);
            return this;
        }

        /**
         *
         * @param comments URL of a page for comments relating to the item.
         * @return {@link RssItem.Builder}
         */
        public Builder comments(String comments) {
            rssItem.setComments(comments);
            return this;
        }

        /**
         *
         * @param enclosure A media object that is attached to the item.
         * @return {@link RssItem.Builder}
         */
        public Builder enclosure(RssItemEnclosure enclosure) {
            rssItem.setEnclosure(enclosure);
            return this;
        }

        /**
         *
         * @param pubDate When the item was published.
         * @return {@link RssItem.Builder}
         */
        public Builder pubDate(ZonedDateTime pubDate) {
            rssItem.setPubDate(pubDate);
            return this;
        }

        /**
         *
         * @param guid A string that uniquely identifies the item.
         * @return {@link RssItem.Builder}
         */
        public Builder guid(String guid) {
            rssItem.setGuid(guid);
            return this;
        }

        /**
         *
         * @param source The RSS channel that the item came from.
         * @return {@link RssItem.Builder}
         */
        public Builder source(String source) {
            rssItem.setSource(source);
            return this;
        }

        /**
         *
         * @return Builds a {@link RssItem}
         */
        public RssItem build() {
            return this.rssItem;
        }

    }
}
