package pl.damian.zamawiam.service.service.security.impl.userDetails;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.damian.zamawiam.repo.model.security.User;
import pl.damian.zamawiam.repo.repository.security.UserRepository;

/**
 * Finds a record from users database tables to build a UserDetails object for authentication.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmailIgnoreCase(email).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + email));
        return UserDetailsImpl.build(user);
    }
}
