package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("register")
public class RegisterController {
    private final UserService userService;

    @GetMapping
    public String showRegistrationForm(@RequestParam(required = false) String accountType, Model model) {
        model.addAttribute("accountType", accountType != null ? accountType : " ");
        model.addAttribute("userDto", new UserDto());
        return "register/register";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String register(@RequestParam(required = false) Long accountType,
                           @Valid UserDto userDto,
                           BindingResult bindingResult,
                           Model model) {

        if (accountType == 1) {
            boolean onlyIgnoredErrors = bindingResult.getFieldErrors().stream()
                    .allMatch(error -> error.getField().equals("surname") || error.getField().equals("age"));

            if (onlyIgnoredErrors) {
                bindingResult = new BeanPropertyBindingResult(userDto, "userDto");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("accountType", accountType != null ? accountType.toString() : "");
            model.addAttribute("userDto", userDto);
            return "register/register";
        }

        userService.registerUser(userDto, accountType);

        return "redirect:/profile";
    }


}
