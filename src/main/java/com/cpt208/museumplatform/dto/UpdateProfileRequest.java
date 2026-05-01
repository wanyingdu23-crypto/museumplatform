package com.cpt208.museumplatform.dto;

public record UpdateProfileRequest(String username, String password, String confirmPassword) {
}
