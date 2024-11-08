package marcowidesott.BackPJM2W3.exceptions;

import marcowidesott.BackPJM2W3.payloads.ErrorsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandler {

    // Gestione delle eccezioni personalizzate NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorsResponseDTO> handleNotFoundException(NotFoundException ex) {
        ErrorsResponseDTO response = new ErrorsResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Gestione delle eccezioni personalizzate BadRequestException
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorsResponseDTO> handleBadRequestException(BadRequestException ex) {
        ErrorsResponseDTO response = new ErrorsResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Gestione delle eccezioni personalizzate UnauthorizedException
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorsResponseDTO> handleUnauthorizedException(UnauthorizedException ex) {
        ErrorsResponseDTO response = new ErrorsResponseDTO(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // Gestione delle eccezioni di validazione
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Gestione generale per tutte le altre eccezioni
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorsResponseDTO> handleException(Exception ex) {
        ErrorsResponseDTO response = new ErrorsResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Errore del server");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}