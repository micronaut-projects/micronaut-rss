package io.micronaut.rss.jsonfeed

import io.micronaut.context.ApplicationContext
import io.micronaut.json.JsonMapper
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import jakarta.validation.Validator

abstract class ApplicationContextSpecification extends Specification implements ConfigurationFixture {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run(configuration)

    @Shared
    Validator validator = applicationContext.getBean(Validator)

    @Shared
    JsonMapper objectMapper = applicationContext.getBean(JsonMapper)
}
