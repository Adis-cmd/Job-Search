package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<UserDto> searchSuccessfulApplicants(Long vacancyId);

    List<UserDto> findUser(String userId);

    List<UserDto> findEmployee(String employeeId);

    String uploadingPhotos(MultipartFile file);

    ResponseEntity<?> findByName(String imageName);

    void editUser(UserDto userDto, Long userId);

    void registerUser(UserDto userDto);

    List<UserDto> getUsers(String name);

    UserDto getUserEmail(String email);

    UserDto getUserPhone(String phone);

    Boolean userExists(String email);

    List<UserDto> getUserByResponse();
}
