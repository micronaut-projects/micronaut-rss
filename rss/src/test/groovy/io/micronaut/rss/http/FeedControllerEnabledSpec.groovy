package io.micronaut.rss.http

import io.micronaut.context.ApplicationContext
import io.micronaut.rss.http.FeedController
import spock.lang.Specification

class FeedControllerEnabledSpec extends Specification {

    void "FeedController can be disabled with micronaut.rss.feed.enabled=false"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                'micronaut.rss.feed.enabled': false,
                'spec.name': getClass().simpleName
        ])

        expect:
        !context.containsBean(FeedController)

        cleanup:
        context.close()
    }

    void "FeedController is enabled by default"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                'spec.name': getClass().simpleName
        ])

        expect:
        context.containsBean(FeedController)

        cleanup:
        context.close()
    }
}
