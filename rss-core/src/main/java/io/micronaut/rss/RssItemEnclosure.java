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


import edu.umd.cs.findbugs.annotations.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 *
 * RSS Item enclosure.
 * @see <a href="https://cyber.harvard.edu/rss/rss.html#ltenclosuregtSubelementOfLtitemgt">Rss Item enclosure</a>
 *
 * @author Sergio del Amo
 * @since 1.0
 */
public class RssItemEnclosure {

    /**
     * The URL attribute points to your podcast content. The file extension specified within the URL attribute determines whether or note content appears in the podcast directory. Supported file formats include M4A, MP3, MOV, MP4, M4V, PDF, and EPUB.
     */
    @NotBlank
    @NonNull
    private String url;

    /**
     * The length attribute is the file size in bytes.
     */
    @Positive
    @NotNull
    @NonNull
    private Integer length;

    @NotNull
    @NonNull
    private String type;

    /**
     *
     * @return The URL attribute points to your podcast content.
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url A URL to the podcast content.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return The length attribute is the file size in bytes.
     */
    public Integer getLength() {
        return length;
    }

    /**
     *
     * @param length The file size in bytes.
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * @return A RssItemEnclosure.Builder
     */
    public static RssItemEnclosure.Builder builder() {
        return new RssItemEnclosure.Builder();
    }

    /**
     *
     * @return A standard MIME type.
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type A standard MIME type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * A builder class to add different properties to {@link RssItemEnclosure}.
     */
    public static final class Builder {

        private RssItemEnclosure enclosure = new RssItemEnclosure();

        /**
         * Construct a builder.
         */
        private Builder() {
        }

        /**
         *
         * @param url A URL to the podcast content.
         * @return The {@link RssItemEnclosure.Builder}
         */
        public RssItemEnclosure.Builder url(String url) {
            enclosure.setUrl(url);
            return this;
        }

        /**
         *
         * @param length The file size in bytes.
         * @return The {@link RssItemEnclosure.Builder}
         */
        public RssItemEnclosure.Builder length(Integer length) {
            enclosure.setLength(length);
            return this;
        }

        /**
         *
         * @param type A standard MIME type.
         * @return The {@link RssItemEnclosure.Builder}
         */
        public RssItemEnclosure.Builder type(String type) {
            enclosure.setType(type);
            return this;
        }

        /**
         *
         * @return A {@link RssItemEnclosure}
         */
        public RssItemEnclosure build() {
            return this.enclosure;
        }

    }
}
