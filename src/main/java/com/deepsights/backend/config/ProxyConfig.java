package com.deepsights.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class ProxyConfig {

    private static final String PROXY_HOST = "127.0.0.1";
    private static final int PROXY_PORT = 3128;

    // 1. Configures the RestClient (Synchronous) - This is your original code
    @Bean
    public RestClient.Builder restClientBuilder() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        requestFactory.setProxy(
                new Proxy(
                        Proxy.Type.HTTP,
                        new InetSocketAddress(PROXY_HOST, PROXY_PORT)
                )
        );

        return RestClient.builder()
                .requestFactory(requestFactory);
    }

    // 2. Configures the WebClient (Reactive/Asynchronous) - Fixes the Netty error
    @Bean
    public WebClient.Builder webClientBuilder() {
        HttpClient httpClient = HttpClient.create()
                .proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP)
                        .host(PROXY_HOST)
                        .port(PROXY_PORT));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}