package omer.solutions.javatest.ProductMasterTest;

import omer.solutions.javatest.controller.ProductMasterController;
import omer.solutions.javatest.dto.request.InsertProductMasterRequest;
import omer.solutions.javatest.dto.request.UpdateProductMasterRequest;
import omer.solutions.javatest.dto.response.GetProductMasterResponse;
import omer.solutions.javatest.dto.response.MessageResponse;
import omer.solutions.javatest.service.impl.ProductMasterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductMasterControllerTest {

    @Mock
    private ProductMasterServiceImpl service;

    @InjectMocks
    private ProductMasterController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertProduct() {
        InsertProductMasterRequest request = new InsertProductMasterRequest();
        when(service.insert(request)).thenReturn(new MessageResponse("Product inserted successfully"));

        ResponseEntity<MessageResponse> response = controller.insertProduct(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product inserted successfully", Objects.requireNonNull(response.getBody()).message());
        verify(service, times(1)).insert(request);
    }

    @Test
    void testUpdateProduct() {
        UpdateProductMasterRequest request = new UpdateProductMasterRequest();
        when(service.update(request)).thenReturn(new MessageResponse("Product updated successfully"));

        ResponseEntity<MessageResponse> response = controller.updateProduct(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product updated successfully", Objects.requireNonNull(response.getBody()).message());
        verify(service, times(1)).update(request);
    }

    @Test
    void testGetProductById() {
        UUID productId = UUID.randomUUID();
        GetProductMasterResponse mockResponse = new GetProductMasterResponse(productId, "TestProduct", 20.0, "TestCategory", true, null, "TestDescription");
        when(service.getById(productId)).thenReturn(mockResponse);

        ResponseEntity<GetProductMasterResponse> response = controller.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(service, times(1)).getById(productId);
    }
}
