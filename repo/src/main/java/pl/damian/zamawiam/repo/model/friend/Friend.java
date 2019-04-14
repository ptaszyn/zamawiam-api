package pl.damian.zamawiam.repo.model.friend;

import lombok.Data;
import pl.damian.zamawiam.repo.model.security.User;

import javax.persistence.*;

@Data
@Entity(name="friends")
public class Friend {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private User friend;

    private String email;
}
