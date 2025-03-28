package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<UserDto> searchSuccessfulApplicants(Long vacancyId);

    void findUser(Long userId);

    UserDto findEmployee(Long employeeId);

    ResponseEntity<UserDto> author(UserDto userDto);

    String uploadingPhotos(MultipartFile file);

    ResponseEntity<?> findByName(String imageName);

    void createUser(UserDto userDto);

    void editUser(UserDto userDto, Long userId);

    List<UserDto> getUsers(String name);

    UserDto getUserEmail(String email);

    UserDto getUserPhone(String phone);

    Boolean userExists(String email);

    List<UserDto> getUserByResponse();
}
