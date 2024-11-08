package marcowidesott.BackPJM2W3.repositories;

import marcowidesott.BackPJM2W3.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}
