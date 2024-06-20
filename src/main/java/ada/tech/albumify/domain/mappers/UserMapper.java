package ada.tech.albumify.domain.mappers;

import ada.tech.albumify.domain.dto.UserDto;
import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.domain.entities.User;

public class UserMapper {

    public static User toEntity(UserDto dto) {
        return User
                .builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public static UserDto toDto(User entity) {
        return new UserDto(entity.getUsername(), entity.getPassword());
    }
}
