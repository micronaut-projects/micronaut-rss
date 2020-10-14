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
import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;

public final class JsonFeedAttachment {

    @Nonnull
    @NotBlank
    private String url;

    @Nonnull
    @NotBlank
    private String mimeType;

    @Nullable
    private String title;

    @Nullable
    private Long sizeInBytes;

    @Nullable
    private Integer durationInSeconds;

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Location of the attachment.
     *
     * @return location of the attachment
     */
    @Nonnull
    public String getUrl() {
        return this.url;
    }

    /**
     * Specifies the location of the attachment.
     *
     * @param url the location of the attachment
     */
    public void setUrl(@Nonnull String url) {
        this.url = url;
    }

    /**
     * Mime type of the attachment.
     *
     * @return mime type of the attachment
     */
    @Nonnull
    public String getMimeType() {
        return this.mimeType;
    }

    /**
     * Mime type of the attachment.
     *
     * @param mimeType mime type of the attachment
     */
    public void setMimeType(@Nonnull String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Name of the attachment.
     *
     * @return name of the attachment
     */
    @Nullable
    public String getTitle() {
        return this.title;
    }

    /**
     * Name of the attachment.
     *
     * @param title name of the attachment
     */
    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    /**
     * Specifies how large the file is in bytes.
     *
     * @return specifies how large the file is in bytes
     */
    @Nullable
    public Long getSizeInBytes() {
        return this.sizeInBytes;
    }

    /**
     * Specifies how large the file is in bytes.
     *
     * @param sizeInBytes specifies how large the file is in bytes
     */
    public void setSizeInBytes(@Nullable Long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    /**
     * Specifies how long it takes to listen to or watch, when played at normal speed.
     *
     * @return specifies duration of the attachment
     */
    @Nullable
    public Integer getDurationInSeconds() {
        return this.durationInSeconds;
    }

    /**
     * Specifies how long it takes to listen to or watch, when played at normal speed.
     *
     * @param durationInSeconds duration of the attachment
     */
    public void setDurationInSeconds(@Nullable Integer durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public static final class Builder {

        private final JsonFeedAttachment feedAttachment = new JsonFeedAttachment();

        private Builder() {
        }

        /**
         * Specifies the location of the attachment.
         *
         * @param url the location of the attachment
         * @return The Builder
         */
        public Builder url(String url) {
            this.feedAttachment.setUrl(url);
            return this;
        }

        /**
         * Mime type of the attachment.
         *
         * @param mimeType mime type of the attachment
         * @return The Builder
         */
        public Builder mimeType(String mimeType) {
            this.feedAttachment.setMimeType(mimeType);
            return this;
        }

        /**
         * Name of the attachment.
         *
         * @param title name of the attachment
         * @return The Builder
         */
        public Builder title(String title) {
            this.feedAttachment.setTitle(title);
            return this;
        }

        /**
         * Specifies how large the file is in bytes.
         *
         * @param sizeInBytes sive of the file in bytes
         * @return The Builder
         */
        public Builder sizeInBytes(Long sizeInBytes) {
            this.feedAttachment.setSizeInBytes(sizeInBytes);
            return this;
        }

        /**
         * Specifies how long it takes to listen to or watch, when played at normal speed.
         *
         * @param durationInSeconds duration of the attachment in seconds
         * @return The Builder
         */
        public Builder durationInSeconds(Integer durationInSeconds) {
            this.feedAttachment.setDurationInSeconds(durationInSeconds);
            return this;
        }

        public JsonFeedAttachment build() {
            return this.feedAttachment;
        }
    }
}
