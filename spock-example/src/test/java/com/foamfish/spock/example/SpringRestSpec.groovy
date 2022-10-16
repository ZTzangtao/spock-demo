package com.foamfish.spock.example

import com.foamfish.spock.example.entity.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification
import spock.lang.Stepwise

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Stepwise
class SpringRestSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    def "Simple status checker"() {
        when: "a rest call is performed to the status page"
//        RestTemplate restTemplate = new RestTemplate()
        String status = restTemplate.getForObject("/rest-service-example/status", String.class)

        then: "the correct message is expected"
        status == "Up and Running"
    }

    def "Cleaning all products"() {
        given: "a rest call is performed that deletes everything"
//        RestTemplate restTemplate = new RestTemplate()
        restTemplate.delete("/rest-service-example/products")

        when: "a product list is requested"
        List<Product> products = restTemplate.getForObject("/rest-service-example/products", List.class)

        then: "it should be empty"
        products.size() == 0
    }

    def "Creating a product"() {
//        given: "a rest template"
//        RestTemplate restTemplate = new RestTemplate()

        given: "a new product is created"
        Product product = restTemplate.postForObject("/rest-service-example/products", "unused", Product.class)

        when: "product list is requested again"
        List<Product> products = restTemplate.getForObject("/rest-service-example/products", List.class)

        then: "it should have default values"
        with(product) {
            name == "A product"
            stock == 0
            price == 0
            weight == 0
        }

        and: "product list should contain it"
        products.size() == 1
    }


}