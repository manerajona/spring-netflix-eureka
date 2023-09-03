package com.manerajona.gateway.proxies;

import com.manerajona.gateway.domain.Order;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class OrdersServiceProxy {

    private static final String ORDER_SERVICE = "order-service";

    private final WebClient webClient;
    private final EurekaClient discoveryClient;

    public OrdersServiceProxy(WebClient webClient, EurekaClient discoveryClient) {
        this.webClient = webClient;
        this.discoveryClient = discoveryClient;
    }

    @PostMapping("v1/orders")
    public Mono<ResponseEntity<Void>> ordersV1(Order order) {

        String baseUrl =
            discoveryClient.getNextServerFromEureka(ORDER_SERVICE, false)
                .getHomePageUrl();

        return webClient.post()
            .uri(baseUrl + "/orders")
            .body(Mono.just(order), Order.class)
            .retrieve()
            .toBodilessEntity();
    }
}