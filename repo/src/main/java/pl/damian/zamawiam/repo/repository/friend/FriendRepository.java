package pl.damian.zamawiam.repo.repository.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damian.zamawiam.repo.model.friend.Friend;
import pl.damian.zamawiam.repo.model.security.User;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Long> {

    List<Friend> findByUser(User user);
}
