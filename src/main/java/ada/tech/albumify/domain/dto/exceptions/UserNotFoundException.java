package ada.tech.albumify.domain.dto.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotFoundException  extends RuntimeException{
    private String message;
}
