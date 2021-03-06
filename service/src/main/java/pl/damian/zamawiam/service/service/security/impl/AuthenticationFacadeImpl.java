package pl.damian.zamawiam.service.service.security.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.service.service.security.AuthenticationFacade;
import pl.damian.zamawiam.service.service.security.impl.userDetails.UserDetailsImpl;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public Long getUserId() {
        UserDetailsImpl userDetails = (UserDetailsImpl) getAuthentication().getPrincipal();
        return userDetails.getId();
    }


}
