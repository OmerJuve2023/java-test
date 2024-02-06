package omer.solutions.javatest.controller;

import jakarta.validation.Valid;
import omer.solutions.javatest.dto.request.UpdateProductDetailRequest;
import omer.solutions.javatest.dto.response.MessageResponse;
import omer.solutions.javatest.model.ProductDetail;
import omer.solutions.javatest.service.impl.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/product/detail")
@CrossOrigin("*")
public class ProductDetailController {

    private final ProductDetailServiceImpl service;

    @Autowired
    public ProductDetailController(ProductDetailServiceImpl service) {
        this.service = service;
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateProduct(
            @RequestBody @Valid UpdateProductDetailRequest request) {
        return ResponseEntity.ok().body(service.update(request));
    }

    @GetMapping("/getById/{uuid}")
    public ResponseEntity<ProductDetail> getProductById(@PathVariable UUID uuid) {
        return ResponseEntity.ok().body(service.getById(uuid));
    }
}
