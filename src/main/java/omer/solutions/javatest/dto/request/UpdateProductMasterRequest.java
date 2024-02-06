package omer.solutions.javatest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import omer.solutions.javatest.util.RequestException;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductMasterRequest extends RequestException {
    @NotNull(message = "el id tiene que instanciarse")
    UUID id;
    @NotBlank(message = "El nombre no puede estar en blanco")
    String name;
    @Positive(message = "El precio debe ser un número positivo")
    Double price;
    String category;
    String description;
    Boolean enable;

}
