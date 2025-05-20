package kg.attractor.jobsearch.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.NumberFormatException.UserServiceException;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.repos.UserRepository;
import kg.attractor.jobsearch.service.AccountTypeService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.util.CommonUtilities;
import kg.attractor.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends MethodClass implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccountTypeService accountTypeService;
    private final EmailServiceImpl emailService;
    private final MessageSource messageSource;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

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
    public String userLanguage(String email) {
        String message = messageSource.getMessage("user.service.notFound", null, Locale.getDefault());
        User user = getEntityOrThrow(userRepository.findByEmail(email), new UserServiceException(message));
        return user.getLanguage();
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
        String message = messageSource.getMessage("user.service.notFound", null, Locale.getDefault());
        User user = getEntityOrThrow(userRepository.findById(userId), new UserServiceException(message));
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
            String message = messageSource.getMessage("user.service.phoneNumber", null, Locale.getDefault());
            throw new UserServiceException(message);        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            String message = messageSource.getMessage("user.service.email", null, Locale.getDefault());
            throw new UserServiceException(message);
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
        user.setLanguage(userDto.getLanguage());
        user.setAccountType(accountTypeService.findById(accountTypeId));

        userRepository.save(user);
    }

    @Override
    public void registerGoogleUser(UserDto userDto, Long accountTypeId, String email) {
        String message = messageSource.getMessage("user.service.notFound", null, Locale.getDefault());
        User user = getEntityOrThrow(userRepository.findByEmail(email), new UserServiceException(message));
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(email);
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAccountType(accountTypeService.findById(accountTypeId));

        userRepository.saveAndFlush(user);
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
        String message = messageSource.getMessage("user.service.emailNotFound", new Object[]{email}, Locale.getDefault());
        User user = getEntityOrThrow(userRepository.findByEmail(email), new UserServiceException(message));
        return userDtos(user);
        //TODO Логика для поиска пользователя по его email
    }

    @Override
    public User getUserByEmail(String email) {
        String message = messageSource.getMessage("user.service.emailNotFound", new Object[]{email}, Locale.getDefault());
        return getEntityOrThrow(userRepository.findByEmail(email), new UserServiceException(message));
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Поиск пользователя с id: {}", id);
        String message = messageSource.getMessage("user.service.idNotFound", new Object[]{id}, Locale.getDefault());
        User user = getEntityOrThrow(userRepository.findById(id), new UserServiceException(message));
        return userDtos(user);
        //TODO Логика для поиска пользователя по его email
    }

    @Override
    public Long getUserId(String email) {
        Long userId = userRepository.findUSerByEmail(email);
        if (userId == null || userId == 0) {
            String message = messageSource.getMessage("user.service.emailNotFound", new Object[]{email}, Locale.getDefault());
            throw new UserServiceException(message);        }
        return userId;
    }

    @Override
    public Page<UserDto> findAllUserEmployee(Pageable pageable) {
        Page<User> users = userRepository.findAllUserEmployee(pageable);

        if (users == null) {
            String message = messageSource.getMessage("user.service.notFound", null, Locale.getDefault());
            throw new UserServiceException(message);        }
        return users.map(this::userDtos);
    }

    @Override
    public UserDto findUserEmployeeById(Long id) {
        String message = messageSource.getMessage("user.service.notFound", null, Locale.getDefault());
        User user = getEntityOrThrow(userRepository.findUserById(id), new UserServiceException(message));
        return userDtos(user);
    }


    @Override
    public UserDto getUserPhone(String phone) {
        log.info("Поиск пользователя с номером телефона: {}", phone);
        String message = messageSource.getMessage("user.service.phoneNumberNotFound", new Object[]{phone}, Locale.getDefault());
        User user = getEntityOrThrow(userRepository.findUserByPhoneNumber(phone), new UserServiceException(message));
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
        String message = messageSource.getMessage("user.service.notFound", null, Locale.getDefault());
        return getEntityOrThrow(userRepository.findById(id), new UserServiceException(message));    }

    private void updateResetPasswordToken(String token, String email) {
        String message = messageSource.getMessage("user.service.notFound", null, Locale.getDefault());
        User user = getEntityOrThrow(userRepository.findByEmail(email), new UserServiceException(message));
        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void makeResetPasswordToken(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        String resetPasswordLink = CommonUtilities.getSiteURL(request) + "/auth/reset_password?token=" + token;
        emailService.sendEmail(email, resetPasswordLink);
    }


    @Override
    public User getUserByResetPasswordToken(String token) {
        String message = messageSource.getMessage("user.service.notFound", null, Locale.getDefault());
        return getEntityOrThrow(userRepository.findUserByResetPasswordToken(token), new UserServiceException(message));
    }

    @Override
    public void updatePassword(User user, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }


    @Transactional
    @Override
    public void updateUserLanguage(String email, String lang) {
        User user = getUserByEmail(email);
        user.setLanguage(lang);
        userRepository.save(user);
    }


}