package com.cpt208.museumplatform.dto;

public record RegisterRequest(String username, String email, String password, String confirmPassword) {
}
