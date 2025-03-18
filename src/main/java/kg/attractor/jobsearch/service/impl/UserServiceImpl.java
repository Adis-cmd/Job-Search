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

    }

}
