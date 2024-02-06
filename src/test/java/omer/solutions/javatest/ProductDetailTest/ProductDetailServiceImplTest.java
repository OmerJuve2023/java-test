package omer.solutions.javatest.ProductDetailTest;

import omer.solutions.javatest.dto.request.UpdateProductDetailRequest;
import omer.solutions.javatest.dto.response.MessageResponse;
import omer.solutions.javatest.exceptions.classes.NotFoundClassException;
import omer.solutions.javatest.model.ProductDetail;
import omer.solutions.javatest.repository.ProductDetailRepository;
import omer.solutions.javatest.service.impl.ProductDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductDetailServiceImplTest {

    @Mock
    private ProductDetailRepository repository;

    @InjectMocks
    private ProductDetailServiceImpl productDetailService;

    @Test
    void getById_shouldReturnProductDetail() {
        UUID uuid = UUID.randomUUID();
        ProductDetail productDetail = ProductDetail.builder()
                .id(uuid)
                .description("Test Description")
                .build();

        Mockito.when(repository.existsById(uuid)).thenReturn(true);
        Mockito.when(repository.findAllById(uuid)).thenReturn(productDetail);

        ProductDetail result = productDetailService.getById(uuid);

        assertEquals(uuid, result.getId());
        assertEquals("Test Description", result.getDescription());
    }

    @Test
    void getById_shouldThrowNotFoundClassException() {
        UUID uuid = UUID.randomUUID();
        Mockito.when(repository.existsById(uuid)).thenReturn(false);
        assertThrows(NotFoundClassException.class, () -> productDetailService.getById(uuid));
    }

    @Test
    void update_shouldUpdateProductDetail() {

        UpdateProductDetailRequest request = UpdateProductDetailRequest.builder()
                .id(UUID.randomUUID())
                .description("Updated Description")
                .build();

        UUID productId = request.getId();
        Mockito.when(repository.existsById(productId)).thenReturn(true);
        Mockito.when(repository.findAllById(productId)).thenReturn(new ProductDetail());

        MessageResponse result = productDetailService.update(request);

        assertEquals("update product details successful", result.message());
    }

    @Test
    void update_shouldThrowNotFoundClassException() {
        UpdateProductDetailRequest request = UpdateProductDetailRequest.builder()
                .id(UUID.randomUUID())
                .build();

        UUID productId = request.getId();
        Mockito.when(repository.existsById(productId)).thenReturn(false);
        assertThrows(NotFoundClassException.class, () -> productDetailService.update(request));
    }
}
