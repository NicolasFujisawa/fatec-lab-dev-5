package fatec.labdev.projeto.pollingapp.user.controller.v1.response;

import java.util.UUID;
import fatec.labdev.projeto.pollingapp.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID id;
    private String username;
    private UserRole role;
    private String token;
}
