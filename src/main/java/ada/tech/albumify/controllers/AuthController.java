package ada.tech.albumify.controllers;

import ada.tech.albumify.domain.dto.LoginResponseDto;
import ada.tech.albumify.domain.dto.UserDto;
import ada.tech.albumify.domain.dto.exceptions.IncorrectCredentialsException;
import ada.tech.albumify.domain.dto.exceptions.UserNotFoundException;
import ada.tech.albumify.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/albumify")
@AllArgsConstructor
public class AuthController {

    private final IUserService userService;
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        try {
            LoginResponseDto loginResponse = userService.userLogin(userDto);
            return ResponseEntity.ok(loginResponse);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    @PostMapping(path="/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        LoginResponseDto loginResponse = userService.userRegister(userDto);
        return ResponseEntity.created(null).body(loginResponse);
    }
}
