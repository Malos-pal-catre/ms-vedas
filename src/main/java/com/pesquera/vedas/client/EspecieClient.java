package com.pesquera.vedas.client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;
@Component
public class EspecieClient {
    private final WebClient webClient;
    public EspecieClient(WebClient.Builder builder, @Value("${app.ms-especies.url}") String url) {
        this.webClient = builder.baseUrl(url).build();
    }
    public Map obtenerEspeciePorNombre(String nombre) {
        return webClient.get()
                .uri("/api/especies/nombre/{nombre}", nombre)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response -> {
                    throw new RuntimeException("Especie no encontrada: " + nombre);
                })
                .bodyToMono(Map.class)
                .block();
    }
}