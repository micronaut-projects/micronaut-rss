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
    ARTS_BOOKS(Arrays.asList("Arts", "Books")),
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
    BUSINESS_MANAGEMENT(Arrays.asList("Business", "Management")),
    BUSINESS_MARKETING(Arrays.asList("Business", "Marketing")),
    BUSINESS_NONPROFIT(Arrays.asList("Business", "Non-profit")),
    BUSINESS_MANAGEMENT_AND_MARKETING(Arrays.asList("Business", "Management &amp; Marketing")),
    BUSINESS_SHOPPING(Arrays.asList("Business", "Shopping")),
    COMEDY(Arrays.asList("Comedy")),
    COMEDY_INTERVIEWS(Arrays.asList("Comedy", "Comedy Interviews")),
    COMEDY_IMPROV(Arrays.asList("Comedy", "Improv")),
    COMEDY_STANDUP(Arrays.asList("Comedy", "Standup")),

    EDUCATION(Arrays.asList("Education")),
    EDUCATION_COURSES(Arrays.asList("Education", "Courses")),
    EDUCATION_HOWTO(Arrays.asList("Education", "How To")),
    EDUCATION_SELFIMPROVEMENT(Arrays.asList("Education", "Self-Improvement")),
    EDUCATION_EDUCATIONAL_TECHNOLOGY(Arrays.asList("Education", "Educational Technology")),
    EDUCATION_HIGHER_EDUCATION(Arrays.asList("Education", "Higher Education")),
    EDUCATION_K12(Arrays.asList("Education", "K-12")),
    EDUCATION_LANGUAGE_COURSES(Arrays.asList("Education", "Language Courses")),
    EDUCATION_TRAINING(Arrays.asList("Education", "Training")),

    GOVERNMENT(Arrays.asList("Government")),
    HISTORY(Arrays.asList("History")),

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
    HEALTH_FITNESS(Arrays.asList("Health", "Fitness")),
    HEALTH_MEDICINE(Arrays.asList("Health", "Medicine")),
    HEALTH_MENTAL_HEALTH(Arrays.asList("Health", "Mental Health")),
    HEALTH_NUTRITION(Arrays.asList("Health", "Nutrition")),
    HEALTH_SEXUALITY(Arrays.asList("Health", "Sexuality")),
    HEALTH_ALTERNATIVE_HEALTH(Arrays.asList("Health", "Alternative Health")),
    HEALTH_FITNESS_NUTRITION(Arrays.asList("Health", "Fitness &amp; Nutrition")),
    HEALTH_SELF_HELP(Arrays.asList("Health", "Self-Help")),

    KIDS_AND_FAMILY(Arrays.asList("Kids &amp; Family")),
    KIDS_AND_FAMILY_EDUCATION_FOR_KIDS(Arrays.asList("Kids &amp; Family", "Education for Kids")),
    KIDS_AND_FAMILY_PARENTING(Arrays.asList("Kids &amp; Family", "Parenting")),
    KIDS_AND_FAMILY_PETS_AND_ANIMALS(Arrays.asList("Kids &amp; Family", "Pets &amp; Animals")),
    KIDS_AND_FAMILY_STORIES_FOR_KIDS(Arrays.asList("Kids &amp; Family", "Stories for Kids")),

    LEISURE(Arrays.asList("Leisure")),
    LEISURE_ANIMATION_AND_MANGA(Arrays.asList("Leisure", "Animation &amp; Manga")),
    LEISURE_AUTOMOTIVE(Arrays.asList("Leisure", "Automotive")),
    LEISURE_AVIATION(Arrays.asList("Leisure", "Aviation")),
    LEISURE_CRAFTS(Arrays.asList("Leisure", "Crafts")),
    LEISURE_GAMES(Arrays.asList("Leisure", "Games")),
    LEISURE_HOBBIES(Arrays.asList("Leisure", "Hobbies")),
    LEISURE_HOME_AND_GARDEN(Arrays.asList("Leisure", "Home &amp; Garden")),
    LEISURE_VIDEO_GAMES(Arrays.asList("Leisure", "Video Games")),

    MUSIC(Arrays.asList("Music")),
    MUSIC_COMMENTARY(Arrays.asList("Music", "Music Commentary")),
    MUSIC_HISTORY(Arrays.asList("Music", "Music History")),
    MUSIC_INTERVIEWS(Arrays.asList("Music", "Music Interviews")),

    NEWS_AND_POLITICS(Arrays.asList("News &amp; Politics")),

    NEWS(Arrays.asList("News")),
    NEWS_BUSINESS_NEWS(Arrays.asList("News", "Business News")),
    NEW_DAILY_NEWS(Arrays.asList("News", "Daily News")),
    NEWS_ENTERTAINMENT_NEWS(Arrays.asList("News", "Entertainment News")),
    NEWS_NEWS_COMMENTARY(Arrays.asList("News", "News Commentary")),
    NEWS_POLITICS(Arrays.asList("News", "Politics")),
    NEWS_SPORTS_NEWS(Arrays.asList("News", "Sports News")),
    NEWS_TECH_NEWS(Arrays.asList("News", "Tech News")),

    RELIGION_AND_SPIRITUALITY(Arrays.asList("Religion &amp; Spirituality")),
    RELIGION_AND_SPIRITUALITY_BUDDHISM(Arrays.asList("Religion &amp; Spirituality", "Buddhism")),
    RELIGION_AND_SPIRITUALITY_CHRISTIANITY(Arrays.asList("Religion &amp; Spirituality", "Christianity")),
    RELIGION_AND_SPIRITUALITY_HINDUISM(Arrays.asList("Religion &amp; Spirituality", "Hinduism")),
    RELIGION_AND_SPIRITUALITY_ISLAM(Arrays.asList("Religion &amp; Spirituality", "Islam")),
    RELIGION_AND_SPIRITUALITY_JUDAISM(Arrays.asList("Religion &amp; Spirituality", "Judaism")),
    RELIGION_AND_SPIRITUALITY_OTHER(Arrays.asList("Religion &amp; Spirituality", "Other")),
    RELIGION_AND_SPIRITUALITY_SPIRITUALITY(Arrays.asList("Religion &amp; Spirituality", "Spirituality")),

    SCIENCE(Arrays.asList("Science")),
    SCIENCE_ASTRONOMY(Arrays.asList("Science", "Astronomy")),
    SCIENCE_CHEMISTRY(Arrays.asList("Science", "Chemistry")),
    SCIENCE_EARTH_SCIENCES(Arrays.asList("Science", "Earth Sciences")),
    SCIENCE_LIFE_SCIENCES(Arrays.asList("Science", "Life Sciences")),
    SCIENCE_MATHEMATICS(Arrays.asList("Science", "Mathematics")),
    SCIENCE_NATURAL_SCIENCES(Arrays.asList("Science", "Natural Sciences")),
    SCIENCE_NATURE(Arrays.asList("Science", "Nature")),
    SCIENCE_PHYSICS(Arrays.asList("Science", "Physics")),
    SCIENCE_SOCIAL_SCIENCES(Arrays.asList("Science", "Social Sciences")),

    SCIENCE_MEDICINE(Arrays.asList("Science &amp; Medicine")),
    SCIENCE_MEDICINE_MEDICINE(Arrays.asList("Science &amp; Medicine", "Medicine")),
    SCIENCE_MEDICINE_NATURAL_SCIENCES(Arrays.asList("Science &amp; Medicine", "Natural Sciences")),
    SCIENCE_MEDICINE_SOCIAL_SCIENCES(Arrays.asList("Science &amp; Medicine", "Social Sciences")),

    SOCIETY_CULTURE(Arrays.asList("Society &amp; Culture")),
    SOCIETY_CULTURE_HISTORY(Arrays.asList("Society &amp; Culture", "History")),
    SOCIETY_CULTURE_PERSONAL_JOURNALS(Arrays.asList("Society &amp; Culture", "Personal Journals")),
    SOCIETY_CULTURE_PHILOSOPHY(Arrays.asList("Society &amp; Culture", "Philosophy")),
    SOCIETY_CULTURE_PLACES_AND_TRAVEL(Arrays.asList("Society &amp; Culture", "Places &amp; Travel")),
    SOCIETY_CULTURE_RELATIONSHIPS(Arrays.asList("Society &amp; Culture", "Relationships")),

    SOCIETY_AND_CULTURE(Arrays.asList("Society &amp; Culture")),
    SOCIETY_AND_CULTURE_DOCUMENTARY(Arrays.asList("Society &amp; Culture", "Documentary")),
    SOCIETY_AND_CULTURE_HISTORY(Arrays.asList("Society &amp; Culture", "History")),
    SOCIETY_AND_CULTURE_PERSONAL_JOURNALS(Arrays.asList("Society &amp; Culture", "Personal Journals")),
    SOCIETY_AND_CULTURE_PHILOSOPHY(Arrays.asList("Society &amp; Culture", "Philosophy")),
    SOCIETY_AND_CULTURE_PLACES_AND_TRAVEL(Arrays.asList("Society &amp; Culture", "Places &amp; Travel")),
    SOCIETY_AND_CULTURE_RELATIONSHIPS(Arrays.asList("Society &amp; Culture", "Relationships")),

    SPORTS(Arrays.asList("Sports")),
    SPORTS_BASEBALL(Arrays.asList("Sports", "Baseball")),
    SPORTS_BASKETBALL(Arrays.asList("Sports", "Basketball")),
    SPORTS_CRICKET(Arrays.asList("Sports", "Cricket")),
    SPORTS_FANTASY(Arrays.asList("Sports", "Fantasy")),
    SPORTS_FOOTBALL(Arrays.asList("Sports", "Football")),
    SPORTS_GOLF(Arrays.asList("Sports", "Golf")),
    SPORTS_HOCKEY(Arrays.asList("Sports", "Hockey")),
    SPORTS_RUGBY(Arrays.asList("Sports", "Rugby")),
    SPORTS_RUNNING(Arrays.asList("Sports", "Running")),
    SPORTS_SOCCER(Arrays.asList("Sports", "Soccer")),
    SPORTS_SWIMMING(Arrays.asList("Sports", "Swimming")),
    SPORTS_TENNIS(Arrays.asList("Sports", "Tennis")),
    SPORTS_OUTDOOR(Arrays.asList("Sports", "Outdoor")),
    SPORTS_VOLLEYBALL(Arrays.asList("Sports", "Volleyball")),
    SPORTS_WILDERNESS(Arrays.asList("Sports", "Wilderness")),
    SPORTS_WRESTLING(Arrays.asList("Sports", "Wrestling")),

    SPORTS_AND_RECREATION(Arrays.asList("Sports &amp; Recreation")),
    SPORTS_AND_RECREATION_AMATEUR(Arrays.asList("Sports &amp; Recreation", "Amateur")),
    SPORTS_AND_RECREATION_COLLEGE_AND_HIGH_SCHOOL(Arrays.asList("Sports &amp; Recreation", "College &amp; High School")),
    SPORTS_AND_RECREATION_OUTDOOR(Arrays.asList("Sports &amp; Recreation", "Outdoor")),
    SPORTS_AND_RECREATION_PROFESSIONAL(Arrays.asList("Sports &amp; Recreation", "Professional")),

    TRUE_CRIME(Arrays.asList("True Crime")),

    TECHNOLOGY(Arrays.asList("Technology")),
    TECHNOLOGY_Gadgets(Arrays.asList("Technology", "Gadgets")),
    TECHNOLOGY_TECH_NEWS(Arrays.asList("Technology", "Tech News")),
    TECHNOLOGY_PODCASTING(Arrays.asList("Technology", "Podcasting")),
    TECHNOLOGY_SOFTWARE_HOWTO(Arrays.asList("Technology", "Software How-To")),

    TV_AND_FILM(Arrays.asList("TV &amp; Film")),
    TV_AND_FILM_AFTERSHOWS(Arrays.asList("TV &amp; Film", "Aftershows")),
    TV_AND_FILM_FILM_HISTORY(Arrays.asList("TV &amp; Film", "Film History")),
    TV_AND_FILM_FILM_INTERVIEWS(Arrays.asList("TV &amp; Film", "Film Interviews")),
    TV_AND_FILM_FILM_REVIEWS(Arrays.asList("TV &amp; Film", "Film Reviews")),
    TV_AND_FILM_TV_REVIEWS(Arrays.asList("TV &amp; Film", "TV Reviews"));

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
