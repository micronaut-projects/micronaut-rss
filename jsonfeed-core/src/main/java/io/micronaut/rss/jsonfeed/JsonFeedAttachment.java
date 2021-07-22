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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.annotation.Introspected;
import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * Related resource for a JSON Feed item. Podcasts, for instance, would include an attachment that’s an audio or video file.
 * @see <a href="https://jsonfeed.org/version/1.1">JSON Feed 1.1</a>
 * @author Sergio del Amo, mark1626
 * @since 2.2.0
 */
@Introspected
public class JsonFeedAttachment {

    public static final String KEY_URL = "url";
    public static final String KEY_MIME_TYPE = "mime_type";
    public static final String KEY_TITLE = "title";
    public static final String KEY_SIZE_IN_BYTES = "size_in_bytes";
    public static final String KEY_DURATION_IN_SECONDS = "duration_in_seconds";

    @Nonnull
    @NotBlank
    private String url;

    @JsonProperty(KEY_MIME_TYPE)
    @Nonnull
    @NotBlank
    private String mimeType;

    @Nullable
    private String title;

    @Nullable
    @JsonProperty(KEY_SIZE_IN_BYTES)
    private Long sizeInBytes;

    @Nullable
    @JsonProperty(KEY_DURATION_IN_SECONDS)
    private Integer durationInSeconds;

    /**
     * Constructor.
     */
    public JsonFeedAttachment() {
    }

    /**
     * Gets a Builder with the required fields.
     * @param url the location of the attachment
     * @param mimeType mime type of the attachment
     * @return The Builder
     */
    public static Builder builder(@NonNull String url, @NonNull String mimeType) {
        return new Builder().url(url).mimeType(mimeType);
    }

    /**
     * Gets a Builder with the required fields.
     * @return The Builder
     */
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

    /**
     *
     * @return a Map representation of a JSON Feed Author.
     */
    @NonNull
    public Map<String, Object> toMap() {
        Map<String, Object> m = new HashMap<>();
        if (getUrl() != null) {
            m.put(KEY_URL, getUrl());
        }
        if (getMimeType() != null) {
            m.put(KEY_MIME_TYPE, getMimeType());
        }
        if (getTitle() != null) {
            m.put(KEY_TITLE, getTitle());
        }
        if (getSizeInBytes() != null) {
            m.put(KEY_SIZE_IN_BYTES, getSizeInBytes());
        }
        if (getDurationInSeconds() != null) {
            m.put(KEY_DURATION_IN_SECONDS, getDurationInSeconds());
        }
        return m;
    }

    /**
     * JSON Feed Attachment Builder.
     */
    public static final class Builder {

        private final JsonFeedAttachment feedAttachment = new JsonFeedAttachment();

        /**
         * JSON Feed Attachment Builder Constructor.
         */
        private Builder() {
        }

        /**
         *
         * @param url the location of the attachment
         * @return The Builder
         */
        @NonNull
        public Builder url(@NonNull String url) {
            this.feedAttachment.url = url;
            return this;
        }

        /**
         *
         * @param mimeType mime type of the attachment
         * @return the Builder
         */
        @NonNull
        public Builder mimeType(@NonNull String mimeType) {
            this.feedAttachment.mimeType = mimeType;
            return this;
        }

        /**
         * Name of the attachment.
         *
         * @param title name of the attachment
         * @return The Builder
         */
        @NonNull
        public Builder title(@NonNull String title) {
            this.feedAttachment.setTitle(title);
            return this;
        }

        /**
         * Specifies how large the file is in bytes.
         *
         * @param sizeInBytes sive of the file in bytes
         * @return The Builder
         */
        @NonNull
        public Builder sizeInBytes(@NonNull Long sizeInBytes) {
            this.feedAttachment.setSizeInBytes(sizeInBytes);
            return this;
        }

        /**
         * Specifies how long it takes to listen to or watch, when played at normal speed.
         *
         * @param durationInSeconds duration of the attachment in seconds
         * @return The Builder
         */
        @NonNull
        public Builder durationInSeconds(@NonNull Integer durationInSeconds) {
            this.feedAttachment.setDurationInSeconds(durationInSeconds);
            return this;
        }

        /**
         *
         * @return The Attachment.
         */
        @NonNull
        public JsonFeedAttachment build() {
            return this.feedAttachment;
        }
    }
}
