package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "contact_info", schema = "public")
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPEID")
    private ContactType typeId;
    @ManyToOne
    @JoinColumn(name = "RESUMEID")
    private Resume resumeId;
    @Column(name = "info_value")
    private String value;
}
