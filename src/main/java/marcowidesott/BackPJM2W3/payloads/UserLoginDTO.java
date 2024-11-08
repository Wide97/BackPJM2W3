package marcowidesott.BackPJM2W3.payloads;


import lombok.Data;

@Data
public class UserLoginDTO {
    private String username;
    private String password;
}
