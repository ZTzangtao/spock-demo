package com.foamfish.spock.example.service

import com.foamfish.spock.example.dto.Hello
import com.foamfish.spock.example.external.ExternalApiClient
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@SpringBootTest(webEnvironment = NONE)
class BarServiceSpec extends Specification {
    @Subject BarService service

    ExternalApiClient client = Mock()

    def setup() {
        service = new BarService(client)
    }

    def 'hello() should return a named bean'() {
        given:
        1 * client.findByName('happy') >> new Hello('happy')
        println System.getProperty("java.version")

        when:
        def hello = service.hello('happy')

        then:
        hello.name == 'happy'
    }
}