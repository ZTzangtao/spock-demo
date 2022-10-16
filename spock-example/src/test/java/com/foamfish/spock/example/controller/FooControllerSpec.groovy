package com.foamfish.spock.example.controller

import com.foamfish.spock.example.config.IntegrationTestConfiguration
import com.foamfish.spock.example.dto.Hello
import com.foamfish.spock.example.external.ExternalApiClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(IntegrationTestConfiguration)
class FooControllerSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    ExternalApiClient client

    def '/foo should return world'() {
        given:
        1 * client.getDefault() >> new Hello('world')

        when:
        def entity = restTemplate.getForEntity('/foo', Hello)

        then:
        with(entity) {
            statusCode == HttpStatus.OK
            body.name == 'world'
        }
    }
}