package marcowidesott.BackPJM2W3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    public Booking(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public Booking(Long id, User user, Event event) {
        this.id = id;
        this.user = user;
        this.event = event;
    }
}