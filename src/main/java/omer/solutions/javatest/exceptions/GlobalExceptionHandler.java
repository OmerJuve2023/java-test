package omer.solutions.javatest.exceptions;

import omer.solutions.javatest.config.DatabaseChecker;
import omer.solutions.javatest.dto.response.MessageResponse;
import omer.solutions.javatest.exceptions.classes.NotContentClassException;
import omer.solutions.javatest.exceptions.classes.NotFoundClassException;
import omer.solutions.javatest.util.MessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(DatabaseChecker.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<MessageResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(new MessageResponse(MessageInfo.JSON_NOT_VALIDATE.toString()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<MessageResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = bindingResult.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.error(errorMessage);
        return ResponseEntity.badRequest().body(new MessageResponse(MessageInfo.METHOD_ARGUMENT_NOT_VALIDATE + errorMessage));
    }

    @ExceptionHandler(NotContentClassException.class)
    @ResponseBody
    public ResponseEntity<MessageResponse> handleListClientNotFoundException(NotContentClassException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse(ex.getMessage()));
    }

    @ExceptionHandler(NotFoundClassException.class)
    @ResponseBody
    public ResponseEntity<MessageResponse> handleClientNotFoundException(NotFoundClassException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<MessageResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new MessageResponse(ex.getMessage()));
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseBody
    public ResponseEntity<MessageResponse> handleInvalidDataAccessApiUsageException(
            InvalidDataAccessApiUsageException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(MessageInfo.INVALIDATE_DATA.toString()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<MessageResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(MessageInfo.DATA_INTEGRATE_VIOLATION.toString()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<MessageResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(MessageInfo.HANDLER_NOT_FOUND.toString()));
    }


}
