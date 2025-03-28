package kg.attractor.jobsearch.exception;

import java.util.NoSuchElementException;

public class CategoryNotFoundException extends NoSuchElementException {
    public CategoryNotFoundException() {
        super(
                "Category not found"
        );
    }
}
