package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.exception.NumberFormatException.UserServiceException;
import kg.attractor.jobsearch.modal.User;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends MethodClass implements UserService {

    private final UserDao userDao;

    @Override
    public List<UserDto> searchSuccessfulApplicants(Long vacancyId) {
        //TODO логика для поиска откликнувшихся соискателей на вакансию
        return List.of();
    }

    @Override
    public UserDto findUser(String userId) {
        Long parse = parseId(userId);
        //TODO логика для поиска заявителя
        return null;
    }


    @Override
    public UserDto findEmployee(String employeeId) {
        Long parse = parseId(employeeId);
        //TODO логика для поиска компании
        return null;
    }

    @Override
    public ResponseEntity<UserDto> author(UserDto userDto) {

        //TODO логика для определения типа соискатель/работадатель
        return ResponseEntity.ok(userDto);
    }


    @Override
    public String uploadingPhotos(MultipartFile file) {
        //TODO логика для загрузки аватарки
        return FileUtil.saveUploadFile(file, "images/");
    }

    @Override
    public ResponseEntity<?> findByName(String imageName) {
        return FileUtil.getOutputFile(imageName, "images/", MediaType.IMAGE_JPEG);
    }

    @Override
    public void createUser(UserDto userDto) {
        //TODO логика для создание пользователя
    }

    @Override
    public void editUser(UserDto userDto, Long userId) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAccountType(userDto.getAccountType());
        userDao.editProfile(user, userId);
    }


    @Override
    public List<UserDto> getUsers(String name) {
        List<User> user = userDao.getAllUserName(name);
        return userDto(user);
        //TODO логика для поиска пользователя по его имени
    }


    @Override
    public UserDto getUserEmail(String email) {
        User user = getEntityOrThrow(userDao.getUserEmail(email),
                new UserServiceException("Не найден пользователь с такой почтой"));
        return userDtos(user);
        //TODO Логика для поиска пользователя по его email
    }

    @Override
    public UserDto getUserPhone(String phone) {
        User user = getEntityOrThrow(userDao.getUserPhone(phone),
                new UserServiceException("Не найден пользователь с таким номером"));
        return userDtos(user);
        //TODO Логика для поиска пользователя по его телефону
    }

    @Override
    public Boolean userExists(String email) {
        return userDao.userExists(email);
        //TODO Логика что выводит существует ли такоей то пользователь по его id
    }

    @Override
    public List<UserDto> getUserByResponse() {
        List<User> users = userDao.getUserByResponse();
        return userDto(users);
    }

    private List<UserDto> userDto(List<User> user) {
        return user.stream()
                .map(this::userDtos)
                .filter(Objects::nonNull)
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
