package pl.damian.zamawiam.service.mapper.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.repo.model.friend.Friend;
import pl.damian.zamawiam.repo.model.security.User;
import pl.damian.zamawiam.repo.repository.security.UserRepository;
import pl.damian.zamawiam.service.dto.friend.FriendDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;

import java.util.Optional;

@Component
public class FriendMapper extends GenericMapper<Friend, FriendDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected Friend initEntity() {
        return new Friend();
    }

    @Override
    protected FriendDTO initDTO() {
        return new FriendDTO();
    }

    @Override
    protected void mapEntitytoDTO(Friend friend, FriendDTO friendDTO) {
        friendDTO.setId(friend.getId());
        friendDTO.setUserId(friend.getUser().getId());
        friendDTO.setFriendId(friend.getFriend().getId());
        friendDTO.setEmail(friend.getEmail());
    }

    @Override
    protected void mapDTOToEntity(FriendDTO friendDTO, Friend friend) {
        friend.setId(friendDTO.getId());
        if(friendDTO.getUserId()!=null){
            Optional<User> user = userRepository.findById(friendDTO.getUserId());
            user.ifPresent(friend::setUser);
        }
        if(friendDTO.getFriendId()!=null){
            Optional<User> user = userRepository.findById(friendDTO.getFriendId());
            user.ifPresent(friend::setFriend);
        }
        friend.setEmail(friendDTO.getEmail());
    }
}
