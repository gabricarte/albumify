package ada.tech.albumify.domain.dto.exceptions;

import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AlreadyExistsException extends Exception{
    private Album album;
    private User user;
}
