package marcowidesott.BackPJM2W3.services;

import marcowidesott.BackPJM2W3.entities.Role;
import marcowidesott.BackPJM2W3.entities.User;
import marcowidesott.BackPJM2W3.exceptions.BadRequestException;
import marcowidesott.BackPJM2W3.exceptions.NotFoundException;
import marcowidesott.BackPJM2W3.exceptions.UnauthorizedException;
import marcowidesott.BackPJM2W3.payloads.NewUserDTO;
import marcowidesott.BackPJM2W3.payloads.UserLoginDTO;
import marcowidesott.BackPJM2W3.repositories.RoleRepository;
import marcowidesott.BackPJM2W3.repositories.UsersRepository;
import marcowidesott.BackPJM2W3.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWT jwt;

    public String login(UserLoginDTO body) {
        // Cerca l'utente nel database tramite username
        User user = usersRepository.findByUsername(body.getUsername())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        // Verifica se la password è corretta
        if (!passwordEncoder.matches(body.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Password non corretta");
        }

        // Genera e restituisce un token JWT
        return jwt.generateToken(user);
    }

    public User register(NewUserDTO body) {
        // Verifica se l'username è già in uso
        usersRepository.findByUsername(body.getUsername()).ifPresent(user -> {
            throw new BadRequestException("Username già in uso");
        });

        // Crea un nuovo utente
        User newUser = new User(
                body.getUsername(),
                passwordEncoder.encode(body.getPassword()),
                body.getEmail()
        );

        // Recupera il ruolo dal database oppure crea un nuovo ruolo
        Role role = roleRepository.findByRoleName(
                body.isOrganizer() ? "ROLE_ORGANIZER" : "ROLE_USER"
        ).orElseGet(() -> new Role(body.isOrganizer() ? "ROLE_ORGANIZER" : "ROLE_USER"));

        // Assegna il ruolo all'utente
        newUser.setRoles(Set.of(role));

        // Salva l'utente nel database
        return usersRepository.save(newUser);
    }
}



