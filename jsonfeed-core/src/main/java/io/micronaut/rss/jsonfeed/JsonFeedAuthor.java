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

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * A JSON Feed Author.
 * @see <a href="https://jsonfeed.org/version/1.1">JSON Feed 1.1</a>
 * @author Sergio del Amo, mark1626
 * @since 2.2.0
 */
@Introspected
public class JsonFeedAuthor {

    public static final String KEY_AVATAR = "avatar";
    public static final String KEY_URL = "url";
    public static final String KEY_NAME = "name";
    /**
     * Author's name.
     */
    @Nullable
    private String name;

    /**
     * The URL of a site owned by the author. It could be a blog, micro-blog, Twitter account, and so on. Ideally the linked-to page provides a way to contact the author, but that’s not required. The URL could be a mailto: link, though we suspect that will be rare.
     */
    @Nullable
    private String url;

    /**
     *  The URL for an image for the author. As with icon, it should be square and relatively large — such as 512 x 512 pixels — and should use transparency where appropriate, since it may be rendered on a non-white background.
     */
    @Nullable
    private String avatar;

    /**
     * Name of the author.
     *
     * @return name of the author
     */
    @Nullable
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
    @Nullable
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
    @Nullable
    public String getAvatar() {
        return avatar;
    }

    /**
     * The URL for an image for the author. It should be square and relatively large such as 512 x 512 pixels and should use transparency where appropriate, since it may be rendered on a
     * non-white background.
     *
     * @param avatar the URL for an image for the author.
     */
    public void setAvatar(@Nullable String avatar) {
        this.avatar = avatar;
    }

    /**
     *
     * @return true if all author's, name, url and avatar are empty or null.
     */
    public boolean isEmpty() {
        return StringUtils.isEmpty(this.avatar) &&
            StringUtils.isEmpty(this.url) &&
            StringUtils.isEmpty(this.name);
    }

    /**
     *
     * @return a Map representation of a JSON Feed Author.
     */
    @NonNull
    public Map<String, Object> toMap() {
        Map<String, Object> m = new HashMap<>();
        if (getName() != null) {
            m.put(KEY_NAME, getName());
        }
        if (getUrl() != null) {
            m.put(KEY_URL, getUrl());
        }
        if (getAvatar() != null) {
            m.put(KEY_AVATAR, getAvatar());
        }
        return m;
    }

    /**
     *
     * @return JSON Feed Author's Builder.
     */
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * JSON Feed Author's Builder.
     */
    public static final class Builder {

        private final JsonFeedAuthor feedauthor = new JsonFeedAuthor();

        /**
         * JSON Feed Builder Constructor.
         */
        private Builder() {
        }

        /**
         * Name of the author.
         *
         * @param name name of the author
         * @return Builder
         */
        @NonNull
        public Builder name(@Nullable String name) {
            feedauthor.setName(name);
            return this;
        }

        /**
         * The URL of a site owned by the author.
         *
         * @param url the URL of a site owned by the author
         * @return Builder
         */
        @NonNull
        public Builder url(@Nullable String url) {
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
        @NonNull
        public Builder avatar(@Nullable String avatar) {
            feedauthor.setAvatar(avatar);
            return this;
        }

        @NonNull
        public JsonFeedAuthor build() {
            return this.feedauthor;
        }
    }
}
