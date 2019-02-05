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

package io.micronaut.rss;

import io.micronaut.context.annotation.ConfigurationProperties;

/**
 * {@link io.micronaut.context.annotation.ConfigurationProperties} for {@link io.micronaut.rss.FeedController}.
 *
 * @author Sergio del Amo
 * @since 1.0
 */
@ConfigurationProperties(RssConfiguration.PREFIX)
public class FeedControllerConfigurationProperties implements FeedControllerConfiguration {

    public static final String PREFIX = RssConfiguration.PREFIX + ".feed";

    /**
     * The default enable value.
     */
    @SuppressWarnings("WeakerAccess")
    public static final boolean DEFAULT_ENABLED = true;

    /**
     * The default path.
     */
    @SuppressWarnings("WeakerAccess")
    public static final String DEFAULT_PATH = "/feed";

    private boolean enabled = DEFAULT_ENABLED;

    private String path = DEFAULT_PATH;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Whether {@link io.micronaut.rss.FeedController} should be enabled. Default value ({@value #DEFAULT_ENABLED}).
     * @param enabled enabled flag
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Configures {@link io.micronaut.rss.FeedController} path. Default value {@value #DEFAULT_PATH}
     * @param path Path to be matched by Token Propagation Filter.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @return the path where {@link io.micronaut.rss.FeedController} listens.
     */
    @Override
    public String getPath() {
        return this.path;
    }
}
