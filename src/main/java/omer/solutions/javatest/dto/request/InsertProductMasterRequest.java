package omer.solutions.javatest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import omer.solutions.javatest.util.RequestException;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class InsertProductMasterRequest extends RequestException {
    @NotBlank(message = "El nombre no puede estar en blanco")
    String name;
    @Positive(message = "El precio debe ser un número positivo")
    Double price;
    String category;
    String description;
}