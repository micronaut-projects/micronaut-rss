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
import java.util.Optional;

/**
 * @see <a href="https://cyber.harvard.edu/rss/rss.html#ltimagegtSubelementOfLtchannelgt">RSS Image element</a>
 * @author Sergio del Amo
 * @since 1.0
 */
public class RssChannelImage {

    /**
     * The URL of a GIF, JPEG or PNG image that represents the channel.
     */
    private String url;

    /**
     * Describes the image, it's used in the ALT attribute of the HTML <img> tag when the channel is rendered in HTML.
     */
    private String title;

    /**
     * The URL of the site, when the channel is rendered, the image is a link to the site.
     */
    private String link;

    /**
     * width of the image in pixels.
     */
    @Nullable
    private Integer width;

    /**
     * height of the image in pixels.
     */
    @Nullable
    private Integer height;

    /**
     * contains text that is included in the TITLE attribute of the link formed around the image in the HTML rendering.
     */
    @Nullable
    private String description;

    /**
     *
     * @return width of the image in pixels.
     */
    public Optional<Integer> getWidth() {
        if (width == null) {
            return Optional.empty();
        }
        return Optional.of(this.width);
    }

    /**
     *
     * @return height of the image in pixels.
     */
    public Optional<Integer> getHeight() {
        if (height == null) {
            return Optional.empty();
        }
        return Optional.of(this.height);
    }

    /**
     *
     * @return A text that is included in the TITLE attribute of the link formed around the image in the HTML rendering.
     */
    public Optional<String> getDescription() {
        if (description == null) {
            return Optional.empty();
        }
        return Optional.of(this.description);
    }

    /**
     *
     * @param description A longer image description.
     */
    private void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return The URL of a GIF, JPEG or PNG image that represents the channel.
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url A URL of a GIF, JPEG or PNG image that represents the channel.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return The image description.
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title A description of the image.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param title The title for the image
     * @param url URL of the site.
     * @param link The URL of the site,
     * @return A Builder to {@link RssChannelImage}.
     */
    public static RssChannelImage.Builder builder(String title, String url, String link) {
        return new RssChannelImage.Builder(title, url, link);
    }

    /**
     *
     * @param width width of the image in pixels.
     */
    public void setWidth(@Nullable Integer width) {
        this.width = width;
    }

    /**
     *
     * @param height height of the image in pixels.
     */
    public void setHeight(@Nullable Integer height) {
        this.height = height;
    }

    /**
     *
     * @param link The URL of the site
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return The URL of the site
     */
    public String getLink() {
        return this.link;
    }



    /**
     * A builder class to add different properties to {@link RssChannelImage}.
     */
    public static final class Builder {

        RssChannelImage image = new RssChannelImage();

        /**
         *
         * @param title The title for the image
         * @param url URL of the site.
         * @param link The URL of the site
         */
        private Builder(String title, String url, String link) {
            image.setTitle(title);
            image.setUrl(url);
            image.setLink(link);
        }

        /**
         *
         * @param width width of the image in pixels.
         * @return The {@link RssChannelImage.Builder}
         */
        public RssChannelImage.Builder width(Integer width) {
            image.setWidth(width);
            return this;
        }

        /**
         *
         * @param height height of the image in pixels.
         * @return The {@link RssChannelImage.Builder}
         */
        public RssChannelImage.Builder height(Integer height) {
            image.setHeight(height);
            return this;
        }

        /**
         *
         * @param description The longer image description.
         * @return The {@link RssChannelImage.Builder}
         */
        public RssChannelImage.Builder description(String description) {
            image.setDescription(description);
            return this;
        }

        /**
         * Builds an RSS Channel.
         * @return a {@link RssChannelImage}
         */
        public RssChannelImage build() {
            return this.image;
        }

    }
}
