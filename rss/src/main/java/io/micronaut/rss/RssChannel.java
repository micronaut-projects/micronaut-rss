/*
 * Copyright 2017-2019 original authors
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

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;

/**
 * @see <a href="https://cyber.harvard.edu/rss/rss.html">RSS 2.0 Specification</a>
 *
 * @author Sergio del Amo
 * @since 1.0
 */
public class RssChannel {

    public static final String RSS_LANGUAGE_CODE_REGEX = "af|sq|eu|be|bg|ca|zh-cn|zh-tw|hr|cs|da|nl|nl-be|nl-nl|en|en-au|en-bz|en-ca|en-ie|en-jm|en-nz|en-ph|en-za|en-tt|en-gb|en-us|en-zw| et|fo|fi|fr|fr-be|fr-ca|fr-fr|fr-lu|fr-mc|fr-ch|gl|gd|de|de-at|de-de|de-li|de-lu|de-ch|el|haw|hu|is|in|ga|it|it-it|it-ch|ja|ko|mk|no|pl|pt|pt-br|pt-pt|ro|ro-mo|ro-ro|ru|ru-mo|ru-ru|sr|sk|sl|es|es-ar|es-bo|es-cl|es-co|es-cr|es-do|es-ec|es-sv|es-gt|es-hn|es-mx|es-ni|es-pa|es-py|es-pe|es-pr|es-es|es-uy|es-ve|sv|sv-fi|sv-se|tr|uk";

    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String link;

    @NotBlank
    @NotNull
    private String description;

    @Pattern(regexp = RSS_LANGUAGE_CODE_REGEX)
    @Nullable
    private RssLanguage language;

    @Nullable
    private String copyright;

    @Nullable
    private String managingEditor;

    @Nullable
    private String webMaster;

    @Nullable
    private ZonedDateTime pubDate;

    @Nullable
    private ZonedDateTime lastBuildDate;

    @Nullable
    private List<List<String>> category = new ArrayList<>();

    @Nullable
    private String generator;

    @Nullable
    private String docs;

    @Nullable
    private String cloud;

    @Positive
    @Nullable
    private Integer ttl;

    @Nullable
    private RssChannelImage image;

    @Nullable
    private String rating;

    @Nullable
    private RssTextInput textInput;

    @Nullable
    private List<RssSkipHours> skipHours;

    @Nullable
    private List<RssSkipDays> skipDays;

    @Nullable
    private List<RssItem> item = new ArrayList<>();

    /**
     * The name of the channel.
     *
     * <p>It's how people refer to your service. If you have an HTML website that contains the same information as your RSS file, the title of your channel should be the same as the title of your website.</p>
     * @return The name of the channel.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The URL to the HTML website corresponding to the channel.
     */
    public String getLink() {
        return link;
    }

    /**
     * Phrase or sentence describing the channel.
     * @return Phrase or sentence describing the channel.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * The language the channel is written in. This allows aggregators to group all Italian language sites, for example, on a single page. A list of allowable values for this element, as provided by Netscape, is here. You may also use values defined by the W3C
     * @return The language the channel is written in.
     */
    public Optional<RssLanguage> getLanguage() {
        if (language == null) {
            return Optional.empty();
        }
        return Optional.of(language);
    }

    /**
     * Copyright notice for content in the channel.
     *
     * @return Copyright notice for content in the channel.
     */
    public Optional<String> getCopyright() {
        if (copyright == null) {
            return Optional.empty();
        }
        return Optional.of(copyright);
    }

    /**
     * @return Email address for person responsible for editorial content.
     */
    public Optional<String> getManagingEditor() {
        if (managingEditor == null) {
            return Optional.empty();
        }
        return Optional.of(managingEditor);
    }

    /**
     * @return Email address for person responsible for technical issues relating to channel.
     */
    public Optional<String> getWebMaster() {
        if (webMaster == null) {
            return Optional.empty();
        }
        return Optional.of(webMaster);
    }

    /**
     * The publication date for the content in the channel. For example, the New York Times publishes on a daily basis, the publication date flips once every 24 hours. That's when the pubDate of the channel changes. All date-times in RSS conform to the Date and Time Specification of RFC 822, with the exception that the year may be expressed with two characters or four characters (four preferred).
     * @return The publication date for the content in the channel.
     */
    public Optional<ZonedDateTime> getPubDate() {
        if (pubDate == null) {
            return Optional.empty();
        }
        return Optional.of(pubDate);
    }

    /**
     * The last time the content of the channel changed.
     * @return The last time the content of the channel changed.
     */
    public Optional<ZonedDateTime> getLastBuildDate() {
        if (lastBuildDate == null) {
            return Optional.empty();
        }
        return Optional.of(lastBuildDate);
    }

    /**
     * Specify one or more categories that the channel belongs to. Follows the same rules as the <item>-level category element. More info.
     * @return Specify one or more categories that the channel belongs to.
     */
    public Optional<List<List<String>>> getCategory() {
        if (category == null) {
            return Optional.empty();
        }
        return Optional.of(category);
    }

    /**
     * A string indicating the program used to generate the channel.
     * @return A string indicating the program used to generate the channel.
     */
    public Optional<String> getGenerator() {
        if (generator == null) {
            return Optional.empty();
        }
        return Optional.of(generator);
    }

    /**
     * A URL that points to the documentation for the format used in the RSS file. It's probably a pointer to this page. It's for people who might stumble across an RSS file on a Web server 25 years from now and wonder what it is.
     * @return A URL that points to the documentation for the format used in the RSS file
     */
    public Optional<String> getDocs() {
        if (docs == null) {
            return Optional.empty();
        }
        return Optional.of(docs);
    }

    /**
     * Allows processes to register with a cloud to be notified of updates to the channel, implementing a lightweight publish-subscribe protocol for RSS feeds. More info here.
     * @return Allows processes to register with a cloud to be notified of updates to the channel, implementing a lightweight publish-subscribe protocol for RSS feeds.
     */
    public Optional<String> getCloud() {
        if (cloud == null) {
            return Optional.empty();
        }
        return Optional.of(cloud);
    }


    /**
     * ttl stands for time to live. It's a number of minutes that indicates how long a channel can be cached before refreshing from the source. More info here.
     * @return It's a number of minutes that indicates how long a channel can be cached before refreshing from the source.
     */
    public Optional<Integer> getTtl() {
        if (ttl == null) {
            return Optional.empty();
        }
        return Optional.of(ttl);
    }

    /**
     * Specifies a GIF, JPEG or PNG image that can be displayed with the channel. More info here.
     * @return Specifies a GIF, JPEG or PNG image that can be displayed with the channel.
     */
    public Optional<RssChannelImage> getImage() {
        if (image == null) {
            return Optional.empty();
        }
        return Optional.of(image);
    }

    /**
     * The PICS rating for the channel.
     * @return The PICS rating for the channel.
     */
    public Optional<String> getRating() {
        if (rating == null) {
            return Optional.empty();
        }
        return Optional.of(rating);
    }

    /**
     * Specifies a text input box that can be displayed with the channel.
     * @return An optional {@link RssTextInput}
     */
    public Optional<RssTextInput> getTextInput() {
        if (textInput == null) {
            return Optional.empty();
        }
        return Optional.of(textInput);
    }

    /**
     * A hint for aggregators telling them which hours they can skip.
     * @return A hint for aggregators telling them which hours they can skip.
     */
    public Optional<List<RssSkipHours>> getSkipHours() {
        if (skipHours == null) {
            return Optional.empty();
        }
        return Optional.of(skipHours);
    }

    /**
     * A hint for aggregators telling them which days they can skip.
     * @return A hint for aggregators telling them which days they can skip.
     */
    public Optional<List<RssSkipDays>> getSkipDays() {
        if (skipDays == null) {
            return Optional.empty();
        }
        return Optional.of(skipDays);
    }

    /**
     * An item may represent a "story" -- much like a story in a newspaper or magazine; if so its description is a synopsis of the story, and the link points to the full story.
     * @return An optional List of {@link RssItem}
     */
    public Optional<List<RssItem>> getItem() {
        if (item == null) {
            return Optional.empty();
        }
        return Optional.of(item);
    }

    /**
     *
     * @param item a RSS Item
     */
    public void addItem(RssItem item) {
        if (item != null) {
            this.item.add(item);
        }
    }

    /**
     *
     * @param skipDays a List of {@link RssSkipDays}
     */
    public void setSkipDays(List<RssSkipDays> skipDays) {
        this.skipDays = skipDays;
    }

    /**
     *
     * @param skipHours a List of {@link RssSkipHours}.
     */
    public void setSkipHours(List<RssSkipHours> skipHours) {
        this.skipHours = skipHours;
    }

    /**
     *
     * @param textInput A {@link RssTextInput}.
     */
    public void setTextInput(RssTextInput textInput) {
        this.textInput = textInput;
    }

    /**
     *
     * @param rating A PICS Rating.
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     *
     * @param image GIF, JPEG or PNG image that can be displayed with the channel.
     */
    public void setImage(RssChannelImage image) {
        this.image = image;
    }

    /**
     *
     * @param ttl time to live expressed in minutes.
     */
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    /**
     *
     * @param cloud A cloud.
     */
    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    /**
     *
     * @param docs A URL that points to the documentation for the format used in the RSS file.
     */
    public void setDocs(String docs) {
        this.docs = docs;
    }

    /**
     *
     * @param generator A string indicating the program used to generate the channel.
     */
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    /**
     *
     * @param category A list of categories to associate the RSS Channel with.
     */
    public void setCategory(List<List<String>> category) {
        this.category = category;
    }

    /**
     *
     * @param lastBuildDate The last time the content of the channel changed.
     */
    public void setLastBuildDate(ZonedDateTime lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    /**
     *
     * @param pubDate The publication date for the content in the channel.
     */
    public void setPubDate(ZonedDateTime pubDate) {
        this.pubDate = pubDate;
    }

    /**
     *
     * @param webMaster Email address for person responsible for technical issues relating to channel.
     */
    public void setWebMaster(String webMaster) {
        this.webMaster = webMaster;
    }

    /**
     *
     * @param managingEditor Email address for person responsible for editorial content.
     */
    public void setManagingEditor(String managingEditor) {
        this.managingEditor = managingEditor;
    }

    /**
     *
     * @param copyright Copyright notice for content in the channel.
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     *
     * @param language The language the channel is written in.
     */
    public void setLanguage(RssLanguage language) {
        this.language = language;
    }

    /**
     *
     * @param description Phrase or sentence describing the channel.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param link The URL to the HTML website corresponding to the channel.
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @param title The name of the channel.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @param item A RSS Item.
     */
    public void setItem(List<RssItem> item) {
        this.item = item;
    }

    /**
     * @param title The name of the channel.
     * @param link The URL to the HTML website corresponding to the channel.
     * @param description Phrase or sentence describing the channel.
     * @return A Builder to build a {@link RssChannel}.
     */
    public static Builder builder(String title,
                                  String link,
                                  String description) {
        return new Builder(title, link, description);
    }

    /**
     * RSS Channel Builder.
     */
    public static final class Builder {

        private RssChannel rssChannel = new RssChannel();

        /**
         * Construct a builder.
         *
         * @param title The name of the channel.
         * @param link The URL to the HTML website corresponding to the channel.
         * @param description Phrase or sentence describing the channel.
         */
        private Builder(String title, String link, String description) {
            rssChannel.setTitle(title);
            rssChannel.setLink(link);
            rssChannel.setDescription(description);

        }

        /**
         *
         * @param language The language the channel is written in.
         * @return The Builder.
         */
        public Builder language(RssLanguage language) {
            rssChannel.setLanguage(language);
            return this;
        }

        /**
         *
         * @param copyright Copyright notice for content in the channel.
         * @return The Builder.
         */
        public Builder copyright(String copyright) {
            rssChannel.setCopyright(copyright);
            return this;
        }

        /**
         *
         * @param managingEditor Email address for person responsible for editorial content.
         * @return The Builder.
         */
        public Builder managingEditor(String managingEditor) {
            rssChannel.setManagingEditor(managingEditor);
            return this;
        }

        /**
         *
         * @param webMaster Email address for person responsible for technical issues relating to channel.
         * @return The Builder.
         */
        public Builder webMaster(String webMaster) {
            rssChannel.setWebMaster(webMaster);
            return this;
        }

        /**
         *
         * @param pubDate The publication date for the content in the channel.
         * @return The Builder.
         */
        public Builder pubDate(ZonedDateTime pubDate) {
            rssChannel.setPubDate(pubDate);
            return this;
        }

        /**
         *
         * @param lastBuildDate The last time the content of the channel changed.
         * @return The Builder.
         */
        public Builder lastBuildDate(ZonedDateTime lastBuildDate) {
            rssChannel.setLastBuildDate(lastBuildDate);
            return this;
        }

        /**
         *
         * @param category a category to associate the RSS Channel with.
         * @return The Builder.
         */
        public Builder category(List<List<String>> category) {
            rssChannel.setCategory(category);
            return this;
        }

        /**
         *
         * @param generator A string indicating the program used to generate the channel.
         * @return The Builder.
         */
        public Builder generator(String generator) {
            rssChannel.setGenerator(generator);
            return this;
        }

        /**
         *
         * @param docs A URL that points to the documentation for the format used in the RSS file.
         * @return The Builder.
         */
        public Builder docs(String docs) {
            rssChannel.setDocs(docs);
            return this;
        }

        /**
         *
         * @param cloud  A cloud.
         * @return The Builder.
         */
        public Builder cloud(String cloud) {
            rssChannel.setCloud(cloud);
            return this;
        }

        /**
         *
         * @param ttl time to live expressed in minutes.
         * @return the Builder
         */
        public Builder ttl(Integer ttl) {
            rssChannel.setTtl(ttl);
            return this;
        }

        /**
         *
         * @param image GIF, JPEG or PNG image that can be displayed with the channel
         * @return The Builder.
         */
        public Builder image(RssChannelImage image) {
            rssChannel.setImage(image);
            return this;
        }

        /**
         *
         * @param rating The PICS rating for the channel.
         * @return The Builder.
         */
        public Builder rating(String rating) {
            rssChannel.setRating(rating);
            return this;
        }

        /**
         *
         * @param textInput a text input box
         * @return The Builder.
         */
        public Builder textInput(RssTextInput textInput) {
            rssChannel.setTextInput(textInput);
            return this;
        }

        /**
         *
         * @param skipHours a List of {@link RssSkipHours}.
         * @return The Builder.
         */
        public Builder skipHours(List<RssSkipHours> skipHours) {
            rssChannel.setSkipHours(skipHours);
            return this;
        }

        /**
         *
         * @param skipDays a List of {@link RssSkipDays}.
         * @return The Builder.
         */
        public Builder skipDays(List<RssSkipDays> skipDays) {
            rssChannel.setSkipDays(skipDays);
            return this;
        }

        /**
         *
         * @param item An RSS Item.
         * @return The Builder.
         */
        public Builder item(RssItem item) {
            if (rssChannel.getItem().isPresent()) {
                rssChannel.addItem(item);
            } else {
                rssChannel.setItem(Arrays.asList(item));
            }
            return this;
        }

        /**
         *
         * @return A RSS Channel.
         */
        public RssChannel build() {
            return this.rssChannel;
        }
    }
}
