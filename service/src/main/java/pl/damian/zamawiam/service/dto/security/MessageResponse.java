package pl.damian.zamawiam.service.dto.security;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MessageResponse {

    @NonNull
    private String message;
}
