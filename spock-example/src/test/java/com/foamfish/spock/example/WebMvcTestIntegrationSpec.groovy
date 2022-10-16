package com.foamfish.spock.example

import com.foamfish.spock.example.controller.HelloWorldController
import com.foamfish.spock.example.service.BarService
import com.foamfish.spock.example.service.FooService
import com.foamfish.spock.example.service.HelloWorldService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = HelloWorldController)
class WebMvcTestIntegrationSpec extends Specification {

    @Autowired
    MockMvc mvc

    @Autowired
    HelloWorldService helloWorldService

    def "spring context loads for web mvc slice"() {
        given:
        helloWorldService.getHelloMessage() >> 'hello world'

        expect: "controller is available"
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello world"))
    }

    def "spring context loads for web mvc slice2"() {
        given:
        helloWorldService.getHelloMessage() >> 'hello world'

        when: "controller is called"
        def response = mvc.perform(MockMvcRequestBuilders.get("/")).andReturn().response

        then: "controller is available"
        with(response) {
            status == HttpStatus.OK.value
            contentAsString == "hello world"
        }

    }


    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        HelloWorldService helloWorldService() {
            return detachedMockFactory.Stub(HelloWorldService)
        }

    }
}
