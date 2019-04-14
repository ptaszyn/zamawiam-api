package pl.damian.zamawiam.service.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.repo.model.security.Role;
import pl.damian.zamawiam.repo.model.security.RoleName;
import pl.damian.zamawiam.repo.model.security.User;
import pl.damian.zamawiam.repo.repository.security.RoleRepository;
import pl.damian.zamawiam.repo.repository.security.UserRepository;
import pl.damian.zamawiam.service.dto.security.LoginRequest;
import pl.damian.zamawiam.service.dto.security.LoginResponse;
import pl.damian.zamawiam.service.dto.security.MessageResponse;
import pl.damian.zamawiam.service.service.security.WebSecurityService;
import pl.damian.zamawiam.service.service.security.impl.jwt.JwtProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class WebSecurityServiceImpl implements WebSecurityService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new LoginResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    }

    @Override
    public MessageResponse validRegister(LoginRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new MessageResponse("Fail -> Email is already in use!");
        }
        return null;
    }

    @Override
    public MessageResponse register(LoginRequest signUpRequest) {
        User user = new User(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = new HashSet<>(Arrays.asList("user"));
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }
}
