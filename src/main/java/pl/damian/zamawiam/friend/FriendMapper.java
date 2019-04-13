package pl.damian.zamawiam.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.core.Mapper;
import pl.damian.zamawiam.security.user.User;
import pl.damian.zamawiam.security.user.UserRepository;

import java.util.Optional;

@Component
public class FriendMapper implements Mapper<Friend, FriendDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public FriendDTO toDto(Friend entity) {
        FriendDTO dto = new FriendDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setFriendId(entity.getFriend().getId());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    @Override
    public Friend toEntity(FriendDTO dto) {
        Friend entity = new Friend();
        entity.setId(dto.getId());
        if(dto.getUserId()!=null){
            Optional<User> user = userRepository.findById(dto.getUserId());
            user.ifPresent(entity::setUser);
        }
        if(dto.getFriendId()!=null){
            Optional<User> user = userRepository.findById(dto.getFriendId());
            user.ifPresent(entity::setFriend);
        }
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
