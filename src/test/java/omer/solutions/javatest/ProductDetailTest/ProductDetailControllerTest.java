package omer.solutions.javatest.ProductDetailTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import omer.solutions.javatest.controller.ProductDetailController;
import omer.solutions.javatest.dto.request.UpdateProductDetailRequest;
import omer.solutions.javatest.dto.response.MessageResponse;
import omer.solutions.javatest.model.ProductDetail;
import omer.solutions.javatest.service.impl.ProductDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductDetailController.class)
class ProductDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductDetailServiceImpl productDetailService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void updateProduct_shouldReturnOk() throws Exception {
        UpdateProductDetailRequest request = UpdateProductDetailRequest.builder()
                .id(UUID.randomUUID())
                .description("Updated Description")
                .build();

        Mockito.when(productDetailService.update(Mockito.any())).thenReturn(new MessageResponse("Product detail updated"));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/product/detail/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Product detail updated"));
    }


    @Test
    void getProductById_shouldReturnProductDetail() throws Exception {
        UUID uuid = UUID.randomUUID();
        ProductDetail productDetail = ProductDetail.builder()
                .id(uuid)
                .description("Test Description")
                .build();
        Mockito.when(productDetailService.getById(uuid)).thenReturn(productDetail);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/detail/getById/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }
}
