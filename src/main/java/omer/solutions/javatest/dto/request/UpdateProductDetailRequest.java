package omer.solutions.javatest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import omer.solutions.javatest.util.RequestException;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDetailRequest extends RequestException {
    @NotBlank(message = "la descripcion no puede estar vacia")
    String description;
    @NotNull(message = "el id tiene que instanciarse")
    UUID id;
}
