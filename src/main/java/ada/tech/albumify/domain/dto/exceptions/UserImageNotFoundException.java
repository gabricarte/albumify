package ada.tech.albumify.domain.dto.exceptions;

public class UserImageNotFoundException extends RuntimeException {
    public UserImageNotFoundException(Class<?> clazz, int id) {
        super(clazz.getSimpleName() + " image not found with id " + id);
    }
}
