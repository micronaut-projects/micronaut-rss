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

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.rss.RssChannel;
import io.micronaut.rss.RssChannelImage;
import io.micronaut.rss.RssItem;
import io.micronaut.rss.RssLanguage;
import io.micronaut.rss.RssSkipDays;
import io.micronaut.rss.RssSkipHours;
import io.micronaut.rss.RssTextInput;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * RSS 2.0 Feed for iTunes podcasts.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
public class ItunesPodcast extends RssChannel {

    private ItunesPodcastOwner owner;

    @Nullable
    private String author;

    private ItunesPodcastType type;

    private boolean explicit;

    @Nullable
    private String subtitle;

    @Nullable
    private String summary;

    private List<String> keywords = new ArrayList<>();
    private boolean block;

    /**
     * Sets the Podcast's block.
     * @param block Podcast's block.
     */
    public void setBlock(boolean block) {
        this.block = block;
    }

    /**
     *
     * @return The podcast owner.
     */
    public ItunesPodcastOwner getOwner() {
        return owner;
    }

    /**
     * Sets the Podcast's owner.
     * @param owner Podcast's owner.
     */
    public void setOwner(ItunesPodcastOwner owner) {
        this.owner = owner;
    }

    /**
     *
     * @return The podcast author.
     */
    public Optional<String> getAuthor() {
        if (author == null) {
            return Optional.empty();
        }
        return Optional.of(author);
    }

    /**
     * Sets the Podcast's author.
     * @param author Podcast's author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the Podcast's type.
     * @param type Podcast's type.
     */
    public void setType(ItunesPodcastType type) {
        this.type = type;
    }

    /**
     *
     * @return The podcast type.
     */
    public Optional<ItunesPodcastType> getType() {
        if (type == null) {
            return Optional.empty();
        }
        return Optional.of(type);
    }

    /**
     *
     * @return The podcast summary.
     */
    public Optional<String> getSummary() {
        if (summary == null) {
            return Optional.empty();
        }
        return Optional.of(summary);
    }

    /**
     * @deprecated Use {@link ItunesPodcast#isExplicit()} instead
     * @return The podcast explicit flag.
     */
    @Deprecated
    public boolean isExplict() {
        return isExplicit();
    }

    /**
     *
     * @return The podcast explicit flag.
     */
    public boolean isExplicit() {
        return explicit;
    }

    /**
     * Sets the Podcast's explicit flag.
     * @param explicit Podcast's explicit flag.
     * @deprecated Use {@link ItunesPodcast#setExplicit(boolean)} instead.
     */
    @Deprecated
    public void setExplict(boolean explicit) {
        setExplicit(explicit);
    }

    /**
     *
     * @param explicit Podcast's explicit flag.
     */
    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    /**
     *
     * @return The podcast subtitle.
     */
    public Optional<String> getSubtitle() {
        if (subtitle == null) {
            return Optional.empty();
        }
        return Optional.of(subtitle);
    }

    /**
     * Sets the Podcast's subtitle.
     * @param subtitle Podcast's subtitle.
     */
    public void setSubtitle(@Nullable String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * Sets the Podcast's summary.
     * @param summary Podcast's summary.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return A Builder to build a {@link ItunesPodcast}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     *
     * @return Whether it should block.
     */
    public boolean shouldBlock() {
        return this.block;
    }

    /**
     *
     * @return The podcast keywords.
     */
    public List<String> getKeywords() {
        return this.keywords;
    }

    /**
     * ItunesPodcast Builder.
     */
    public static final class Builder {

        ItunesPodcast podcast = new ItunesPodcast();

        private Builder() {
        }

        /**
         * Populates Podcast's title.
         * @param title Podcast's title
         * @return a Podcast Builder
         */
        public Builder title(String title) {
            podcast.setTitle(title);
            return this;
        }

        /**
         * Populates Podcast's type.
         * @param type Podcast's type
         * @return a Podcast Builder
         */
        public Builder type(ItunesPodcastType type) {
            podcast.setType(type);
            return this;
        }

        /**
         * Populates Podcast's explicit flag.
         * @param explicit Podcast's explicit flag
         * @return a Podcast Builder
         */
        public Builder explicit(boolean explicit) {
            podcast.setExplict(explicit);
            return this;
        }

        /**
         * Populates Podcast's explicit flag.
         * @param explicit Podcast's explicit flag
         * @deprecated {@link ItunesPodcast.Builder#setExplicit(boolean)} instead.
         * @return a Podcast Builder
         */
        @Deprecated
        public Builder explict(boolean explicit) {
            return explicit(explicit);
        }

        /**
         * Populates Podcast's author.
         * @param author Podcast's author
         * @return a Podcast Builder
         */
        public Builder author(String author) {
            podcast.setAuthor(author);
            return this;
        }

        /**
         * Populates Podcast's owner.
         * @param owner Podcast's owner
         * @return a Podcast Builder
         */
        public Builder owner(ItunesPodcastOwner owner) {
            podcast.setOwner(owner);
            return this;
        }

        /**
         * Populates Podcast's link.
         * @param link Podcast's link
         * @return a Podcast Builder
         */
        public Builder link(String link) {
            podcast.setLink(link);
            return this;
        }

        /**
         * Populates Podcast's description.
         * @param description Podcast's description
         * @return a Podcast Builder
         */
        public Builder description(String description) {
            podcast.setDescription(description);
            return this;
        }

        /**
         * Populates Podcast's language.
         * @param language Podcast's language
         * @return a Podcast Builder
         */
        public Builder language(RssLanguage language) {
            podcast.setLanguage(language);
            return this;
        }

        /**
         * Populates Podcast's copyright.
         * @param copyright Podcast's copyright
         * @return a Podcast Builder
         */
        public Builder copyright(String copyright) {
            podcast.setCopyright(copyright);
            return this;
        }

        /**
         * Populates Podcast's managingEditor.
         * @param managingEditor Podcast's managingEditor
         * @return a Podcast Builder
         */
        public Builder managingEditor(String managingEditor) {
            podcast.setManagingEditor(managingEditor);
            return this;
        }

        /**
         * Populates Podcast's webMaster.
         * @param webMaster Podcast's webMaster
         * @return a Podcast Builder
         */
        public Builder webMaster(String webMaster) {
            podcast.setWebMaster(webMaster);
            return this;
        }

        /**
         * Populates Podcast's Publication date.
         * @param pubDate Podcast's Publication date
         * @return a Podcast Builder
         */
        public Builder pubDate(ZonedDateTime pubDate) {
            podcast.setPubDate(pubDate);
            return this;
        }

        /**
         * Populates Podcast's lastBuildDate.
         * @param lastBuildDate Podcast's lastBuildDate
         * @return a Podcast Builder
         */
        public Builder lastBuildDate(ZonedDateTime lastBuildDate) {
            podcast.setLastBuildDate(lastBuildDate);
            return this;
        }

        /**
         * Populates Podcast's category.
         * @param category Podcast's category
         * @return a Podcast Builder
         */
        public Builder category(List<List<String>> category) {
            podcast.setCategory(category);
            return this;
        }

        /**
         * Populates Podcast's generator.
         * @param generator Podcast's generator
         * @return a Podcast Builder
         */
        public Builder generator(String generator) {
            podcast.setGenerator(generator);
            return this;
        }

        /**
         * Populates Podcast's docs.
         * @param docs Podcast's docs
         * @return a Podcast Builder
         */
        public Builder docs(String docs) {
            podcast.setDocs(docs);
            return this;
        }

        /**
         * Populates Podcast's cloud.
         * @param cloud Podcast's cloud
         * @return a Podcast Builder
         */
        public Builder cloud(String cloud) {
            podcast.setCloud(cloud);
            return this;
        }

        /**
         * Populates Podcast's ttl.
         * @param ttl Podcast's ttl
         * @return a Podcast Builder
         */
        public Builder ttl(Integer ttl) {
            podcast.setTtl(ttl);
            return this;
        }

        /**
         * Populates Podcast's image.
         * @param image Podcast's image
         * @return a Podcast Builder
         */
        public Builder image(RssChannelImage image) {
            podcast.setImage(image);
            return this;
        }

        /**
         * Populates Podcast's rating.
         * @param rating Podcast's rating
         * @return a Podcast Builder
         */
        public Builder rating(String rating) {
            podcast.setRating(rating);
            return this;
        }

        /**
         * Populates Podcast's textInput.
         * @param textInput Podcast's textInput
         * @return a Podcast Builder
         */
        public Builder textInput(RssTextInput textInput) {
            podcast.setTextInput(textInput);
            return this;
        }

        /**
         * Populates Podcast's skipHours.
         * @param skipHours Podcast's skipHours
         * @return a Podcast Builder
         */
        public Builder skipHours(List<RssSkipHours> skipHours) {
            podcast.setSkipHours(skipHours);
            return this;
        }

        /**
         * Populates Podcast's skipDays.
         * @param skipDays Podcast's skipDays
         * @return a Podcast Builder
         */
        public Builder skipDays(List<RssSkipDays> skipDays) {
            podcast.setSkipDays(skipDays);
            return this;
        }

        /**
         * Populates Podcast's item.
         * @param item Podcast's item
         * @return a Podcast Builder
         */
        public Builder item(RssItem item) {
            if (podcast.getItem().isPresent()) {
                podcast.addItem(item);
            } else {
                podcast.setItem(Arrays.asList(item));
            }
            return this;
        }

        /**
         * Populates Podcast's subtitle.
         * @param subtitle Podcast's subtitle
         * @return a Podcast Builder
         */
        public Builder subtitle(String subtitle) {
            podcast.setSubtitle(subtitle);
            return this;
        }

        /**
         * Populates Podcast's summary.
         * @param summary Podcast's summary
         * @return a Podcast Builder
         */
        public Builder summary(String summary) {
            podcast.setSummary(summary);
            return this;
        }

        /**
         * Populates Podcast's keyword.
         * @param keyword Podcast's keyword
         * @return a Podcast Builder
         */
        public Builder keyword(String keyword) {
            this.podcast.keywords.add(keyword);
            return this;
        }

        /**
         * Populates Podcast's block.
         * @param block Podcast's block
         * @return a Podcast Builder
         */
        public Builder block(boolean block) {
            this.podcast.setBlock(block);
            return this;
        }

        /**
         * Builds a {@link ItunesPodcast}.
         * @return A fully constructed {@link ItunesPodcast}
         */
        public ItunesPodcast build() {
            return this.podcast;
        }
    }
}
