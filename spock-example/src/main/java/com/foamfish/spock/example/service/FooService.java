package com.foamfish.spock.example.service;

import com.foamfish.spock.example.dto.Hello;
import com.foamfish.spock.example.external.ExternalApiClient;
import org.springframework.stereotype.Service;

@Service
public class FooService {
    private final ExternalApiClient client;

    public FooService(ExternalApiClient client) {
        this.client = client;
    }

    public Hello hello() {
        return client.getDefault();
    }
}
