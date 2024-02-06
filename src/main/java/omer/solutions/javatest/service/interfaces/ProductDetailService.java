package omer.solutions.javatest.service.interfaces;

import omer.solutions.javatest.dto.request.UpdateProductDetailRequest;
import omer.solutions.javatest.dto.response.MessageResponse;
import omer.solutions.javatest.model.ProductDetail;

import java.util.UUID;


public interface ProductDetailService {
    ProductDetail getById(UUID request);

    MessageResponse update(UpdateProductDetailRequest request);
}
