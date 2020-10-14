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
import java.util.Objects;

public final class JsonFeedAuthor {

    @Nullable
    private String name;
    @Nullable
    private String url;
    @Nullable
    private String avatar;

    /**
     * Name of the author.
     *
     * @return name of the author
     */
    public String getName() {
        return name;
    }

    /**
     * Name of the author.
     *
     * @param name name of the author
     */
    public void setName(@Nullable String name) {
        this.name = name;
    }

    /**
     * The URL of a site owned by the author.
     *
     * @return the URL of a site owned by the author
     */
    public String getUrl() {
        return url;
    }

    /**
     * The URL of a site owned by the author.
     *
     * @param url the URL of a site owned by the author
     */
    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    /**
     * the URL for an image for the author.
     *
     * @return the URL for an image for the author.
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * The URL for an image for the author. It should be square and relatively large such as 512 x
     * 512 pixels and should use transparency where appropriate, since it may be rendered on a
     * non-white background.
     *
     * @param avatar the URL for an image for the author.
     */
    public void setAvatar(@Nullable String avatar) {
        this.avatar = avatar;
    }

    public boolean isEmpty() {
        return Objects.isNull(this.avatar) &&
            Objects.isNull(this.url) &&
            Objects.isNull(this.name);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private final JsonFeedAuthor feedauthor = new JsonFeedAuthor();

        private Builder() {
        }

        /**
         * Name of the author.
         *
         * @param name name of the author
         * @return Builder
         */
        public Builder name(String name) {
            feedauthor.setName(name);
            return this;
        }

        /**
         * The URL of a site owned by the author.
         *
         * @param url the URL of a site owned by the author
         * @return Builder
         */
        public Builder url(String url) {
            feedauthor.setUrl(url);
            return this;
        }

        /**
         * The URL for an image for the author. It should be square and relatively large such as 512
         * x 512 pixels and should use transparency where appropriate, since it may be rendered on a
         * non-white background.
         *
         * @param avatar the URL for an image for the author.
         * @return Builder
         */
        public Builder avatar(String avatar) {
            feedauthor.setAvatar(avatar);
            return this;
        }

        public JsonFeedAuthor build() {
            return this.feedauthor;
        }
    }
}
