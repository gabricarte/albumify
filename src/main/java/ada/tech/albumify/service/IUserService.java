package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.LoginResponseDto;
import ada.tech.albumify.domain.dto.UserDto;
import ada.tech.albumify.domain.dto.exceptions.UserNotFoundException;
import ada.tech.albumify.domain.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    LoginResponseDto userLogin(UserDto userDto);

    LoginResponseDto userRegister(UserDto userDto) throws IOException;

    List<User> getAllUsers();

    User getUser(int id);

    byte[] getUserImage(int userId) throws UserNotFoundException;
}
