package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.modal.Vacancy;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {


    @Override
    public List<User> searchSuccessfulApplicants(Vacancy vacancies) {
        //TODO логика для поиска откликнувшихся соискателей на вакансию
        return List.of();
    }


    @Override
    public void findUser(Integer userId, User user) {
        //TODO логика для поиска заявителя
    }


    @Override
    public void findEmployee(Integer employeeId, User user) {
        //TODO логика для поиска компании
    }

    @Override
    public ResponseEntity<UserDto> author(UserDto userDto) {
       
        //TODO логика для определения типа соискатель/работадатель
        return ResponseEntity.ok(userDto);
    }


    @Override
    public String uploadingPhotos(MultipartFile file) {
        //TODO логика для загрузки аватарки
        FileUtil fu = new FileUtil();
        return fu.saveUploadFile(file, "images/");
    }

    @Override
    public ResponseEntity<?> findByName(String imageName) {
        return new FileUtil().getOutputFile(imageName, "images/", MediaType.IMAGE_JPEG);
    }

    @Override
    public void createUser(UserDto userDto) {
        //TODO логика для создание пользователя
    }


    @Override
    public List<UserDto> getUsers(String name) {
        List<User> user = userDao.getAllUserName(name);
        return userDto(user);
    }


    @Override
    public UserDto getUserEmail(String email) {
        User user = userDao.getUserEmail(email)
                .orElseThrow(() -> new UserServiceException("Не найден пользователь с такой почтой"));
        return userDtos(user);
    }

    @Override
    public UserDto getUserPhone(String phone) {
        User user = userDao.getUserPhone(phone)
                .orElseThrow(() -> new UserServiceException("Не найден пользователь с таким номером"));
        return userDtos(user);
    }

    @Override
    public Boolean userExists(String email) {
        return userDao.userExists(email);
    }

    @Override
    public List<UserDto> getUserByResponse() {
        List<User> users = userDao.getUserByResponse();
        return userDto(users);
    }

    private List<UserDto> userDto(List<User> user) {
        return user.stream()
                .map(e -> UserDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .surname(e.getSurname())
                        .email(e.getEmail())
                        .avatar(e.getAvatar())
                        .password(e.getPassword())
                        .phoneNumber(e.getPhoneNumber())
                        .age(e.getAge())
                        .accountType(e.getAccountType())
                        .build())
                .toList();
    }

    private UserDto userDtos(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .age(user.getAge())
                .accountType(user.getAccountType())
                .build();
    }

}
