package io.micronaut.rss

import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.core.annotation.AnnotationMetadataResolver
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.core.util.PathMatcher
import io.micronaut.core.util.StringUtils
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Filter
import spock.lang.Specification

class FeedControllerPathSpec extends Specification {

    static final SPEC_NAME_PROPERTY = 'spec.name'

    void "Default FeedController path is /feed"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                (SPEC_NAME_PROPERTY):getClass().simpleName
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
                (SPEC_NAME_PROPERTY):getClass().simpleName
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
