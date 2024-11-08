package marcowidesott.BackPJM2W3.payloads;

import lombok.Data;

@Data
public class NewUserDTO {
    private String username;
    private String password;
    private String email;
    private boolean isOrganizer;
}
