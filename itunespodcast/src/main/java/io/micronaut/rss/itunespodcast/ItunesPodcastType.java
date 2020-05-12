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

package io.micronaut.rss.itunespodcast;

import java.util.HashMap;
import java.util.Map;

/**
 * Representation of iTunesPodcastType.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
public enum ItunesPodcastType {
    /**
     * For non-chronological episodes that will behave as they have for years and download the latest episode.
     */
    EPISODIC,

    /**
     * for chronological episodes that should be consumed oldest to newest.
     */
    SERIAL;

    /**
     *
     * @return Returns a map representation.
     */
    public Map<String, Object> toMap() {
        Map m = new HashMap();
        m.put("name", this.name());
        m.put("value", this.name());
        return m;
    }
}
