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

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * Endpoint that can be used to subscribe to real-time notifications from the publisher of this feed.
 * @see <a href="https://jsonfeed.org/version/1.1">JSON Feed 1.1</a>
 * @author Sergio del Amo, mark1626
 * @since 2.2.0
 */
@Introspected
public class JsonHub {

    public static final String KEY_TYPE = "type";
    public static final String KEY_URL = "url";
    @NonNull
    @NotBlank
    private String type;

    @NonNull
    @NotBlank
    private String url;

    /**
     * Constructor.
     */
    public JsonHub() {
    }

    /**
     * Describes the protocol used to talk to the hub.
     *
     * @return type describes the protocol used to talk to the hub
     */
    @NonNull
    public String getType() {
        return type;
    }

    /**
     * Describes the protocol used to talk to the hub.
     *
     * @param type describes the protocol used to talk to the hub
     */
    public void setType(@NonNull String type) {
        this.type = type;
    }

    /**
     * Url where the hub is present.
     *
     * @return url where the hub is present
     */
    @NonNull
    public String getUrl() {
        return url;
    }

    /**
     * Url where the hub is present.
     *
     * @param url location where the hub is present
     */
    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    /**
     *
     * @return a Map representation of a JSON Hub.
     */
    @NonNull
    public Map<String, Object> toMap() {
        Map<String, Object> m = new HashMap<>();
        if (getType() != null) {
            m.put(KEY_TYPE, getType());
        }
        if (getUrl() != null) {
            m.put(KEY_URL, getUrl());
        }
        return m;
    }

    /**
     *
     * @return The builder
     */
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     *
     * @param type describes the protocol used to talk to the hub
     * @param url location where the hub is present
     * @return The builder
     */
    @NonNull
    public static Builder builder(@NonNull String type, @NonNull String url) {
        return new Builder().type(type).url(url);
    }

    /**
     * JsonHub Builder
     */
    public static final class Builder {

        private final JsonHub jsonHub = new JsonHub();

        /**
         * JSON Hub Builder Constructor.
         */
        private Builder() {
        }

        /**
         *
         * @param type describes the protocol used to talk to the hub
         * @return The Builder
         */
        @NonNull
        public Builder type(@NonNull String type) {
            this.jsonHub.type = type;
            return this;
        }

        /**
         *
         * @param url location where the hub is present
         * @return The Builder
         */
        @NonNull
        public Builder url(@NonNull String url) {
            this.jsonHub.url = url;
            return this;
        }

        @NonNull
        public JsonHub build() {
            return jsonHub;
        }
    }
}
