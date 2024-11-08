package marcowidesott.BackPJM2W3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType name;

    public Role(Long id, RoleType name) {
        this.id = id;
        this.name = name;
    }

    public enum RoleType {
        USER,
        ORGANIZER
    }
}