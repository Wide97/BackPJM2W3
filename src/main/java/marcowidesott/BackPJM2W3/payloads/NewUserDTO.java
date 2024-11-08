package marcowidesott.BackPJM2W3.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NewUserDTO {
    @NotBlank(message = "Username è obbligatorio")
    @Size(min = 3, max = 20, message = "Username deve avere tra 3 e 20 caratteri")
    private String username;

    @NotBlank(message = "Password è obbligatoria")
    @Size(min = 6, max = 20, message = "Password deve avere tra 6 e 20 caratteri")
    private String password;

    @Email(message = "Email non valida")
    private String email;

    private boolean isOrganizer;
}
