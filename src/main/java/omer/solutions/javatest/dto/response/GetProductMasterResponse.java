package omer.solutions.javatest.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record GetProductMasterResponse(
        UUID uuid,
        String name,
        Double price,
        String Category,
        Boolean enable,
        LocalDate dateCreated,
        String description
) {
}
