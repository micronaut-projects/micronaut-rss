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
package io.micronaut.rss.jsonfeed.http;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.rss.jsonfeed.JsonFeed;
import org.reactivestreams.Publisher;

/**
 * Defines a provider of a JSON Feed.
 *
 * @author Sergio del Amo
 * @author Graeme Rocher
 * @since 1.0
 */
public interface JsonFeedProvider {

    /**
     * Retrieves a JSON Feed. You can use the paramters `maxNumberOfItems` and `pageNumber` in combination with {@link JsonFeed#getNextURL()}
     * @param maxNumberOfItems desired max number of items in the the JSON Feed
     * @param pageNumber Page number to allow pagination in the JSON Feed Items.
     * @return A Json Feed
     */
    @NonNull
    Publisher<JsonFeed> feed(@Nullable Integer maxNumberOfItems,
                             @Nullable Integer pageNumber);

    /**
     * Retrieves a JSON Feed.
     * @return A Json Feed
     */
    @NonNull
    default Publisher<JsonFeed> feed() {
        return feed(null, null);
    }
}
