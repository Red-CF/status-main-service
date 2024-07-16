package org.democat.status.main.service.web.client.clients;

import lombok.extern.slf4j.Slf4j;
import org.democat.status.main.service.web.client.models.breeds.BreedsMO;
import org.democat.status.main.service.web.client.models.facts.FactsMO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.http.HttpClient;

@Slf4j
@Component
public class DogClientHandler {

    private final WebClient webClient;

    public DogClientHandler(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8080").build();
    }


    public Mono<ServerResponse> getBreeds(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new BreedsMO()));
    }


    public Mono<ServerResponse> getFacts(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new FactsMO()));
    }
}
