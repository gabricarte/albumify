package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.LoginResponseDto;
import ada.tech.albumify.domain.dto.UserDto;
import ada.tech.albumify.domain.dto.exceptions.IncorrectCredentialsException;
import ada.tech.albumify.domain.dto.exceptions.UserNotFoundException;
import ada.tech.albumify.domain.entities.User;
import ada.tech.albumify.domain.mappers.UserMapper;
import ada.tech.albumify.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository repository;
    @Autowired
    private TokenService tokenService;

    public UserService(PasswordEncoder passwordEncoder, IUserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Override
    public LoginResponseDto userLogin(UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        User user = repository.findByUsername(username);
        if (user != null) {
            String senhaEncoded = user.getPassword();
            if (passwordEncoder.matches(password, senhaEncoded)) {
                LoginResponseDto loginResponse = new LoginResponseDto(tokenService.generateTokenUser(user));
                return loginResponse;
            } else {
                throw new IncorrectCredentialsException("Incorrect credentials!");
            }
        } else {
            throw new UserNotFoundException(User.class, 0);
        }
    }

    @Override
    public LoginResponseDto userRegister(UserDto userDto) throws IOException {
        String passwordEncoded = passwordEncoder.encode(userDto.getPassword());
        User user = UserMapper.toEntity(userDto);
        user.setPassword(passwordEncoded);

        if (userDto.getProfileImageBase64() != null && !userDto.getProfileImageBase64().isEmpty()) {
            String base64Image = userDto.getProfileImageBase64().split(",")[1]; // Remove o cabeçalho "data:image/jpeg;base64,"
            byte[] profileImageBytes = Base64.getDecoder().decode(base64Image);
            user.setProfileImage(profileImageBytes);
        }

        repository.save(user);
        return new LoginResponseDto(tokenService.generateTokenUser(user));
    }

    @Override
    public byte[] getUserImage(int userId) throws UserNotFoundException {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(User.class, userId));

        if (user.getProfileImage() == null) {
            throw new UserNotFoundException(User.class, userId); //img not found add later
        }

        return user.getProfileImage();
    }
}
