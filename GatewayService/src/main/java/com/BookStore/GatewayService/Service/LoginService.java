package com.BookStore.GatewayService.Service;

import com.BookStore.GatewayService.dto.LoginRequest;
import com.BookStore.GatewayService.dto.LoginResponse;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LoginService {
    private WebClient webClient;

    @Autowired
    public LoginService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<LoginResponse> validToken(LoginRequest loginRequest) {
        Mono<LoginResponse> loginResponse = webClient.post()
                .uri("/api/authentication-service/valid")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(loginRequest), LoginRequest.class)
                .retrieve()
                .bodyToMono(LoginResponse.class);
        return loginResponse;
    }
}
