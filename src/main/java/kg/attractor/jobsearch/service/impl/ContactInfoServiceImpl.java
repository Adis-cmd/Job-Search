package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exception.NumberFormatException.UserServiceException;
import kg.attractor.jobsearch.model.ContactInfo;
import kg.attractor.jobsearch.model.ContactType;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.repos.ContactInfoRepository;
import kg.attractor.jobsearch.repos.ContactTypeRepository;
import kg.attractor.jobsearch.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {
    private final ContactTypeRepository contactTypeRepository;
    private final ContactInfoRepository contactInfoRepository;

    @Override
    public ContactType toContactTypeEntity(Long typeId) {
        return contactTypeRepository.findById(typeId)
                .orElseThrow(() -> new UserServiceException("No such contact type"));
    }

    @Override
    @Transactional
    public void saveContactInfos(ResumeDto dto, Resume resume) {
        if (dto.getContactInfos() == null) return;
        dto.getContactInfos().forEach(info -> {
            ContactInfo contact = ContactInfo.builder()
                    .resumeId(resume)
                    .typeId(toContactTypeEntity(info.getTypeId()))
                    .value(info.getValue())
                    .build();
            System.out.println("Contact built: " + contact);
            contactInfoRepository.saveAndFlush(contact);
            System.out.println("Contact saved!");
        });
    }
}
