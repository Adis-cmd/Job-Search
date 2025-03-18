package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.VacanciesDto;
import kg.attractor.jobsearch.modal.Vacancy;
import kg.attractor.jobsearch.service.VacanciesService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacanciesServiceImpl implements VacanciesService {

    @Override
    public void editVacancies(VacanciesDto vacanciesDto , int vacancyId) {
        //TODO логика для редактирование вакансии
    }

    @Override
    public void createVacancies(VacanciesDto vacanciesDto) {
        //TODO логика для создании вакансии
    }


    @Override
    public void responseVacancies(Integer vacancyId, User user) {
        //TODO логика для отклика вакансии
    }

    @Override
    public void deleteVacancies(Integer vacancyId, Vacancy vacancies) {
        //TODO логика для удаление вакансии по id
    }

    @Override
    public List<Vacancy> getAllVacanciesCategory(Integer categoryId, Vacancy vacancies) {
        //TODO логика для поиска вакансии по категории
        return List.of();
    }

    @Override
    public List<Vacancy> getAllVacanciesIsActive(Vacancy vacancies) {
        //TODO логика для поиска всех активных вакансий
        return List.of();
    }
}
