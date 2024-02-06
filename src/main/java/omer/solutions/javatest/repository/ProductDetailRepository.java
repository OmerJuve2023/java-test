package omer.solutions.javatest.repository;

import omer.solutions.javatest.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, UUID> {

    ProductDetail findAllById(UUID id);


}
