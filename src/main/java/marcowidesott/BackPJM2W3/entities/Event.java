package marcowidesott.BackPJM2W3.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il titolo è obbligatorio")
    private String title;

    private String description;

    @Future(message = "La data dell'evento deve essere nel futuro")
    private LocalDateTime date;

    private int maxParticipants;

    @ManyToOne
    private User organizer;
}