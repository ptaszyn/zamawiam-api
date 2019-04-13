package pl.damian.zamawiam.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendResource {

    @Autowired
    private FriendService friendService;

    @GetMapping
    public List<FriendDTO> getFriends(){
        return friendService.findFriends();
    }


}
