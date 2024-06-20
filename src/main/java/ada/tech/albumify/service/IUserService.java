package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.LoginResponseDto;
import ada.tech.albumify.domain.dto.UserDto;
public interface IUserService {
    LoginResponseDto userLogin(UserDto userDto);

    LoginResponseDto userRegister(UserDto userDto);
}
