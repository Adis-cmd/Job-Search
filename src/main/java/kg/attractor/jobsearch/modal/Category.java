package kg.attractor.jobsearch.modal;

import lombok.Data;

@Data
public class Category {
    private Long id;
    private String name;
    private Long parentId;
}
