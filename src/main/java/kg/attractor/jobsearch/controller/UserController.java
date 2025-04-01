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
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PutMapping("{id}")
    public void editUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        userService.editUser(userDto, id);
    }

    @GetMapping("applicant/{name}")
    public List<UserDto> findUser(@PathVariable("name") String name) {
       return userService.findUser(name);
    }

    @GetMapping("employee/{name}")
    public ResponseEntity<List<UserDto>> findEmployee(@PathVariable String name) {
       List<UserDto> employee = userService.findEmployee(name);
        return new ResponseEntity<>(employee, HttpStatus.OK);
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
    public ResponseEntity<List<UserDto>> searchVacancies(@PathVariable Long vacancyId) {
        List<UserDto> applicants = userService.searchSuccessfulApplicants(vacancyId);
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<UserDto>> getUserByName(@PathVariable String name) {
        List<UserDto> users = userService.getUsers(name);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto user = userService.getUserEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("phone/{phone}")
    public ResponseEntity<UserDto> getUserByPhone(@PathVariable String phone) {
        UserDto user = userService.getUserPhone(phone);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/exist/{email}")
    public ResponseEntity<Boolean> searchUser(@PathVariable String email) {
        Boolean exists = userService.userExists(email);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/responses")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getUserByResponse();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
