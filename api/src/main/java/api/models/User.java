package api.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Table(schema = "custom_survey", name = "tb_users")
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "sq_users", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "sq_users",
            schema = "custom_survey",
            sequenceName = "sq_users",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
