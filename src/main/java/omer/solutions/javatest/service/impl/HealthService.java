package omer.solutions.javatest.service.impl;

import omer.solutions.javatest.config.DatabaseChecker;
import omer.solutions.javatest.dto.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HealthService {
    private final DatabaseChecker databaseChecker;

    public HealthService(DatabaseChecker databaseChecker) {
        this.databaseChecker = databaseChecker;
    }

    public ResponseEntity<MessageResponse> isDatabaseConnected() {
        final boolean isDatabaseConnected = databaseChecker.checkDatabaseConnection();
        if (isDatabaseConnected) {
            return new ResponseEntity<>(
                    new MessageResponse("Service in operation"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new MessageResponse("Error connecting V1.0.0.sql to the database"),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
