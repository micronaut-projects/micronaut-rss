package io.micronaut.rss

import spock.lang.Specification

class RssLanguageSpec extends Specification {

    void "RssLanguage::of is a static method"() {
        expect:
        RssLanguage.LANG_SPANISH == RssLanguage.of('es').get()
    }
}
