package pl.damian.zamawiam.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.security.auth.AuthenticationFacade;
import pl.damian.zamawiam.security.user.User;
import pl.damian.zamawiam.security.user.UserDetailsImpl;
import pl.damian.zamawiam.security.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendMapper friendMapper;

    public List<FriendDTO> findFriends() {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        User user = userRepository.getOne(userDetails.getId());
        return friendRepository.findByUser(user).stream().map(friendMapper::toDto).collect(Collectors.toList());
    }

    public List<FriendDTO> saveFriends(List<FriendDTO> friendDTOs) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        User user = userRepository.getOne(userDetails.getId());
        friendDTOs = friendDTOs.stream().map(friendDTO -> {
           if(friendDTO.getFriendId()==null){
               Optional<User> friend = userRepository.findFirstByEmailIgnoreCase(friendDTO.getEmail());
               return friend.ifPresent(friendDTO.setFriendId(friend.get().getId()));
           }
        }).collect(Collectors.toList());
        return friendDTOs.stream()
                .map(friendMapper::toEntity)
                .map(friendRepository::save)
                .map(friendMapper::toDto)
                .collect(Collectors.toList());
    }

    private Long findUserByEmail(String email){
        userRepository.findFirstByEmailIgnoreCase()
    }
}
