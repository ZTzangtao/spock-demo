package com.foamfish.spock.example.service;

import com.foamfish.spock.example.dto.Hello;
import com.foamfish.spock.example.external.ExternalApiClient;
import org.springframework.stereotype.Service;

@Service
public class BarService {
    private final ExternalApiClient client;

    public BarService(ExternalApiClient client) {
        this.client = client;
    }

    public Hello hello(String name) {
        return client.findByName(name);
    }
}
