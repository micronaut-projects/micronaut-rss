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

import io.micronaut.rss.RssItem;
import io.micronaut.rss.RssItemEnclosure;

import edu.umd.cs.findbugs.annotations.Nullable;
import javax.validation.constraints.Positive;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Extends the RSS item with iTunes podcast episode properties.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
public class ItunesPodcastEpisode extends RssItem {

    @Nullable
    private String subtitle;

    @Nullable
    private String contentEncoded;

    @Nullable
    private String summary;

    @Nullable
    private String duration;

    private ItunesPodcastEpisodeType episodeType;

    private boolean explict;

    @Positive
    @Nullable
    private Integer episode;

    @Positive
    @Nullable
    private Integer season;

    @Nullable
    private String image;

    /**
     *
     * @return Get Podcast episode image
     */
    @Nullable
    public String getImage() {
        return image;
    }

    /**
     * Sets the episode type.
     * @param episodeType Episode's type
     */
    public void setEpisodeType(ItunesPodcastEpisodeType episodeType) {
        this.episodeType = episodeType;
    }

    /**
     *
     * @return Episode's subtitle.
     */
    public Optional<String> getSubtitle() {
        if (subtitle == null) {
            return Optional.empty();
        }
        return Optional.of(subtitle);
    }

    /**
     *
     * @return Episode's summary.
     */
    public Optional<String> getSummary() {
        if (summary == null) {
            return Optional.empty();
        }
        return Optional.of(summary);
    }

    /**
     * Sets episode subtitle.
     * @param subtitle Episode's subtitle.
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     *
     * @return Episode's type.
     */
    public Optional<ItunesPodcastEpisodeType> getEpisodeType() {
        if (episodeType == null) {
            return Optional.empty();
        }
        return Optional.of(episodeType);
    }

    /**
     *
     * @return Episode's content encoded.
     */
    public Optional<String> getContentEncoded() {
        if (contentEncoded == null) {
            return Optional.empty();
        }
        return Optional.of(contentEncoded);
    }

    /**
     * Sets episode summary.
     * @param summary Episode's summary.
     */
    public void setSummary(@Nullable String summary) {
        this.summary = summary;
    }

    /**
     * Sets episode contentEncoded.
     * @param contentEncoded Episode's contentEncoded.
     */
    public void setContentEncoded(String contentEncoded) {
        this.contentEncoded = contentEncoded;
    }

    /**
     * Sets episode duration.
     * @param duration Episode's duration.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     *
     * @return Episode's duration.
     */
    public Optional<String> getDuration() {
        if (duration == null) {
            return Optional.empty();
        }
        return Optional.of(duration);
    }

    /**
     *
     * @return whether an episode is explict.
     */
    public boolean isExplict() {
        return this.explict;
    }

    /**
     *
     * @param explict whether an episode is explict.
     */
    public void setExplict(boolean explict) {
        this.explict = explict;
    }

    /**
     * Sets episode episode number.
     * @param episode Episode's episode number.
     */
    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    /**
     *
     * @return Episode's number.
     */
    @Nullable
    public Optional<Integer> getEpisode() {
        if (episode == null) {
            return Optional.empty();
        }
        return Optional.of(episode);
    }

    /**
     * Sets episode season number.
     * @param season Episode's episode season number.
     */
    public void setSeason(Integer season) {
        this.season = season;
    }

    /**
     *
     * @return Episode's season number.
     */
    @Nullable
    public Optional<Integer> getSeason() {
        if (season == null) {
            return Optional.empty();
        }
        return Optional.of(season);
    }

    /**
     * Sets episode's image.
     * @param image episode's image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @param title Episode's title
     * @return A Builder to build a {@link ItunesPodcastEpisode}.
     */
    public static Builder builder(String title) {
        return new Builder(title);
    }

    /**
     * {@link ItunesPodcastEpisode} Builder.
     */
    public static final class Builder {

        ItunesPodcastEpisode episode = new ItunesPodcastEpisode();

        private Builder(String title) {
            title(title);
        }

        /**
         * Builds a {@link ItunesPodcastEpisode}.
         * @return A fully constructed {@link ItunesPodcastEpisode}
         */
        public ItunesPodcastEpisode build() {
            return this.episode;
        }

        /**
         * Populates Episode's type.
         * @param type Episode's type
         * @return a Episode Builder
         */
        public Builder episodeType(ItunesPodcastEpisodeType type) {
            episode.setEpisodeType(type);
            return this;
        }

        /**
         * Populates Episode's title.
         * @param title Episode's title
         * @return a Episode Builder
         */
        public Builder title(String title) {
            episode.setTitle(title);
            return this;
        }

        /**
         * Populates Episode's author.
         * @param author Episode's author.
         * @return a Episode Builder
         */
        public Builder author(String author) {
            episode.setAuthor(author);
            return this;
        }

        /**
         * Populates Episode's subtitle.
         * @param subtitle Episode's subtitle.
         * @return a Episode Builder
         */
        public Builder subtitle(String subtitle) {
            episode.setSubtitle(subtitle);
            return this;
        }

        /**
         * Populates Episode's summary.
         * @param summary Episode's summary.
         * @return a Episode Builder
         */
        public Builder summary(String summary) {
            episode.setSummary(summary);
            return this;
        }

        /**
         * Populates Episode's description.
         * @param description Episode's description.
         * @return a Episode Builder
         */
        public Builder description(String description) {
            episode.setDescription(description);
            return this;
        }

        /**
         * Populates Episode unique identifier.
         * @param guid Episode unique identifier.
         * @return a Episode Builder
         */
        public Builder guid(String guid) {
            episode.setGuid(guid);
            return this;
        }

        /**
         * Populates episode publication date.
         * @param pubDate Episode publication date.
         * @return a Episode Builder
         */
        public Builder pubDate(ZonedDateTime pubDate) {
            episode.setPubDate(pubDate);
            return this;
        }

        /**
         * Populate episode content encoded.
         * @param contentEncoded episode content encoded.
         * @return a Episode Builder
         */
        public Builder contentEncoded(String contentEncoded) {
            episode.setContentEncoded(contentEncoded);
            return this;
        }

        /**
         * Populates Episode's enclosure.
         * @param enclosure Episode enclosure.
         * @return a Episode Builder
         */
        public Builder enclosure(RssItemEnclosure enclosure) {
            episode.setEnclosure(enclosure);
            return this;
        }

        /**
         * Populate episode's duration.
         * @param duration Episode's duration.
         * @return a Episode Builder
         */
        public Builder duration(String duration) {
            episode.setDuration(duration);
            return this;
        }

        /**
         * Populate Episode explicit flag.
         * @param explicit if episode is explicit.
         * @return a Episode Builder
         */
        public Builder explicit(boolean explicit) {
            episode.setExplict(explicit);
            return this;
        }

        /**
         * Populates episode's number.
         * @param episode Episode number
         * @return a Episode Builder
         */
        public Builder episode(Integer episode) {
            this.episode.setEpisode(episode);
            return this;
        }

        /**
         * Populates episode season number.
         * @param season episode season number.
         * @return a Episode Builder
         */
        public Builder season(Integer season) {
            this.episode.setSeason(season);
            return this;
        }

        /**
         *
         * @param image Episode's image
         * @return a Episode Builder
         */
        public Builder image(String image) {
            this.episode.setImage(image);
            return this;
        }
    }
}
