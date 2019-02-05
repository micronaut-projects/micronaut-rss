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

package io.micronaut.rss.itunespodcast;

/**
 * Itunes podcast enclosure types.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
public enum ItunesPodcastEnclosureType {
    AUDIO_X_M4A("audio/x-m4a"),
    AUDIO_MPEG("audio/mpeg"),
    VIDEO_QUICKTIME("video/quicktime"),
    VIDEO_MP4("video/mp4"),
    VIDEO_XM4V("video/x-m4v"),
    APPLICATION_PDF("application/pdf"),
    DOCUMENT_X_EPUB("document/x-epub");

    private final String type;

    /**
     * Constructor.
     *
     * @param type The order of type
     */
    ItunesPodcastEnclosureType(String type) {
        this.type = type;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }
}
