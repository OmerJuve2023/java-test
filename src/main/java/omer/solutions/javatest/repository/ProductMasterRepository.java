package omer.solutions.javatest.repository;

import omer.solutions.javatest.dto.response.GetProductMasterResponse;
import omer.solutions.javatest.model.ProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, UUID> {

    ProductMaster searchProductMasterById(UUID uuid);

    @Query("SELECT new omer.solutions.javatest.dto.response.GetProductMasterResponse(" +
           "pm.id, pm.name, pm.price, pm.category, pm.enable, pm.dateCreated, pd.description) " +
           "FROM ProductMaster pm " +
           "JOIN ProductDetail pd ON pm.id = pd.productMaster.id " +
           "WHERE pm.id = :productId")
    GetProductMasterResponse findProductDetailsById(@Param("productId") UUID productId);

}
