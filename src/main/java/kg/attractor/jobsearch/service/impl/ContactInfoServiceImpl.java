package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.exception.NumberFormatException.UserServiceException;
import kg.attractor.jobsearch.model.ContactType;
import kg.attractor.jobsearch.repos.ContactTypeRepository;
import kg.attractor.jobsearch.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public ContactType toContactTypeEntity(Long typeId) {
        return contactTypeRepository.findById(typeId)
                .orElseThrow(() -> new UserServiceException("No such contact type"));
    }
}
