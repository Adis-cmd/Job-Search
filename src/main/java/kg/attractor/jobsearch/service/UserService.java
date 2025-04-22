package kg.attractor.jobsearch.service;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<UserDto> searchSuccessfulApplicants(Long vacancyId);


    List<UserDto> findUserByVacancy(Page<VacancyDto> dto);

    String uploadingPhotos(MultipartFile file);

    ResponseEntity<?> findByName(String imageName);

    void editUser(UserDto userDto, Long userId, String userAvatar);

    void registerUser(UserDto userDto, Long accountTypeId, HttpServletRequest request);

    List<UserDto> getUsers(String name);

    UserDto getUserEmail(String email);


    UserDto getUserById(Long id);

    Long getUserId(String email);

//    List<Long> getUserById(Long userId);

    Page<UserDto> findAllUserEmployee(Pageable pageable);

    UserDto findUserEmployeeById(Long id);

    UserDto getUserPhone(String phone);

    Boolean userExists(String email);

    List<UserDto> getUserByResponse();

    UserDto auxiliaryMethodUser(User user);
}
