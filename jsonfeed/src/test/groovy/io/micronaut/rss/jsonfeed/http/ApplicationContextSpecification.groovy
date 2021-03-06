package io.micronaut.rss.jsonfeed.http

import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.validation.Validator

abstract class ApplicationContextSpecification extends Specification implements ConfigurationFixture {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run(configuration)

    @Shared
    Validator validator = applicationContext.getBean(Validator)

    @Shared
    ObjectMapper objectMapper = applicationContext.getBean(ObjectMapper)
}
