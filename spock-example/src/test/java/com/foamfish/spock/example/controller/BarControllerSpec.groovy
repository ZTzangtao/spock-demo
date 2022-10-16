package com.foamfish.spock.example.controller

import com.foamfish.spock.example.config.IntegrationTestConfiguration
import com.foamfish.spock.example.dto.Hello
import com.foamfish.spock.example.external.ExternalApiClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(IntegrationTestConfiguration)
class BarControllerSpec extends Specification {
    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    ExternalApiClient client

    @Autowired
    ApplicationContext context

    def "test context loads"() {
        expect:
        context != null
        context.containsBean("externalApiClient")
        context.containsBean("externalApiClientMock")
        !context.containsBean("scopedTarget.externalApiClient")
        !context.containsBean("scopedTarget.externalApiClientMock")
        !client.class.simpleName.startsWith('externalApiClient$$EnhancerBySpringCGLIB$$')

    }

    def '/bar/you should return world'() {
        given: 'mock 数据 并 只触发一次 findByName'
        1 * client.findByName('happy') >> new Hello('happy')

        when:
        def entity = restTemplate.getForEntity('/bar/happy', Hello)

        then:
        with(entity) {
            statusCode == HttpStatus.OK
            body.name == 'happy'
        }

    }

//    @TestConfiguration
//    static class IntegrationTestConfiguration {
//        private final detachedMockFactory = new DetachedMockFactory()
//
//        @Bean
//        @Primary
//        ExternalApiClient externalApiClientMock() {
//            detachedMockFactory.Mock(ExternalApiClient)
//        }
//    }
}