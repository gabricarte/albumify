package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.LoginResponseDto;
import ada.tech.albumify.domain.dto.UserDto;
import ada.tech.albumify.domain.dto.exceptions.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUserService {
    LoginResponseDto userLogin(UserDto userDto);

    LoginResponseDto userRegister(UserDto userDto) throws IOException;


    byte[] getUserImage(int userId) throws UserNotFoundException;
}
