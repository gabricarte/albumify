package ada.tech.albumify.controllers;

import ada.tech.albumify.domain.dto.LoginResponseDto;
import ada.tech.albumify.domain.dto.UserDto;
import ada.tech.albumify.domain.dto.exceptions.IncorrectCredentialsException;
import ada.tech.albumify.domain.dto.exceptions.UserNotFoundException;
import ada.tech.albumify.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/albumify")
@AllArgsConstructor
public class UserController {

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
        try {
            LoginResponseDto loginResponse = userService.userRegister(userDto);
            return ResponseEntity.created(null).body(loginResponse);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving profile image");
        }
    }

    @GetMapping("/user-img/{userId}")
    public ResponseEntity<?> getUserImage(@PathVariable int userId) {
        try {
            byte[] imageBlob = userService.getUserImage(userId);
            return ResponseEntity.ok(imageBlob);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
