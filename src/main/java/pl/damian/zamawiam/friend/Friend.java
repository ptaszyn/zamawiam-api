package pl.damian.zamawiam.friend;

import lombok.Data;
import pl.damian.zamawiam.security.user.User;

import javax.persistence.*;

@Entity(name="friends")
@Data
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
