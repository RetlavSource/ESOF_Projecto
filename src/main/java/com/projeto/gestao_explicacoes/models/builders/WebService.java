package com.projeto.gestao_explicacoes.models.builders;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WebService {
    private static RestTemplate restTemplate = null;
    private static HttpHeaders httpHeaders = null;

    private WebService() { }

    public static <T> ResponseEntity<T> byGet(String url, Class<T> responseType) {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        return restTemplate.getForEntity(url, responseType);
    }

    public static <T> ResponseEntity<T> byPost(T body, String url, Class<T> responseType) {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
        }

        HttpEntity<T> payload = new HttpEntity<>(body, httpHeaders);

        return restTemplate.postForEntity(url, payload, responseType);

    }
}
