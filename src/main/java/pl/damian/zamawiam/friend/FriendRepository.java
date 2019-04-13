package pl.damian.zamawiam.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.damian.zamawiam.security.user.User;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend,Long> {

    List<Friend> findByUser(User user);
}
