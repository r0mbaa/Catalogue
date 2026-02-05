package ru.spring.manager.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatedProductPayload(

        @NotNull
        @Size(min = 3, max = 100)
        String title,

        @Size(max = 1000)
        String details,

        @NotNull
        Integer price,

        @NotNull
        Integer quantity)
 {
}
