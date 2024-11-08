package marcowidesott.BackPJM2W3.repositories;

import marcowidesott.BackPJM2W3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
