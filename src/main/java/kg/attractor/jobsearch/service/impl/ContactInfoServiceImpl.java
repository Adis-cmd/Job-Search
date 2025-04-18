package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ContactTypeDto;
import kg.attractor.jobsearch.model.ContactType;
import kg.attractor.jobsearch.repos.ContactInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl {

    private final ContactInfoRepository contactInfoRepository;

    protected ContactType toContactTypeEntity(ContactTypeDto dto) {
        return ContactType.builder()
                .id(dto.getId())
                .type(dto.getType())
                .build();
    }
}
