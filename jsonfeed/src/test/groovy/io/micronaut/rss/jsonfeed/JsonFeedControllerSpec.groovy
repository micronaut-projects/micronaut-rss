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
package io.micronaut.rss.jsonfeed

import groovy.json.JsonSlurper
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class JsonFeedControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    ApplicationContext context = ApplicationContext.run(['spec.name'                   : getClass().simpleName,
                                                         'micronaut.rss.feed.jsonfeed': true])

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = context.getBean(EmbeddedServer).start()

    @Shared
    @AutoCleanup
    RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    void "fetch feed"() {
        expect:
        embeddedServer.applicationContext.containsBean(DefaultJsonFeedRenderer)

        when:
        JsonFeedRenderer rssFeedRenderer = embeddedServer.applicationContext.getBean(JsonFeedRenderer)

        then:
        noExceptionThrown()
        rssFeedRenderer instanceof DefaultJsonFeedRenderer

        when:
        HttpRequest request = HttpRequest.GET('/feed')
        String rsp = client.toBlocking().retrieve(request)
        def json = new JsonSlurper().parseText(rsp);

        then:
        json.version == "https://jsonfeed.org/version/1.1"
        json.user_comment == "This is a podcast feed. You can add this feed to your podcast client using the following URL: http://therecord.co/feed.json"
        json.title == "The Record"
        json.home_page_url == "http://therecord.co/"
        json.feed_url == "http://therecord.co/feed.json"

        json.hubs.first()
        json.hubs.first().url == "http://test.com"
        json.hubs.first().type == "rssCloud"

        json.authors.find { it.name == "Brent Simmons" }
        json.authors.find { it.name == "Brent Simmons" }.url == "http://example.org/"
        json.authors.find { it.name == "Brent Simmons" }.avatar == "https://example.org/avatar.png"

        json.items.find { it.id == "1" }
        json.items.find { it.id == "1" }.tags.first() == "test"
        json.items.find { it.id == "1" }.content_text == "This is a second item."
        json.items.find { it.id == "1" }.url == "https://example.org/second-item"

        json.items.find { it.id == "2" }
        json.items.find { it.id == "2" }.title == "Special #1 - Chris Parrish"
        json.items.find { it.id == "2" }.url == "http://therecord.co/chris-parrish"
        json.items.find { it.id == "2" }.content_text == "Chris has worked at Adobe and as a founder of Rogue Sheep, which won an Apple Design Award for Postage. Chris’s new company is Aged & Distilled with Guy English — which shipped Napkin, a Mac app for visual collaboration. Chris is also the co-host of The Record. He lives on Bainbridge Island, a quick ferry ride from Seattle."
        json.items.find { it.id == "2" }.content_html == "Chris has worked at <a href=\"http://adobe.com/\">Adobe</a> and as a founder of Rogue Sheep, which won an Apple Design Award for Postage. Chris’s new company is Aged & Distilled with Guy English — which shipped <a href=\"http://aged-and-distilled.com/napkin/\">Napkin</a>, a Mac app for visual collaboration. Chris is also the co-host of The Record. He lives on <a href=\"http://www.ci.bainbridge-isl.wa.us/\">Bainbridge Island</a>, a quick ferry ride from Seattle."
        json.items.find { it.id == "2" }.summary == "Brent interviews Chris Parrish, co-host of The Record and one-half of Aged & Distilled."
        json.items.find { it.id == "2" }.date_published == "Fri, 09 May 2014 14:04:00 GMT"

        def attachment = json.items.find { it.id == "2" }.attachments.first()

        attachment.url == "http://therecord.co/downloads/The-Record-sp1e1-ChrisParrish.m4a"
        attachment.mime_type == "audio/x-m4a"
        attachment.size_in_bytes == 89970236
        attachment.duration_in_seconds == 6629
    }

}

