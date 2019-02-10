package pl.damian.zamawiam.security.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MessageResponse {

    @NonNull
    private String message;
}
