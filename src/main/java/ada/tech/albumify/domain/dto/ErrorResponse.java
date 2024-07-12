package ada.tech.albumify.domain.dto;

import ada.tech.albumify.domain.dto.exceptions.AlreadyExistsException;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.dto.exceptions.UserNotFoundException;
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

    public static ErrorResponse createFromException(UserNotFoundException ex) {
        String message = "User with id " + ex.getId() + " not found.";
        return new ErrorResponse(message);
    }


    public static ErrorResponse createFromAlreadyExistsException(AlreadyExistsException ex) {
    String message = "The album " + ex.getAlbum().getName() + " is already associated with user " + ex.getUser().getUsername();
        return new ErrorResponse(message);
    }



}
