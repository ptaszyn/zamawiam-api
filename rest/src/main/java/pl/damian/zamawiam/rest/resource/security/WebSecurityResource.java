package pl.damian.zamawiam.rest.resource.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.damian.zamawiam.service.dto.security.LoginRequest;
import pl.damian.zamawiam.service.dto.security.MessageResponse;
import pl.damian.zamawiam.service.service.security.WebSecurityService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class WebSecurityResource {

    @Autowired
    private WebSecurityService webSecurityService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(webSecurityService.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody LoginRequest signUpRequest) {
        MessageResponse messageResponse = webSecurityService.validRegister(signUpRequest);
        if (messageResponse!=null) {
            return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(webSecurityService.register(signUpRequest), HttpStatus.OK);
    }
}
