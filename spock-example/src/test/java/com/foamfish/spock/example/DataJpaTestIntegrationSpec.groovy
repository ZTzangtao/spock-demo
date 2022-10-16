package com.foamfish.spock.example

import com.foamfish.spock.example.entity.Book
import com.foamfish.spock.example.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import spock.lang.Specification
import spock.lang.Title


@Title("测试标题")
@DataJpaTest
class DataJpaTestIntegrationSpec extends Specification {

    @Autowired
    TestEntityManager entityManager

    @Autowired
    BookRepository bookRepository

    def "spring context loads for data jpa slice"() {
        given: "some existing books"
        entityManager.persist(new Book("Moby Dick"))
        entityManager.persist(new Book("Testing Spring with Spock"))
//        bookRepository.save(new Book("Moby Dick"))
//        bookRepository.save(new Book("Testing Spring with Spock"))

        expect: "the correct count is inside the repository"
        bookRepository.count() == 2L
    }

    def "loads for data jpa slice with data tables"() {
        given: "some existing books"
        entityManager.persist(targetBook)
//        bookRepository.save(new Book("Moby Dick"))
//        bookRepository.save(new Book("Testing Spring with Spock"))

        when: "query"
        def book = bookRepository.findByTitle(tagartTitle)

        then: "the correct count is inside the repository"
        with(book) {
           title == tagartTitle
        }

        where:
//        tagetTitle | _
//        "1" | _
//        "2" | _
//        "3" | _
        targetBook | tagartTitle
        new Book("1") | "1"
        new Book("2") | "2"

    }


}