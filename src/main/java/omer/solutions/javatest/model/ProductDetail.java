package omer.solutions.javatest.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Getter
@Setter
@Table(name = "product_detail")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductDetail implements Persistable<UUID> {

    @Id
    @Column(unique = true)
    private UUID id;

    @NotBlank(message = "Description cannot be blank")
    @Column(length = 200)
    private String description;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_product_master")
    private ProductMaster productMaster;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return false;
    }
}