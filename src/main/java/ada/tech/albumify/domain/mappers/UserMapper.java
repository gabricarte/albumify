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

        if (dto.getProfileImageBase64() != null && !dto.getProfileImageBase64().isEmpty()) {
            String base64Image = dto.getProfileImageBase64().split(",")[1]; // Remove o cabeçalho "data:image/jpeg;base64,"
            byte[] profileImageBytes = Base64.getDecoder().decode(base64Image);
            user.setProfileImage(profileImageBytes);
        }

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
