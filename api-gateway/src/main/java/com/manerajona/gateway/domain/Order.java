package com.manerajona.gateway.domain;

import java.util.Set;
import java.util.UUID;

public record Order(UUID id, Consumer consumer, Set<OrderItem> items, String notes) {

}