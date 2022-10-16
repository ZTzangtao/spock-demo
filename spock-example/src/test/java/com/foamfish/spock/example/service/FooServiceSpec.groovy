package com.foamfish.spock.example.service

import com.foamfish.spock.example.dto.Hello
import com.foamfish.spock.example.external.ExternalApiClient
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE


@SpringBootTest(webEnvironment = NONE)
class FooServiceSpec extends Specification {
    @Subject FooService service

    ExternalApiClient client = Mock()

    def setup() {
        service = new FooService(client)
    }

    def 'hello() should return world'() {
        given:
        1 * client.getDefault() >> new Hello('world')

        when:
        def hello = service.hello()

        then:
        hello.name == 'world'
    }
}
