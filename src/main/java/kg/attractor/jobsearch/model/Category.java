package kg.attractor.jobsearch.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Category {
    private Long id;
    private String name;
    private Long parentId;
}
