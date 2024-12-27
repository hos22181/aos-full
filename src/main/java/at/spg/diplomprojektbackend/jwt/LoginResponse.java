package at.spg.diplomprojektbackend.jwt;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {

    private String message;
    private String role;
    private String redirect;
    private boolean verified;
    private String jwtToken;

}

