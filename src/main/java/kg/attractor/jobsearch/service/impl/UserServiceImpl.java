package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.exception.NumberFormatException.UserServiceException;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends MethodClass implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private static final Map<String, Long> ACCOUNTTYPEMAP = new HashMap<>();


    @Override
    public List<UserDto> searchSuccessfulApplicants(Long vacancyId) {
        log.info("Поиск успешных кандидатов для вакансии с ID: {}", vacancyId);
        List<User> users = userDao.getApplicantsWhoRespondedToVacancy(vacancyId);
        //TODO логика для поиска откликнувшихся соискателей на вакансию
        return userDto(users);
    }

    @Override
    public Long findAccountTypeId(String accountType) {
        log.info("Найден тип аккаунта");
        return userDao.findAccountTypeId(accountType);
    }

    @Override
    public List<UserDto> findUser(String name) {
        if (name == null || name.isEmpty()) {
            log.error("Попытка поиска пользователя с пустым именем");
            throw new UserServiceException("Имя не может быть пустым");
        }
        log.info("Поиск пользователя по имени: {}", name);
        List<User> users = userDao.findApplicant(name);
        //TODO логика для поиска заявителя
        return userDto(users);
    }


    @Override
    public List<UserDto> findEmployee(String name) {

        if (name == null || name.isEmpty()) {
            log.error("Попытка поиска сотрудника с пустым именем");
            throw new UserServiceException("Имя не может быть пустым");
        }
        log.info("Поиск сотрудника по имени: {}", name);
        List<User> user = userDao.findEmployeeBy(name);
        //TODO логика для поиска компании
        return userDto(user);
    }


    @Override
    public String uploadingPhotos(MultipartFile file) {
        //TODO логика для загрузки аватарки
        log.info("Загрузка аватарки пользователя");
        return FileUtil.saveUploadFile(file, "images/");
    }

    @Override
    public ResponseEntity<?> findByName(String imageName) {
        log.info("Получение файла изображения с именем: {}", imageName);
        return FileUtil.getOutputFile(imageName, "images/", MediaType.IMAGE_JPEG);
    }


    @Override
    public void editUser(UserDto userDto, Long userId, String avatar) {
        log.info("Редактирование профиля пользователя с ID: {}", userId);
        User user = editUser(userDto);
        userDao.editProfile(user, userId, avatar);
    }


    private User editUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setAvatar(userDto.getAvatar());
        return user;
    }

    @Override
    public void registerUser(UserDto userDto) {
        log.info("Регистрация нового пользователя с email: {}", userDto.getEmail());
        User user = createUserFromDto(userDto);
        userDao.registerUser(user);
    }

    private User createUserFromDto(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAccountType(userDto.getAccountType());
//        String accountTypeStr = String.valueOf(userDto.getAccountType());
//        try {
//            Long accountType = Long.parseLong(accountTypeStr);
//            if (ACCOUNTTYPEMAP.get(accountType) == null) {
//                throw new UnknownUserException("Тип аккаунта не найден");
//            }
//            user.setAccountType(accountType);
//        } catch (IllegalArgumentException e) {
//            throw new UnknownUserException("Тип аккаунта должен быть числом. Получено: " + accountTypeStr);
//        }
        log.debug("Создание объекта User из DTO для пользователя с email: {}", userDto.getEmail());
        return user;
    }


    @Override
    public List<UserDto> getUsers(String name) {
        log.info("Поиск всех пользователей с именем: {}", name);
        List<User> user = userDao.getAllUserName(name);
        return userDto(user);
        //TODO логика для поиска пользователя по его имени
    }


    @Override
    public UserDto getUserEmail(String email) {
        log.info("Поиск пользователя с email: {}", email);
        User user = getEntityOrThrow(userDao.getUserEmail(email),
                new UserServiceException("Не найден пользователь с такой почтой"));
        return userDtos(user);
        //TODO Логика для поиска пользователя по его email
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Поиск пользователя с id: {}", id);
        User user = getEntityOrThrow(userDao.getUserById(id),
                new UserServiceException("Не найден пользователь с таким id"));
        return userDtos(user);
        //TODO Логика для поиска пользователя по его email
    }
    @Override
    public Long getUserId(String email) {
        Long userId = userDao.getUserId(email);
        if (userId == null || userId == 0) {
            throw new UserServiceException("Нету пользователя с таким Email");
        }
        return  userId;
    }


    @Override
    public UserDto getUserPhone(String phone) {
        log.info("Поиск пользователя с номером телефона: {}", phone);
        User user = getEntityOrThrow(userDao.getUserPhone(phone),
                new UserServiceException("Не найден пользователь с таким номером"));
        return userDtos(user);
        //TODO Логика для поиска пользователя по его телефону
    }

    @Override
    public Boolean userExists(String email) {
        log.info("Проверка существования пользователя с email: {}", email);
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
                .enabled(user.getEnabled())
                .build();
    }

    static {
        ACCOUNTTYPEMAP.put("Работадатель", 1L);
        ACCOUNTTYPEMAP.put("Соискатель", 2L);
    }

}
