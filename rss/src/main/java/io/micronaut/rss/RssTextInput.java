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

/**
 * @see <a href="https://cyber.harvard.edu/rss/rss.html#lttextinputgtSubelementOfLtchannelgt">RSS text Input</a>
 *
 * @author Sergio del Amo
 * @since 1.0
 */
public class RssTextInput {

    /**
     * The label of the Submit button in the text input area.
     */
    private String title;

    /**
     * Explains the text input area.
     */
    private String description;

    /**
     * The name of the text object in the text input area.
     */
    private String name;

    /**
     * The URL of the CGI script that processes text input requests.
     */
    private String link;

    /**
     *
     * @return The label of the Submit button in the text input area.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the label of the Submit button in the text input area.
     * @param title label of the submit button.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Explains the text input area.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the explanation of the text input area.
     * @param description The text area description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return The name of the text object in the text input area
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the text object in the text input area.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return The URL of the CGI script that processes text input requests.
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the URL of the CGI script that processes text input requests.
     * @param link the CGI script URL.
     */
    public void setLink(String link) {
        this.link = link;
    }
}
