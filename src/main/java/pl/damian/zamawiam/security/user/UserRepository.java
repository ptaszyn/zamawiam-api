package pl.damian.zamawiam.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);
}
