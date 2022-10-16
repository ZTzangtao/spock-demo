package com.foamfish.spock.example

import com.foamfish.spock.example.controller.HelloWorldController
import com.foamfish.spock.example.service.HelloWorldService
import org.spockframework.spring.SpringBean
import spock.lang.Specification

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = HelloWorldController)
class SpringBeanIntegrationSpec extends Specification {

    @Autowired
    MockMvc mvc

    @SpringBean
    HelloWorldService helloWorldService = Stub()

    def "spring context loads for web mvc slice"() {
        given:
        helloWorldService.getHelloMessage() >> 'hello world'

        expect: "controller is available"
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello world"))
    }
}
