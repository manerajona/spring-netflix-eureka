package com.manerajona.gateway.domain;

import java.util.UUID;

public record OrderItem(UUID id, String name, Integer quantity) {

}