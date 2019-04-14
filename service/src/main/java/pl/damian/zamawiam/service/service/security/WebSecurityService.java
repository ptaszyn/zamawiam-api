package pl.damian.zamawiam.service.service.security;

import pl.damian.zamawiam.service.dto.security.*;

public interface WebSecurityService {

    public LoginResponse login (LoginRequest loginRequest);

    public MessageResponse validRegister(LoginRequest loginRequest);

    public MessageResponse register (LoginRequest loginRequest);
}
