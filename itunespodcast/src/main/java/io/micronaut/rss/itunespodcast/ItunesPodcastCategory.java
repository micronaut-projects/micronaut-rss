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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Itunes Podcasts categories.
 * @see <a href="https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12">https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12</a>
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
public enum ItunesPodcastCategory {
    ARTS(Arrays.asList("Arts")),
    ARTS_DESIGN(Arrays.asList("Arts", "Design")),
    ARTS_FASHION_AND_BEAUTY(Arrays.asList("Arts", "Fashion &amp; Beauty")),
    ARTS_FOOD(Arrays.asList("Arts", "Fashion &amp; Food")),
    ARTS_LITERATURE(Arrays.asList("Arts", "Literature")),
    ARTS_PERFORMING_ARTS(Arrays.asList("Arts", "Performing Arts")),
    ARTS_VISUAL_ARTS(Arrays.asList("Arts", "Visual Arts")),
    BUSINESS(Arrays.asList("Business")),
    BUSINESS_BUSINESS_NEWS(Arrays.asList("Business", "Business News")),
    BUSINESS_CAREERS(Arrays.asList("Business", "Careers")),
    BUSINESS_INVESTING(Arrays.asList("Business", "Investing")),
    BUSINESS_MANAGEMENT_AND_MARKETING(Arrays.asList("Business", "Management &amp; Marketing")),
    BUSINESS_SHOPPING(Arrays.asList("Business", "Shopping")),
    COMEDY(Arrays.asList("Comedy")),

    EDUCATION(Arrays.asList("Education")),
    EDUCATION_EDUCATIONAL_TECHNOLOGY(Arrays.asList("Education", "Educational Technology")),
    EDUCATION_HIGHER_EDUCATION(Arrays.asList("Education", "Higher Education")),
    EDUCATION_K12(Arrays.asList("Education", "K-12")),
    EDUCATION_LANGUAGE_COURSES(Arrays.asList("Education", "Language Courses")),
    EDUCATION_TRAINING(Arrays.asList("Education", "Training")),

    GAMES_AND_HOBBIES(Arrays.asList("Games &amp; Hobbies")),
    GAMES_AND_HOBBIES_AUTOMOTIVE(Arrays.asList("Games &amp; Hobbies", "Automotive")),
    GAMES_AND_HOBBIES_AVIATION(Arrays.asList("Games &amp; Hobbies", "Aviation")),
    GAMES_AND_HOBBIES_HOBBIES(Arrays.asList("Games &amp; Hobbies", "Hobbies")),
    GAMES_AND_HOBBIES_OTHER_GAMES(Arrays.asList("Games &amp; Hobbies", "Other Games")),
    GAMES_AND_HOBBIES_VIDEO_GAMES(Arrays.asList("Games &amp; Hobbies", "Video Games")),

    GOVERNMENT_ORGANIZATIONS(Arrays.asList("Government &amp; Organizations")),
    GOVERNMENT_ORGANIZATIONS_LOCAL(Arrays.asList("Government &amp; Organizations", "Local")),
    GOVERNMENT_ORGANIZATIONS_NATIONAL(Arrays.asList("Government &amp; Organizations", "National")),
    GOVERNMENT_ORGANIZATIONS_NONPROFIT(Arrays.asList("Government &amp; Organizations", "Non-Profit")),
    GOVERNMENT_ORGANIZATIONS_REGIONAL(Arrays.asList("Government &amp; Organizations", "Regional")),

    HEALTH(Arrays.asList("Health")),
    HEALTH_ALTERNATIVE_HEALTH(Arrays.asList("Health", "Alternative Health")),
    HEALTH_FITNESS_NUTRITION(Arrays.asList("Health", "Fitness &amp; Nutrition")),
    HEALTH_SELF_HELP(Arrays.asList("Health", "Self-Help")),
    HEALTH_SEXUALITY(Arrays.asList("Health", "Sexuality")),

    KIDS_AND_FAMILY(Arrays.asList("Kids &amp; Family")),

    MUSIC(Arrays.asList("Music")),

    NEWS_AND_POLITICS(Arrays.asList("News &amp; Politics")),

    RELIGION_AND_SPIRITUALITY(Arrays.asList("Religion &amp; Spirituality")),
    RELIGION_AND_SPIRITUALITY_BUDDHISM(Arrays.asList("Religion &amp; Spirituality", "Buddhism")),
    RELIGION_AND_SPIRITUALITY_CHRISTIANITY(Arrays.asList("Religion &amp; Spirituality", "Christianity")),
    RELIGION_AND_SPIRITUALITY_HINDUISM(Arrays.asList("Religion &amp; Spirituality", "Hinduism")),
    RELIGION_AND_SPIRITUALITY_ISLAM(Arrays.asList("Religion &amp; Spirituality", "Islam")),
    RELIGION_AND_SPIRITUALITY_JUDAISM(Arrays.asList("Religion &amp; Spirituality", "Judaism")),
    RELIGION_AND_SPIRITUALITY_OTHER(Arrays.asList("Religion &amp; Spirituality", "Other")),
    RELIGION_AND_SPIRITUALITY_SPIRITUALITY(Arrays.asList("Religion &amp; Spirituality", "Spirituality")),

    SCIENCE_MEDICINE(Arrays.asList("Science &amp; Medicine")),
    SCIENCE_MEDICINE_MEDICINE(Arrays.asList("Science &amp; Medicine", "Medicine")),
    SCIENCE_MEDICINE_NATURAL_SCIENCES(Arrays.asList("Science &amp; Medicine", "Natural Sciences")),
    SCIENCE_MEDICINE_SOCIAL_SCIENCES(Arrays.asList("Science &amp; Medicine", "Social Sciences")),

    SOCIETY_CULTURE(Arrays.asList("Society &amp; Culture")),
    SOCIETY_CULTURE_HISTORY(Arrays.asList("Society &amp; Culture", "History")),
    SOCIETY_CULTURE_PERSONAL_JOURNALS(Arrays.asList("Society &amp; Culture", "Personal Journals")),
    SOCIETY_CULTURE_PHILOSOPHY(Arrays.asList("Society &amp; Culture", "Philosophy")),
    SOCIETY_CULTURE_PLACES_AND_TRAVEL(Arrays.asList("Society &amp; Culture", "Places &amp; Travel")),

    SPORTS_AND_RECREATION(Arrays.asList("Sports &amp; Recreation")),
    SPORTS_AND_RECREATION_AMATEUR(Arrays.asList("Sports &amp; Recreation", "Amateur")),
    SPORTS_AND_RECREATION_COLLEGE_AND_HIGH_SCHOOL(Arrays.asList("Sports &amp; Recreation", "College &amp; High School")),
    SPORTS_AND_RECREATION_OUTDOOR(Arrays.asList("Sports &amp; Recreation", "Outdoor")),
    SPORTS_AND_RECREATION_PROFESSIONAL(Arrays.asList("Sports &amp; Recreation", "Professional")),

    TECHNOLOGY(Arrays.asList("Technology")),
    TECHNOLOGY_Gadgets(Arrays.asList("Technology", "Gadgets")),
    TECHNOLOGY_TECH_NEWS(Arrays.asList("Technology", "Tech News")),
    TECHNOLOGY_PODCASTING(Arrays.asList("Technology", "Podcasting")),
    TECHNOLOGY_SOFTWARE_HOWTO(Arrays.asList("Technology", "Software How-To")),
    TV_AND_FILM(Arrays.asList("TV &amp; Film"));

    private final List<String> categories;

    /**
     * Constructor.
     *
     * @param categories The order of type
     */
    ItunesPodcastCategory(List<String> categories) {
        this.categories = categories;
    }

    /**
     * @return The categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     *
     * @param csvCategory category as a comma separated value string.
     * @return if found a {@link ItunesPodcastCategory} which matches the CSV representation.
     */
    public static Optional<ItunesPodcastCategory> categoryBy(String csvCategory) {
        return Arrays.stream(ItunesPodcastCategory
                .values())
                .filter(c -> c.toCsv().equals(csvCategory))
                .findFirst();
    }

    /**
     *
     * @return Map representation.
     */
    public Map<String, Object> toMap() {
        HashMap<String, Object> m = new HashMap<>();
        m.put("value", toCsv());
        m.put("name", getCategories().stream().reduce((a, b) -> a + " &rarr; " + b).get());
        return m;
    }

    /**
     *
     * @return All categories as comma separated value string.
     */
    public String toCsv() {
        return getCategories().stream().reduce((a, b) -> a + "," + b).get();
    }
}
