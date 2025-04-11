package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("register")
public class RegisterController {
    private final UserService userService;

    @GetMapping
    public String showRegistrationForm(@RequestParam(required = false) String accountType, Model model) {
        model.addAttribute("accountType", accountType != null ? accountType : " ");

        return "register/register";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String register(@RequestParam(required = false) Long accountType, UserDto userDto) {
        userService.registerUser
                (UserDto.builder()
                        .name(userDto.getName())
                        .password(userDto.getPassword())
                        .email(userDto.getEmail())
                        .phoneNumber(userDto.getPhoneNumber())
                        .surname(userDto.getSurname())
                        .age(userDto.getAge())
                        .accountType(accountType)
                .build());
        return "redirect:/profile";
    }

}
