package com.foamfish.spock.example

import com.foamfish.spock.example.service.HelloWorldService
import org.spockframework.spring.ScanScopedBeans
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.web.context.annotation.RequestScope
import spock.lang.Specification
import spock.mock.DetachedMockFactory

@ScanScopedBeans
@SpringBootTest
class SpringBootTestAnnotationScopedProxyMockSpec extends Specification {
    @Autowired
    ApplicationContext context

    @Autowired
    HelloWorldService helloWorldService

    def "test context loads"() {
        expect:
        context != null
        context.containsBean("helloWorldService")
        context.containsBean("helloWorldServiceMock")
        !context.containsBean("scopedTarget.helloWorldService")
        context.containsBean("scopedTarget.helloWorldServiceMock")
        context.containsBean("spockExampleApplication")
    }

    def "scoped mock can be used"() {
        given:
        def helloWorldServiceMock = context.getBean("scopedTarget.helloWorldServiceMock") as HelloWorldService

        expect:
        helloWorldService.class.simpleName.startsWith('HelloWorldService$$EnhancerBySpringCGLIB$$')

        when:
        def result = helloWorldService.helloMessage

        then:
        1 * helloWorldServiceMock.getHelloMessage() >> 'sup?'
        result == 'sup?'
    }


    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        @Primary
        @RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
        HelloWorldService helloWorldServiceMock() {
            return detachedMockFactory.Mock(HelloWorldService)
        }
    }
}