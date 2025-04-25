package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.exception.AccountTypeException;
import kg.attractor.jobsearch.model.AccountType;
import kg.attractor.jobsearch.repos.AccountTypeRepository;
import kg.attractor.jobsearch.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl extends MethodClass implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;

    @Override
    public AccountType findById(Long id) {
        return getEntityOrThrow(accountTypeRepository.findById(id), new AccountTypeException("Not Found AccountType"));
    }
}
