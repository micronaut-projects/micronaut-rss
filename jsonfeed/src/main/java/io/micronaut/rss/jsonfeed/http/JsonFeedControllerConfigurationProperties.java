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
import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

/**
 * {@link ConfigurationProperties} implementation of {@link JsonFeedControllerConfiguration}.
 * @author Sergio del Amo
 * @since 2.2.0
 */
@ConfigurationProperties(JsonFeedControllerConfigurationProperties.PREFIX)
public class JsonFeedControllerConfigurationProperties implements JsonFeedControllerConfiguration {
    public static final String PREFIX = "jsonfeed";

    @SuppressWarnings("WeakerAccess")
    public static final String DEFAULT_ROOT_PATH = "/feeds";

    @SuppressWarnings("WeakerAccess")
    public static final String DEFAULT_PATH = "/json";

    /**
     * The default enable value.
     */
    @SuppressWarnings("WeakerAccess")
    public static final boolean DEFAULT_ENABLED = true;

    private boolean enabled = DEFAULT_ENABLED;

    @NonNull
    @NotBlank
    private String rootPath = DEFAULT_ROOT_PATH;

    @NonNull
    @NotBlank
    private String path = DEFAULT_PATH;

    @Override
    @NonNull
    public String getRootPath() {
        return rootPath;
    }

    /**
     * Configures {@link io.micronaut.rss.jsonfeed.http.JsonFeedController} rootpath. Default value {@value #DEFAULT_ROOT_PATH}
     * @param rootPath Path to be matched by Token Propagation Filter.
     */
    public void setRootPath(@NonNull String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    @NonNull
    public String getPath() {
        return path;
    }

    /**
     * Configures {@link io.micronaut.rss.jsonfeed.http.JsonFeedController} path. Default value {@value #DEFAULT_PATH}
     * @param path Path to be matched by Token Propagation Filter.
     */
    public void setPath(@NonNull String path) {
        this.path = path;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Whether {@link io.micronaut.rss.jsonfeed.http.JsonFeedController} should be enabled. Default value ({@value #DEFAULT_ENABLED}).
     * @param enabled enabled flag
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
