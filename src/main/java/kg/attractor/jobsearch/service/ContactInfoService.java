package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.model.ContactType;
import kg.attractor.jobsearch.model.Resume;

public interface ContactInfoService {
    ContactType toContactTypeEntity(Long typeId);

    void saveContactInfos(ResumeDto dto, Resume resume);
}
