package nexus.inventory.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import nexus.inventory.exceptions.NotFoundException;
import nexus.inventory.exceptions.ConflictException;
import nexus.inventory.exceptions.BadRequestException;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(exception = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorResponse errMessage = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errMessage);
    }

    @ExceptionHandler(exception = ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException e) {
        ErrorResponse errMessage = new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errMessage);
    }

    @ExceptionHandler(exception = BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        ErrorResponse errMessage = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errMessage);
    }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknowException(BadRequestException e) {
        ErrorResponse errMessage = new ErrorResponse(e.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAllUncaughtException(Exception exception) {
        logger.error("Unknown error occurred", exception);

        return new ErrorResponse(
                "Occurred an internal error on server",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}

class ErrorResponse {
    private String message;
    private Integer status;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, Integer status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}