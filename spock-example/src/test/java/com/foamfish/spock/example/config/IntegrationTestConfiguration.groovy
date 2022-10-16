package com.foamfish.spock.example.config

import com.foamfish.spock.example.external.ExternalApiClient
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import spock.mock.DetachedMockFactory

@TestConfiguration
class IntegrationTestConfiguration {
    private final detachedMockFactory = new DetachedMockFactory()

    @Bean
    @Primary
    ExternalApiClient externalApiClientMock() {
        detachedMockFactory.Mock(ExternalApiClient)
    }
}