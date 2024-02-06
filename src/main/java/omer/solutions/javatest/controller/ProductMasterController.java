package omer.solutions.javatest.controller;

import jakarta.validation.Valid;
import omer.solutions.javatest.dto.request.InsertProductMasterRequest;
import omer.solutions.javatest.dto.request.UpdateProductMasterRequest;
import omer.solutions.javatest.dto.response.GetProductMasterResponse;
import omer.solutions.javatest.dto.response.MessageResponse;
import omer.solutions.javatest.service.impl.ProductMasterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product/master")
public class ProductMasterController {

    private final ProductMasterServiceImpl service;

    @Autowired
    public ProductMasterController(ProductMasterServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<MessageResponse> insertProduct(@RequestBody @Valid InsertProductMasterRequest request) {
        return ResponseEntity.ok().body(service.insert(request));
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateProduct(@RequestBody @Valid UpdateProductMasterRequest request) {
        return ResponseEntity.ok().body(service.update(request));
    }

    @GetMapping("/getById/{uuid}")
    public ResponseEntity<GetProductMasterResponse> getProductById(@PathVariable UUID uuid) {
        return ResponseEntity.ok().body(service.getById(uuid));
    }
}
