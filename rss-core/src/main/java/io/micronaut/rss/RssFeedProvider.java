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
package io.micronaut.rss;

import io.micronaut.core.async.annotation.SingleResult;
import org.reactivestreams.Publisher;

import java.io.Serializable;

/**
 * Defines an interface to provide RSS 2.0 feeds.
 * @author Sergio del Amo
 * @since 1.0
 */
public interface RssFeedProvider {

    /**
     *
     * @return the default RSS channel.
     */
    @SingleResult
    Publisher<RssChannel> fetch();

    /**
     *
     * @param id RSS Channel unique identifier.
     * @return An RSS channel identified by the ID parameter.
     */
    @SingleResult
    Publisher<RssChannel> fetchById(Serializable id);
}
