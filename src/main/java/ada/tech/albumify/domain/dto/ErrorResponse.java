package ada.tech.albumify.domain.dto;

import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class ErrorResponse {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Collection<ErrorMessage> errors;

    private ErrorResponse(String message) {
        this.message = message;
    }

    private ErrorResponse(String message, Collection<ErrorMessage> errors) {
        this(message);
        this.errors = errors;
    }
    public static ErrorResponse createFromException(NotFoundException ex) {
        String message = "No record of " + ex.getClazz().getSimpleName() + " found for id " + ex.getId();
        return new ErrorResponse(message);
    }
    public static ErrorResponse createFromException(MethodArgumentNotValidException ex) {
        var violations = ex
                .getFieldErrors()
                .stream()
                .map(it -> new ErrorMessage(it.getField(), it.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErrorResponse("Validation errors", violations);
    }
}
