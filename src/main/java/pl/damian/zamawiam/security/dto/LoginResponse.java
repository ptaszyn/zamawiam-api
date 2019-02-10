package pl.damian.zamawiam.security.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@RequiredArgsConstructor
public class LoginResponse {

    @NonNull
    private String token;

    private String type = "Bearer";

    @NonNull
    private String email;

    @NonNull
    private Collection<? extends GrantedAuthority> authorities;

}
