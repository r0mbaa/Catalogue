package ru.spring.manager.payload;

public record UpdatedProductPayload(String title, String details, Integer price, Integer quantity) {
}
