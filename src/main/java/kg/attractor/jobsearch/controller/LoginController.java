package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        return userService.author(userDto);
    }

    @PostMapping("register")
    public HttpStatus register(UserDto userDto) {
        userService.createUser(userDto);
        return HttpStatus.CREATED;
    }


}
