package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.model.ContactType;

public interface ContactInfoService {
    ContactType toContactTypeEntity(Long typeId);
}
