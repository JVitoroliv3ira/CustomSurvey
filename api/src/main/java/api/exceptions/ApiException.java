package api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String cause;

    public ApiException(String message, String cause, HttpStatus status) {
        super(message);
        this.status = status;
        this.cause = cause;
    }
}
