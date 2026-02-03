package ru.spring.manager.payload;

public record NewProductPayload(String title, String details, Integer price, Integer quantity) {
}
