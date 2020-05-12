package io.micronaut.rss.http

import io.micronaut.context.ApplicationContext
import io.micronaut.core.annotation.AnnotationMetadataResolver
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.core.util.PathMatcher
import io.micronaut.core.util.StringUtils
import io.micronaut.http.annotation.Controller
import io.micronaut.rss.http.FeedController
import spock.lang.Specification

class FeedControllerPathSpec extends Specification {

    void "Default FeedController path is /feed"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                'spec.name': getClass().simpleName
        ])

        when:
        FeedController filter = context.getBean(FeedController)

        then:
        noExceptionThrown()

        when:
        AnnotationMetadataResolver annotationMetadataResolver = context.getBean(AnnotationMetadataResolver)

        then:
        noExceptionThrown()

        when:
        String[] patterns
        Optional<AnnotationValue<Controller>> filterOpt = annotationMetadataResolver.resolveMetadata(filter).findAnnotation(Controller.class)
        if (filterOpt.isPresent()) {
            AnnotationValue<Controller> filterAnn = filterOpt.get()
            patterns = filterAnn.getValue(String[].class).orElse(StringUtils.EMPTY_STRING_ARRAY)
        }

        then:
        patterns

        when:
        String requestPath = "/feed"
        boolean matches = PathMatcher.ANT.matches(patterns.first(), requestPath)

        then:
        matches

        cleanup:
        context.stop()
        context.close()
    }

    void "you can customize FeedController path with micronaut.rss.feed.path"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                'micronaut.rss.feed.path': '/rss',
                'spec.name': getClass().simpleName
        ])

        when:
        FeedController filter = context.getBean(FeedController)

        then:
        noExceptionThrown()

        when:
        AnnotationMetadataResolver annotationMetadataResolver = context.getBean(AnnotationMetadataResolver)

        then:
        noExceptionThrown()

        when:
        String[] patterns
        Optional<AnnotationValue<Controller>> filterOpt = annotationMetadataResolver.resolveMetadata(filter).findAnnotation(Controller.class)
        if (filterOpt.isPresent()) {
            AnnotationValue<Controller> filterAnn = filterOpt.get()
            patterns = filterAnn.getValue(String[].class).orElse(StringUtils.EMPTY_STRING_ARRAY)
        }

        then:
        patterns

        when:
        String requestPath = "/rss"
        boolean matches = PathMatcher.ANT.matches(patterns.first(), requestPath)

        then:
        matches

        when:
        requestPath = "/feed"
        matches = PathMatcher.ANT.matches(patterns.first(), requestPath)

        then:
        !matches

        cleanup:
        context.stop()
        context.close()
    }
}
