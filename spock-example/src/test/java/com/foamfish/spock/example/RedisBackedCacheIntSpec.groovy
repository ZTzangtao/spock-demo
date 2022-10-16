package com.foamfish.spock.example


import spock.lang.Specification

class RedisBackedCacheIntSpec extends Specification {

    private Map underTest

    def setup() {

        underTest = new HashMap()
    }

    def testSimplePutAndGet() {
        setup:
        underTest.put("test", "example")

        when:
        String retrieved = underTest.get("test")

        then:
        retrieved == "example"

//        expect:
//        retrieved == "example1"
    }
}