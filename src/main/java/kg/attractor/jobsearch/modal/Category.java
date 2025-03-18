package kg.attractor.jobsearch.modal;

import lombok.Data;

@Data
public class Category {
    private Integer id;
    private String name;
    private Integer parentId;
}
