package marcowidesott.BackPJM2W3.controllers;

import jakarta.validation.Valid;
import marcowidesott.BackPJM2W3.payloads.NewUserDTO;
import marcowidesott.BackPJM2W3.payloads.UserLoginDTO;
import marcowidesott.BackPJM2W3.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody NewUserDTO newUserDTO) {
        return ResponseEntity.ok(authService.register(newUserDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        String token = authService.login(userLoginDTO);
        return ResponseEntity.ok(token);
    }
}