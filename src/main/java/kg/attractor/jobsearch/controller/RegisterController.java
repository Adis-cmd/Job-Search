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

import java.security.Principal;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@RequestMapping("register")
public class RegisterController {
    private final UserService userService;

    @GetMapping
    public String showRegistrationForm(@RequestParam(required = false) String accountType, Model model,
                                       Locale locale) {
        System.out.println("Текущий язык: " + locale.getLanguage());
        model.addAttribute("accountType", accountType != null ? accountType : " ");
        model.addAttribute("userDto", new UserDto());
        return "register/register";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String register(@RequestParam(required = false) Long accountType,
                           @CookieValue(value = "lang", defaultValue = "en") String lang,
                           Locale locale,
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
        userDto.setLanguage(locale.getLanguage());

        userDto.setLanguage(lang);
        userService.registerUser(userDto, accountType);

        return "redirect:/auth/login";
    }


    @GetMapping("choose-account-type")
    public String registerUseGoogle(@RequestParam(required = false) String accountType, Model model, Principal p) {
        UserDto userDto = userService.getUserEmail(p.getName());
        model.addAttribute("accountType", accountType != null ? accountType : " ");
        model.addAttribute("userDto", userDto);
        return "register/useGoogle";
    }


    @PostMapping("choose-account-type")
    public String addUseGoogle(@Valid UserDto userDto,
                               BindingResult bindingResult, Model model,
                               @RequestParam(required = false) Long accountType, Principal p) {
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
            return "register/useGoogle";
        }

        userService.registerGoogleUser(userDto, accountType, p.getName());

        return "redirect:/profile";
    }


}
