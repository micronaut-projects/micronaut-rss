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

package io.micronaut.rss;

/**
 * RSS SkipDays element.
 * @see <a href="https://cyber.harvard.edu/rss/skipHoursDays.html#skipdays">RSS SkipDays</a>
 *
 * An XML element that contains up to seven <day> sub-elements whose value is . Aggregators may not read the channel during days listed in the skipDays element.
 *
 * @author Sergio del Amo
 * @since 1.0
 */
public enum RssSkipDays {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String dayName;

    /**
     * RssSkipHours Constructor.
     *
     * @param dayName The order of dayName
     */
    RssSkipDays(String dayName) {
        this.dayName = dayName;
    }

    /**
     * @return The dayName
     */
    public String getDayName() {
        return dayName;
    }
}
