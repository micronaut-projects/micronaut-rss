package io.micronaut.rss.jsonfeed.http

import groovy.json.JsonSlurper
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import spock.lang.PendingFeature

class JsonFeedControllerSpec extends EmbeddedServerSpecification {

    @Override
    String getSpecName() {
        'JsonFeedControllerSpec'
    }

    @PendingFeature
    void "/feeds/json returns a json feed if JsonFeedProvider exists"() {
        given:
        String expected = '''
//tag::json[]
{
    "version": "https://jsonfeed.org/version/1.1",
    "title": "My Example Feed",
    "home_page_url": "https://example.org/",
    "feed_url": "https://example.org/feed.json",
    "items": [
        {
            "id": "2",
            "content_text": "This is a second item.",
            "url": "https://example.org/second-item"
        },
        {
            "id": "1",
            "content_html": "<p>Hello, world!</p>",
            "url": "https://example.org/initial-post"
       }
    ]
}        
//end::json[]
'''

        expect:
        applicationContext.containsBean(JsonFeedProvider)

        when:
        HttpResponse<?> rsp = client.exchange(HttpRequest.GET('/feeds/json'), String)

        then:
        noExceptionThrown()
        rsp.status() == HttpStatus.OK
        rsp.header("Content-Type") == 'application/json+feed'
        rsp.getBody(String).isPresent()

        when:
        def result = new JsonSlurper().parseText(rsp.getBody(String).get())
        expected = expected.replaceAll("//end::json\\[]", "").replaceAll("//tag::json\\[]", "")
        def m = new JsonSlurper().parseText(expected)

        then:
        m['version'] == result['version']
        m['title'] == result['title']
        m['home_page_url'] == result['home_page_url']
        m['feed_url'] ==  result['feed_url']
        result['items']
        m['items'].size() == result['items'].size()
        m['items'][0].id == result['items'][0].id
        m['items'][0]['content_text'] == result['items'][0]['content_text']
        m['items'][0]['url'] == result['items'][0]['url']

        m['items'][1].id == result['items'][1].id
        m['items'][1]['content_text'] == result['items'][1]['content_text']
        m['items'][1]['url'] == result['items'][1]['url']
    }
}
