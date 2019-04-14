package pl.damian.zamawiam.repo.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damian.zamawiam.repo.model.security.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);
}
