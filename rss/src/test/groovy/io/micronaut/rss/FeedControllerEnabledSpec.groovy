package io.micronaut.rss

import io.micronaut.context.ApplicationContext
import spock.lang.Specification

class FeedControllerEnabledSpec extends Specification {
    static final SPEC_NAME_PROPERTY = 'spec.name'

    void "FeedController can be disabled with micronaut.rss.feed.enabled=false"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                'micronaut.rss.feed.enabled': false,
                (SPEC_NAME_PROPERTY): getClass().simpleName
        ])

        expect:
        !context.containsBean(FeedController)

        cleanup:
        context.close()
    }

    void "FeedController is enabled by default"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                (SPEC_NAME_PROPERTY): getClass().simpleName
        ])

        expect:
        context.containsBean(FeedController)

        cleanup:
        context.close()
    }
}
