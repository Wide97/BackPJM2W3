package marcowidesott.BackPJM2W3.services;


import marcowidesott.BackPJM2W3.entities.User;
import marcowidesott.BackPJM2W3.exceptions.NotFoundException;
import marcowidesott.BackPJM2W3.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * Trova un utente per username.
     */
    public User findByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con username: " + username));
    }

    /**
     * Salva un nuovo utente.
     */
    public User save(User user) {
        return usersRepository.save(user);
    }

    /**
     * Verifica se un utente con lo username specificato esiste già.
     */
    public boolean existsByUsername(String username) {
        return usersRepository.findByUsername(username).isPresent();
    }
}
