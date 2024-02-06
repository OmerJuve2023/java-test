package omer.solutions.javatest.ProductMasterTest;

import omer.solutions.javatest.dto.request.InsertProductMasterRequest;
import omer.solutions.javatest.dto.request.UpdateProductMasterRequest;
import omer.solutions.javatest.dto.response.GetProductMasterResponse;
import omer.solutions.javatest.dto.response.MessageResponse;
import omer.solutions.javatest.exceptions.classes.NotFoundClassException;
import omer.solutions.javatest.model.ProductDetail;
import omer.solutions.javatest.model.ProductMaster;
import omer.solutions.javatest.repository.ProductMasterRepository;
import omer.solutions.javatest.service.impl.ProductMasterServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductMasterServiceImplTest {

    @Mock
    private ProductMasterRepository masterRepository;

    @InjectMocks
    private ProductMasterServiceImpl productService;

    @Test
    void insert_shouldCreateProduct() {

        InsertProductMasterRequest request = InsertProductMasterRequest.builder()
                .name("Test Product")
                .price(10.0)
                .category("Test Category")
                .description("Test Description")
                .build();

        MessageResponse response = productService.insert(request);

        Mockito.verify(masterRepository, Mockito.times(1)).save(Mockito.any(ProductMaster.class));
        assertEquals("create product successful", response.message());
    }

    @Test
    void testUpdateProduct() {
        UpdateProductMasterRequest request = new UpdateProductMasterRequest();
        request.setId(UUID.randomUUID());

        ProductMaster existingMaster = new ProductMaster();
        existingMaster.setId(request.getId());

        ProductDetail existingDetail = new ProductDetail();
        existingMaster.setProductDetail(existingDetail);

        when(masterRepository.existsById(request.getId())).thenReturn(true);
        when(masterRepository.searchProductMasterById(request.getId())).thenReturn(existingMaster);

        productService.update(request);

        verify(masterRepository).existsById(request.getId());
        verify(masterRepository).searchProductMasterById(request.getId());
        verify(masterRepository).save(existingMaster);
    }

    @Test
    void update_shouldThrowNotFoundClassException() {
        UpdateProductMasterRequest request = UpdateProductMasterRequest.builder()
                .id(UUID.randomUUID())
                .build();
        UUID productId = request.getId();
        Mockito.when(masterRepository.existsById(productId)).thenReturn(false);
        assertThrows(NotFoundClassException.class, () -> productService.update(request));
    }

    @Test
    void getById_shouldReturnProduct() {
        UUID productId = UUID.randomUUID();
        Mockito.when(masterRepository.existsById(productId)).thenReturn(true);
        Mockito.when(masterRepository.findProductDetailsById(productId)).thenReturn(
                new GetProductMasterResponse(productId, "Test Product", 10.0, "Test Category",
                        true, LocalDate.now(), "Test Description"));
        GetProductMasterResponse response = productService.getById(productId);
        assertEquals(productId, response.uuid());
        assertEquals("Test Product", response.name());
    }

    @Test
    void getById_shouldThrowNotFoundClassException() {
        UUID productId = UUID.randomUUID();
        Mockito.when(masterRepository.existsById(productId)).thenReturn(false);
        assertThrows(NotFoundClassException.class, () -> productService.getById(productId));
    }
}
