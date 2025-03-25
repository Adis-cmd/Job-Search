package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PutMapping("edit")
    public void editUser(@RequestBody UserDto userDto, @RequestParam(name = "id") Long id) {
        userService.editUser(userDto, id);
    }

    @GetMapping("applicant/{userId}")
    public HttpStatus findUser(@PathVariable("userId") Long userId) {
        userService.findUser(userId);
        return HttpStatus.OK;
    }

    @GetMapping("employee/{emoloyeeId}")
    public HttpStatus findEmployee(@PathVariable("emoloyeeId") Long employeeId) {
        userService.findEmployee(employeeId);
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


    @GetMapping("search/{vacancyId}")
    public List<UserDto> searchVacancies(@PathVariable Long vacancyId) {
       return userService.searchSuccessfulApplicants(vacancyId);
    }

    @GetMapping("name/{name}")
    public List<UserDto> getUser(@PathVariable String name) {
        return userService.getUsers(name);
    }

    @GetMapping("email/{email}")
    public UserDto getUserEmail(@PathVariable String email) {
        return userService.getUserEmail(email);
    }

    @GetMapping("phone/{phone}")
    public UserDto getUserPhone(@PathVariable String phone) {
        return userService.getUserPhone(phone);
    }

    @GetMapping("search/{email}")
    public Boolean searchUser(@PathVariable String email) {
        return userService.userExists(email);
    }

    @GetMapping("response")
    public List<UserDto> getAllUsers() {
        return userService.getUserByResponse();
    }
}
