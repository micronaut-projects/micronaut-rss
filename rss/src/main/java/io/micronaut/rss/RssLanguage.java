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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Languages for RSS.
 *
 * @see <a href="https://cyber.harvard.edu/rss/languages.html">Allowable values for language in RSS</a>
 *
 * @author Sergio del Amo
 * @since 1.0
 */
public enum RssLanguage {

    LANG_AFRIKAANS("Afrikaans", "af"),
    LANG_ALBANIAN("Albanian", "sq"),
    LANG_BASQUE("Basque", "eu"),
    LANG_BELARUSIAN("Belarusian", "be"),
    LANG_BULGARIAN("Bulgarian", "bg"),
    LANG_CATALAN("Catalan", "ca"),
    LANG_CHINESE_SIMPLIFIED("Chinese (Simplified)", "zh-cn"),
    LANG_CHINESE_TRADITIONAL("Chinese (Traditional)", "zh-tw"),
    LANG_CROATIAN("Croatian", "hr"),
    LANG_CZECH("Czech", "cs"),
    LANG_DANISH("Danish", "da"),
    LANG_DUTCH("Dutch", "nl"),
    LANG_DUTCH_BELGIUM("Dutch (Belgium)", "nl-be"),
    LANG_DUTCH_NETHERLANDS("Dutch (Netherlands)", "nl-nl"),
    LANG_ENGLISH("English", "en"),
    LANG_ENGLISH_AUSTRALIA("English (Australia)", "en-au"),
    LANG_ENGLISH_BELIZE("English (Belize)", "en-bz"),
    LANG_ENGLISH_CANANDA("English (Canada)", "en-ca"),
    LANG_ENGLISH_IRELAND("English (Ireland)", "en-ie"),
    LANG_ENGLISH_JAMAICA("English (Jamaica)", "en-jm"),
    LANG_ENGLISH_NEW_ZEALAND("English (New Zealand)", "en-nz"),
    LANG_ENGLISH_PHILLIPINES("English (Phillipines)", "en-ph"),
    LANG_ENGLISH_SOUTH_AFRICA("English (South Africa)", "en-za"),
    LANG_ENGLISH_TRINIDAD("English (Trinidad)", "en-tt"),
    LANG_ENGLISH_UNITED_KINGDOM("English (United Kingdom)", "en-gb"),
    LANG_ENGLISH_UNITED_STATES("English (United States)", "en-us"),
    LANG_ENGLISH_ZIMBABWE("English (Zimbabwe)", "en-zw"),
    LANG_ESTONIAN("Estonian", " et"),
    LANG_FAEROESE("Faeroese", "fo"),
    LANG_FINNISH("Finnish", "fi"),
    LANG_FRENCH("French", "fr"),
    LANG_FRENCH_BELGIUM("French (Belgium)", "fr-be"),
    LANG_FRENCH_CANADA("French (Canada)", "fr-ca"),
    LANG_FRENCH_FRANCE("French (France)", "fr-fr"),
    LANG_FRENCH_LUXEMBOURG("French (Luxembourg)", "fr-lu"),
    LANG_FRENCH_MONACO("French (Monaco)", "fr-mc"),
    LANG_FRENCH_SWITZERLAND("French (Switzerland)", "fr-ch"),
    LANG_GALICIAN("Galician", "gl"),
    LANG_GAELIC("Gaelic", "gd"),
    LANG_GERMAN("German", "de"),
    LANG_GERMAN_AUSTRIA("German (Austria)", "de-at"),
    LANG_GERMAN_GERMANY("German (Germany)", "de-de"),
    LANG_GERMAN_LIECHTENSTEIN("German (Liechtenstein)", "de-li"),
    LANG_GERMAN_LUXEMBOURG("German (Luxembourg)", "de-lu"),
    LANG_GERMAN_SWITZERLAND("German (Switzerland)", "de-ch"),
    LANG_GREEK("Greek", "el"),
    LANG_HAWAIIAN("Hawaiian", "haw"),
    LANG_HUNGARIAN("Hungarian", "hu"),
    LANG_ICENLANDIC("Icelandic", "is"),
    LANG_INDONESIAN("Indonesian", "in"),
    LANG_IRISH("Irish", "ga"),
    LANG_ITALIAN("Italian", "it"),
    LANG_ITALIAN_ITALY("Italian (Italy)", "it-it"),
    LANG_ITALIAN_SWITZERLAND("Italian (Switzerland)", "it-ch"),
    LANG_JAPANESE("Japanese", "ja"),
    LANG_KOREAN("Korean", "ko"),
    LANG_MACEDONIAN("Macedonian", "mk"),
    LANG_NORWEGIAN("Norwegian", "no"),
    LANG_POLISH("Polish", "pl"),
    LANG_PORTUGUESE("Portuguese", "pt"),
    LANG_PORTUGUESE_BRAZIL("Portuguese (Brazil)", "pt-br"),
    LANG_PORTUGUESE_PORTUGAL("Portuguese (Portugal)", "pt-pt"),
    LANG_ROMANIAN("Romanian", "ro"),
    LANG_ROMANIAN_MOLDOVA("Romanian (Moldova)", "ro-mo"),
    LANG_ROMANIAN_ROMANIA("Romanian (Romania)", "ro-ro"),
    LANG_RUSSIAN("Russian", "ru"),
    LANG_RUSSIAN_MOLDOVA("Russian (Moldova)", "ru-mo"),
    LANG_RUSSIAN_RUSSIA("Russian (Russia)", "ru-ru"),
    LANG_SERBIAN("Serbian", "sr"),
    LANG_SLOVAK("Slovak", "sk"),
    LANG_SLOVENIAN("Slovenian", "sl"),
    LANG_SPANISH("Spanish", "es"),
    LANG_SPANISH_ARGENTINA("Spanish (Argentina)", "es-ar"),
    LANG_SPANISH_BOLIVIA("Spanish (Bolivia)", "es-bo"),
    LANG_SPANISH_CHILE("Spanish (Chile)", "es-cl"),
    LANG_SPANISH_COLOMBIA("Spanish (Colombia)", "es-co"),
    LANG_SPANISH_COSTA_RICA("Spanish (Costa Rica)", "es-cr"),
    LANG_SPANISH_DOMNICAN_REPUBLIC("Spanish (Dominican Republic)", "es-do"),
    LANG_SPANISH_ECUADOR("Spanish (Ecuador)", "es-ec"),
    LANG_SPANISH_EL_SALVADOR("Spanish (El Salvador)", "es-sv"),
    LANG_SPANISH_GUATEMALA("Spanish (Guatemala)", "es-gt"),
    LANG_SPANISH_HONDURAS("Spanish (Honduras)", "es-hn"),
    LANG_SPANISH_MEXICO("Spanish (Mexico)", "es-mx"),
    LANG_SPANISH_NICARAGUA("Spanish (Nicaragua)", "es-ni"),
    LANG_SPANISH_PANAMA("Spanish (Panama)", "es-pa"),
    LANG_SPANISH_PARAGUAY("Spanish (Paraguay)", "es-py"),
    LANG_SPANISH_PERU("Spanish (Peru)", "es-pe"),
    LANG_SPANISH_PUERTO_RICO("Spanish (Puerto Rico)", "es-pr"),
    LANG_SPANISH_SPAIN("Spanish (Spain)", "es-es"),
    LANG_SPANISH_URUGUAY("Spanish (Uruguay)", "es-uy"),
    LANG_SPANISH_VENEZUELA("Spanish (Venezuela)", "es-ve"),
    LANG_SWEDISH("Swedish", "sv"),
    LANG_SWEDISH_FINLAND("Swedish (Finland)", "sv-fi"),
    LANG_SWEDISH_SWEDEN("Swedish (Sweden)", "sv-se"),
    LANG_TURKISH("Turkish", "tr"),
    LANG_UKRANIAN("Ukranian", "uk");

    public static final String LANGUAGE_CODE_PATTERN = "af|sq|eu|be|bg|ca|zh-cn|zh-tw|hr|cs|da|nl|nl-be|nl-nl|en|en-au|en-bz|en-ca|en-ie|en-jm|en-nz|en-ph|en-za|en-tt|en-gb|en-us|en-zw|et|fo|fi|fr|fr-be|fr-ca|fr-fr|fr-lu|fr-mc|fr-ch|gl|gd|de|de-at|de-de|de-li|de-lu|de-ch|el|haw|hu|is|in|ga|it|it-it|it-ch|ja|ko|mk|no|pl|pt|pt-br|pt-pt|ro|ro-mo|ro-ro|ru|ru-mo|ru-ru|sr|sk|sl|es|es-ar|es-bo|es-cl|es-co|es-cr|es-do|es-ec|es-sv|es-gt|es-hn|es-mx|es-ni|es-pa|es-py|es-pe|es-pr|es-es|es-uy|es-ve|sv|sv-fi|sv-se|tr|uk";

    private final String languageName;
    private final String languageCode;

    /**
     * Constructs a RssLanguage.
     *
     * @param languageName The name of the language
     * @param languageCode The code of the language.
     */
    RssLanguage(String languageName, String languageCode) {
        this.languageName = languageName;
        this.languageCode = languageCode;
    }

    /**
     * @return The languageName
     */
    public String getLanguageName() {
        return languageName;
    }

    /**
     * @return The languageCode
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     *
     * @return a RssLanguage as Map
     */
    public Map<String, Object> toMap() {
        Map<String, Object> m = new HashMap<>();
        m.put("name", getLanguageName());
        m.put("code", getLanguageCode());
        return m;
    }

    public Optional<RssLanguage> of(String languageCode) {
        return Arrays.stream(RssLanguage.values())
            .filter(lang -> lang.getLanguageCode().equals(languageCode))
            .findFirst();
    }
}
