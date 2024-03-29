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

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.util.Toggleable;

/**
 * Configuration for {@link JsonFeedController}.
 *
 * @author sdelamo
 * @since 2.2.0
 */
public interface JsonFeedControllerConfiguration extends Toggleable {

    /**
     *
     * @return Configures {@link io.micronaut.rss.jsonfeed.http.JsonFeedController} root path.
     */
    @NonNull
    String getRootPath();

    /**
     *
     * @return Configures {@link io.micronaut.rss.jsonfeed.http.JsonFeedController} path.
     */
    @NonNull
    String getPath();
}
