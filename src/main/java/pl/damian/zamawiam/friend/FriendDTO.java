package pl.damian.zamawiam.friend;

import lombok.Data;

@Data
public class FriendDTO {

    private Long id;

    private Long userId;

    private Long friendId;

    private String email;
}
