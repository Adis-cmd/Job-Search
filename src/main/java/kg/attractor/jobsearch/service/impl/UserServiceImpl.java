package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.NumberFormatException.UserServiceException;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.repos.UserRepository;
import kg.attractor.jobsearch.service.AccountTypeService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends MethodClass implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccountTypeService accountTypeService;


    @Override
    public List<UserDto> searchSuccessfulApplicants(Long vacancyId) {
        log.info("Поиск успешных кандидатов для вакансии с ID: {}", vacancyId);
        List<User> users = userRepository.getApplicantsWhoRespondedToVacancy(vacancyId);
        //TODO логика для поиска откликнувшихся соискателей на вакансию
        return userDto(users);
    }

    @Override
    public List<UserDto> findUserByVacancy(Page<VacancyDto> dto) {
        List<UserDto> result = new ArrayList<>();

        for (VacancyDto vacancy : dto) {
            Long authorId = vacancy.getAuthorId();
            if (authorId != null) {
                Optional<User> userOpt = userRepository.findById(authorId);
                userOpt.ifPresent(user -> result.add(userDtos(user)));
            }
        }
        return result;
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
        User user = getEntityOrThrow(userRepository.findById(userId),
                new UserServiceException("User not found"));
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setAvatar(avatar);
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void registerUser(UserDto userDto, Long accountTypeId) {
        User user = new User();

        if (userRepository.existsByPhoneNumber(userDto.getEmail())) {
            throw new UserServiceException("Есть пользователь с таким номером телефона");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserServiceException("Есть пользотель с таким Email");
        }

        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhoneNumber(userDto.getPhoneNumber());
        if (accountTypeId == 1) {
            user.setAvatar("avatarForEmployee.jpg");
        } else if (accountTypeId == 2) {
            user.setAvatar("avatarForApplicant.jpg");
        }
        user.setEnabled(true);
        user.setAccountType(accountTypeService.findById(accountTypeId));

        userRepository.save(user);
    }


    @Override
    public List<UserDto> getUsers(String name) {
        log.info("Поиск всех пользователей с именем: {}", name);
        List<User> user = userRepository.findAllByName(name);
        return userDto(user);
        //TODO логика для поиска пользователя по его имени
    }


    @Override
    public UserDto getUserEmail(String email) {
        log.info("Поиск пользователя с email: {}", email);
        User user = getEntityOrThrow(userRepository.findByEmail(email),
                new UserServiceException("Не найден пользователь с такой почтой"));
        return userDtos(user);
        //TODO Логика для поиска пользователя по его email
    }
    @Override
    public User getUserByEmail(String email) {
        return getEntityOrThrow(userRepository.findByEmail(email),
                new UserServiceException("Email not found"));
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Поиск пользователя с id: {}", id);
        User user = getEntityOrThrow(userRepository.findById(id),
                new UserServiceException("Не найден пользователь с таким id"));
        return userDtos(user);
        //TODO Логика для поиска пользователя по его email
    }

    @Override
    public Long getUserId(String email) {
        Long userId = userRepository.findUSerByEmail(email);
        if (userId == null || userId == 0) {
            throw new UserServiceException("Нету пользователя с таким Email");
        }
        return userId;
    }

    @Override
    public Page<UserDto> findAllUserEmployee(Pageable pageable) {
        Page<User> users = userRepository.findAllUserEmployee(pageable);

        if (users == null) {
            throw new UserServiceException("User not found");
        }
        return users.map(this::userDtos);
    }

    @Override
    public UserDto findUserEmployeeById(Long id) {
        User users = getEntityOrThrow(userRepository.findUserById(id), new UserServiceException("User not found"));

        return userDtos(users);
    }


    @Override
    public UserDto getUserPhone(String phone) {
        log.info("Поиск пользователя с номером телефона: {}", phone);
        User user = getEntityOrThrow(userRepository.findUserByPhoneNumber(phone),
                new UserServiceException("Не найден пользователь с таким номером"));
        return userDtos(user);
        //TODO Логика для поиска пользователя по его телефону
    }

    @Override
    public Boolean userExists(String email) {
        log.info("Проверка существования пользователя с email: {}", email);
        return userRepository.existsByEmail(email);
        //TODO Логика что выводит существует ли такоей то пользователь
    }

    @Override
    public List<UserDto> getUserByResponse() {
        List<User> users = userRepository.getUserByResponse();
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
                .accountType(user.getAccountType().getId())
                .enabled(user.getEnabled())
                .build();
    }

    @Override
    public UserDto auxiliaryMethodUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getEmail())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public User findById(Long id) {
        return getEntityOrThrow(userRepository.findById(id), new UserServiceException("User not found"));
    }


}