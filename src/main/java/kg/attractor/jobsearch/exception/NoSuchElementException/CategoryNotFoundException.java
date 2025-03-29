package kg.attractor.jobsearch.exception.NoSuchElementException;

import java.util.NoSuchElementException;

public class CategoryNotFoundException extends NoSuchElementException {
    public CategoryNotFoundException() {
        super(
                "Category not found"
        );
    }
}
