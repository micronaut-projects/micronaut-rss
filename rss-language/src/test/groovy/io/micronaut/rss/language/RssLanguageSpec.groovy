package io.micronaut.rss.language

import spock.lang.Specification

class RssLanguageSpec extends Specification {

    void "RssLanguage::of is a static method"() {
        expect:
        RssLanguage.LANG_SPANISH == RssLanguage.of('es').get()
    }
}
