package marcowidesott.BackPJM2W3.payloads;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDTO {
    @NotBlank(message = "Username è obbligatorio")
    private String username;

    @NotBlank(message = "Password è obbligatoria")
    private String password;
}