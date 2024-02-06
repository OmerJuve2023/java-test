package omer.solutions.javatest.controller;

import omer.solutions.javatest.dto.response.MessageResponse;
import omer.solutions.javatest.service.impl.HealthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class HealthCheckController {

    private final HealthService service;

    public HealthCheckController(HealthService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public ResponseEntity<MessageResponse> healthCheck() {
        return service.isDatabaseConnected();
    }
}
