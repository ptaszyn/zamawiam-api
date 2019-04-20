package pl.damian.zamawiam.service.service.friend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.repo.model.friend.Friend;
import pl.damian.zamawiam.repo.model.security.User;
import pl.damian.zamawiam.repo.repository.friend.FriendRepository;
import pl.damian.zamawiam.repo.repository.security.UserRepository;
import pl.damian.zamawiam.service.dto.friend.FriendDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;
import pl.damian.zamawiam.service.service.friend.FriendService;
import pl.damian.zamawiam.service.service.security.AuthenticationFacade;
import pl.damian.zamawiam.service.service.security.impl.userDetails.UserDetailsImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenericMapper<Friend, FriendDTO> friendMapper;

    @Override
    public List<FriendDTO> findFriends() {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        User user = userRepository.getOne(userDetails.getId());
        return friendMapper.toDTO(friendRepository.findByUser(user));
    }

    @Override
    public List<FriendDTO> saveFriends(List<FriendDTO> friendDTOs) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        User user = userRepository.getOne(userDetails.getId());
        /* DO POPRAWY
        friendDTOs = friendDTOs.stream().map(friendDTO -> {
            if(friendDTO.getFriendId()==null){
                Optional<User> friend = userRepository.findFirstByEmailIgnoreCase(friendDTO.getEmail());
                return friend.ifPresent(friendDTO.setFriendId(friend.get().getUserId()));
            }
        }).collect(Collectors.toList());        */
        return friendDTOs.stream()
                .map(friendMapper::toEntity)
                .map(friendRepository::save)
                .map(friendMapper::toDTO)
                .collect(Collectors.toList());
    }
}
