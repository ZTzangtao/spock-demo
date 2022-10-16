package com.foamfish.spock.example.external;

import com.foamfish.spock.example.dto.Hello;
import org.springframework.stereotype.Component;

@Component
public class ExternalApiClient {

    public Hello findByName(String name) {
        return new Hello(name);
    }

    public Hello getDefault() {
        return new Hello("world");
    }
}
