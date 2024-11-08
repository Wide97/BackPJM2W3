package marcowidesott.BackPJM2W3.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorsResponseDTO {
    private int statusCode;
    private String message;
}
