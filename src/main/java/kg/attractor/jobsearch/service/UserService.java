package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void findUser(Integer userId, User user);

    void findEmployee(Integer employeeId, User user);

    ResponseEntity<UserDto> author(UserDto userDto);

    String uploadingPhotos(MultipartFile file);

    ResponseEntity<?> findByName(String imageName);

    void createUser(UserDto userDto);
}
