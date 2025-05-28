package kg.attractor.jobsearch.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {
    void save(User user);

    List<UserDto> searchSuccessfulApplicants(Long vacancyId);


    List<UserDto> findUserByVacancy(Page<VacancyDto> dto);

    String userLanguage(String email);

    String uploadingPhotos(MultipartFile file);

    ResponseEntity<?> findByName(String imageName);

    void editUser(UserDto userDto, Long userId, String userAvatar);

    void registerUser(UserDto userDto, Long accountTypeId);

    void registerGoogleUser(UserDto userDto, Long accountTypeId, String email);

    List<UserDto> getUsers(String name);

    UserDto getUserEmail(String email);


    User getUserByEmail(String email);

    UserDto getUserById(Long id);

    Long getUserId(String email);

//    List<Long> getUserById(Long userId);

    Page<UserDto> findAllUserEmployee(Pageable pageable);

    UserDto findUserEmployeeById(Long id);

    UserDto getUserPhone(String phone);

    Boolean userExists(String email);

    List<UserDto> getUserByResponse();

    UserDto auxiliaryMethodUser(User user);

    User findById(Long id);

    String makeResetPasswordToken(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;

    User getUserByResetPasswordToken(String token);

    void updatePassword(User user, String password);

    @Transactional
    void updateUserLanguage(String email, String lang);
}
