package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.LoginResponseDto;
import ada.tech.albumify.domain.dto.UserDto;
import ada.tech.albumify.domain.dto.exceptions.IncorrectCredentialsException;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.dto.exceptions.UserNotFoundException;
import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.domain.entities.User;
import ada.tech.albumify.domain.mappers.UserMapper;
import ada.tech.albumify.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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
    public LoginResponseDto userLogin(UserDto userDto) throws UserNotFoundException, IncorrectCredentialsException {
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
            throw new UserNotFoundException(0);
        }
    }

    @Override
    public LoginResponseDto userRegister(UserDto userDto) throws IOException {
        String passwordEncoded = passwordEncoder.encode(userDto.getPassword());
        User user = UserMapper.toEntity(userDto);
        user.setPassword(passwordEncoded);
        byte[] profileImageBytes = convertBase64StringToBytes(userDto.getProfileImageBase64());
        user.setProfileImage(profileImageBytes);
        repository.save(user);
        return new LoginResponseDto(tokenService.generateTokenUser(user));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll()
                .stream()
                .toList();
    }

    @Override
    public User getUser(int id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public byte[] convertBase64StringToBytes(String base64String) throws IllegalArgumentException {
        if (base64String != null && !base64String.isEmpty()) {
            try {
                return Base64.getDecoder().decode(base64String);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Failed to decode Base64 string", e);
            }
        }
        return null;
    }

    @Override
    public byte[] getUserImage(int userId) throws UserNotFoundException {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException( userId));

        if (user.getProfileImage() == null) {
            throw new UserNotFoundException( userId); //img not found error treatment add later
        }

        return user.getProfileImage();
    }
}
