package ada.tech.albumify.controllers;

import ada.tech.albumify.domain.dto.ErrorResponse;
import ada.tech.albumify.domain.dto.exceptions.AlreadyExistsException;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.dto.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.createFromException(exception));
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(final UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.createFromException(exception));
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(final AlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.createFromAlreadyExistsException(exception));
    }

}
