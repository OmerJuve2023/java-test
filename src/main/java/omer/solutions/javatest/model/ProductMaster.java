package omer.solutions.javatest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.domain.Persistable;

import java.time.LocalDate;
import java.util.UUID;

@SuppressWarnings("ALL")
@Entity
@Getter
@Setter
@Table(name = "product_master")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMaster implements Persistable<UUID> {

    @Id
    @Column(unique = true)
    private UUID id;
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Column(nullable = false, unique = true, length = 50)
    private String name;
    @Positive(message = "El precio debe ser un número positivo")
    private Double price;
    @Column(nullable = false, length = 50)
    private String category;
    @Column(nullable = false)
    private Boolean enable;
    @Column(nullable = false, updatable = false)
    private LocalDate dateCreated;

    @JsonBackReference
    @OneToOne(mappedBy = "productMaster", cascade = CascadeType.ALL)
    private ProductDetail productDetail;

    @JsonIgnore
    public boolean isNew() {
        return false;
    }
}