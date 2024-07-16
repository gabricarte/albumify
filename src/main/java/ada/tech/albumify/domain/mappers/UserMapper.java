package ada.tech.albumify.domain.mappers;

import ada.tech.albumify.domain.dto.UserDto;
import ada.tech.albumify.domain.entities.User;

import java.util.Base64;

public class UserMapper {

    public static User toEntity(UserDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        return user;
    }

    public static UserDto toDto(User entity) {
        return UserDto.builder()
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .profileImageBase64(null)
                .build();
    }
}
