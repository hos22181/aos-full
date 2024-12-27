package at.spg.diplomprojektbackend.customer;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LoginDTO {
    private String email;
    private String jwtToken;
}

