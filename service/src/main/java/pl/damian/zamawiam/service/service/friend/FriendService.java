package pl.damian.zamawiam.service.service.friend;

import pl.damian.zamawiam.service.dto.friend.FriendDTO;

import java.util.List;

public interface FriendService {

    public List<FriendDTO> findFriends();

    public List<FriendDTO> saveFriends(List<FriendDTO> friendDTOs);
}
