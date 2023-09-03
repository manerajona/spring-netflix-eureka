package com.manerajona.gateway.domain;

import java.util.UUID;

public record Consumer(UUID id, String name, String address, String phone) {

}