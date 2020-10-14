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

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;

public class JsonHub {

    @Nonnull
    @NotBlank
    private String type;

    @Nonnull
    @NotBlank
    private String url;

    /**
     * Describes the protocol used to talk to the hub.
     *
     * @return type describes the protocol used to talk to the hub
     */
    public String getType() {
        return type;
    }

    /**
     * Describes the protocol used to talk to the hub.
     *
     * @param type describes the protocol used to talk to the hub
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Url where the hub is present.
     *
     * @return url where the hub is present
     */
    public String getUrl() {
        return url;
    }

    /**
     * Url where the hub is present.
     *
     * @param url location where the hub is present
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private JsonHub jsonHub = new JsonHub();

        private Builder() {
        }

        /**
         * Describes the protocol used to talk to the hub.
         *
         * @param type describes the protocol used to talk to the hub
         * @return Builder
         */
        public Builder type(String type) {
            this.jsonHub.setType(type);
            return this;
        }

        /**
         * Url where the hub is present.
         *
         * @param url location where the hub is present
         * @return Builder
         */
        public Builder url(String url) {
            this.jsonHub.setUrl(url);
            return this;
        }

        public JsonHub build() {
            return jsonHub;
        }
    }
}
