package api.settings;

import api.dtos.responses.ResponseDTO;
import api.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());

        Map<String, List<String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ResponseDTO<>(
                        null,
                        null,
                        errors
                ));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseDTO<?>> handleApiException(ApiException ex) {
        log.error(ex.getMessage());

        Map<String, List<String>> errors = new HashMap<>();
        errors.put(ex.getCause(), List.of(ex.getMessage()));

        return ResponseEntity
                .status(ex.getStatus())
                .body(new ResponseDTO<>(
                        null,
                        null,
                        errors
                ));
    }
}
