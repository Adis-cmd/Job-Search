package kg.attractor.jobsearch.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final UserService userService;

    @GetMapping("login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "auth/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        try {
            String token = userService.makeResetPasswordToken(request);
            model.addAttribute("token", token);
            model.addAttribute("message", "auth.reset.password.send.email");
            return "auth/display_token";
        } catch (UsernameNotFoundException | UnsupportedEncodingException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (MessagingException ex) {
            model.addAttribute("error", "auth.reset.password.send.email.error");
        }
        return "auth/forgot_password_form";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(

            @RequestParam String token,

            Model model

    ) {
        try {
            userService.getUserByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("error", "auth.reset.password.invalid.token");
        }
        return "auth/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        try {
            User user = userService.getUserByResetPasswordToken(token);
            userService.updatePassword(user, password);
            model.addAttribute("message", "auth.reset.password.successfully.password");
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("message", "auth.reset.password.invalid.token");
        }
        return "auth/message";
    }
}
