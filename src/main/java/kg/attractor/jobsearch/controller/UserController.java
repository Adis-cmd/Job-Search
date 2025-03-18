package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("applicant/{userId}")
    public HttpStatus findUser(@PathVariable("userId") Integer userId, User user) {
        userService.findUser(userId, user);
        return HttpStatus.OK;
    }

    @GetMapping("employee/{emoloyeeId}")
    public HttpStatus findEmployee(@PathVariable("emoloyeeId") Integer employeeId, User user) {
        userService.findEmployee(employeeId, user);
        return HttpStatus.OK;
    }

    @GetMapping("photos/{imageName}")
    public ResponseEntity<?> getImage(@PathVariable String imageName) {
        return userService.findByName(imageName);
    }

    @PostMapping("photos")
    public String uploadImage(MultipartFile file) {
        return userService.uploadingPhotos(file);
    }
}
