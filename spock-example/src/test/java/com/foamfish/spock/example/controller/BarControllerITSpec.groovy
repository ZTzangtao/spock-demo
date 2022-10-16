package com.foamfish.spock.example.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.foamfish.spock.example.dto.Hello
import com.foamfish.spock.example.service.BarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebMvcTest(controllers = BarController)
class BarControllerITSpec extends Specification {

    @Autowired
    MockMvc mvc

    @Autowired
    BarService service

    @Autowired
    ApplicationContext context

    @Autowired
    ObjectMapper objectMapper

    def "test context loads"() {
        expect:
        context != null
        context.containsBean("barService")
        !context.containsBean("scopedTarget.barService")
        !context.containsBean("scopedTarget.barServiceMock")
        !service.class.simpleName.startsWith('barService$$EnhancerBySpringCGLIB$$')

    }

    def '/bar/you should return world'() {
        given:
        service.hello('dummy') >> new Hello('hello world')

        when: "controller is called"
        def response = mvc.perform(get("/bar/{name}", 'dummy')).andReturn().response

        then: "status is ok"
        response.status == HttpStatus.OK.value()

        and:
        with(objectMapper.readValue(response.contentAsString, Map)) {
            name == "hello world"
        }

    }

    @TestConfiguration
    static class IntegrationTestConfiguration {
        private final detachedMockFactory = new DetachedMockFactory()

        @Bean
        BarService barService() {
            detachedMockFactory.Stub(BarService)
        }
    }
}