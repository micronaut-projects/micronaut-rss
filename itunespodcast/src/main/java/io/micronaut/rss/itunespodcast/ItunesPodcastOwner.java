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

import io.micronaut.core.annotation.NonNull;

/**
 * Itunes Podcast owner.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
public class ItunesPodcastOwner {
    private String email;
    private String name;

    /**
     *
     * @return Podcast owner's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets podcast owner's email.
     * @param email Podcast owner's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return Podcast owner's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets podcast owner's name.
     * @param name podcast owner's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return A Builder to {@link ItunesPodcastOwner}
     */
    public static ItunesPodcastOwner.Builder builder() {
        return new ItunesPodcastOwner.Builder();
    }

    /**
     * A builder class to add different properties to {@link ItunesPodcastOwner}.
     */
    public static final class Builder {

        ItunesPodcastOwner owner = new ItunesPodcastOwner();

        /**
         * Construct a builder.
         */
        private Builder() {
        }

        /**
         *
         * @param name Podcast owner's name.
         * @return ItunesPodcastOwner builder.
         */
        public ItunesPodcastOwner.Builder name(String name) {
            owner.setName(name);
            return this;
        }

        /**
         *
         * @param email Podcast owner's email.
         * @return ItunesPodcastOwner builder.
         */
        public ItunesPodcastOwner.Builder email(String email) {
            owner.setEmail(email);
            return this;
        }

        /**
         *
         * @return a fully constructed {@link ItunesPodcastOwner}.
         */
        @NonNull
        public ItunesPodcastOwner build() {
            return this.owner;
        }

    }

}
