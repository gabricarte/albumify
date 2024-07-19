package ada.tech.albumify.domain.dto;

import ada.tech.albumify.domain.dto.exceptions.AlbumNotFoundException;
import ada.tech.albumify.domain.dto.exceptions.AlreadyExistsException;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.dto.exceptions.UserNotFoundException;
import ada.tech.albumify.domain.entities.Album;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    public static ErrorResponse createFromException(UserNotFoundException ex) {
        String message = "User with id " + ex.getId() + " not found.";
        return new ErrorResponse(message);
    }

    public static ErrorResponse createFromException(AlbumNotFoundException ex) {
        String message = "Album with id " + ex.getId() + " not found.";
        return new ErrorResponse(message);
    }

    public static ErrorResponse createFromAlreadyExistsException(AlreadyExistsException ex) {
    String message = "The album " + ex.getAlbum().getName() + " is already associated with user " + ex.getUser().getUsername();
        return new ErrorResponse(message);
    }

    public static ErrorResponse createFromIntegrityViolationException(DataIntegrityViolationException ex) {
//        String message = "The cause: " + ex.getCause();
        String message = "E-mail or username already exists";
        return new ErrorResponse(message);
    }

}
