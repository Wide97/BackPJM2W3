package marcowidesott.BackPJM2W3.services;

import marcowidesott.BackPJM2W3.entities.User;
import marcowidesott.BackPJM2W3.exceptions.BadRequestException;
import marcowidesott.BackPJM2W3.exceptions.UnauthorizedException;
import marcowidesott.BackPJM2W3.payloads.NewUserDTO;
import marcowidesott.BackPJM2W3.payloads.UserLoginDTO;
import marcowidesott.BackPJM2W3.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWT jwt;

    /**
     * Registra un nuovo utente.
     */
    public User register(NewUserDTO body) {
        // 1. Verifico che l'username non sia già in uso
        if (userService.existsByUsername(body.getUsername())) {
            throw new BadRequestException("Username " + body.getUsername() + " già in uso!");
        }

        // 2. Creo un nuovo utente
        User newUser = new User(
                body.getUsername(),
                passwordEncoder.encode(body.getPassword()),
                body.getEmail()
        );

        // 3. Salvo il nuovo utente
        return userService.save(newUser);
    }

    /**
     * Effettua il login dell'utente.
     */
    public String login(UserLoginDTO body) {
        // 1. Trova l'utente per username
        User user = userService.findByUsername(body.getUsername());

        // 2. Verifico la password
        if (!passwordEncoder.matches(body.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Password non corretta");
        }

        // 3. Genero il token JWT
        return jwt.generateToken(user.getUsername());
    }
}


