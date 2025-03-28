package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.exception.IdFormatCheckException;

import java.util.Optional;

public class MethodClass {
    protected Long parseId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IdFormatCheckException();
        }
    }
    protected  <T> T getEntityOrThrow(Optional<T> optionalEntity, RuntimeException exception) {
        return optionalEntity.orElseThrow(() -> exception);
    }
}
