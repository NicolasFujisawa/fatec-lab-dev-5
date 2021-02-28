package fatec.labdev.projeto.pollingapp.user.controller.v1.response;

import java.util.UUID;
import fatec.labdev.projeto.pollingapp.user.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    private UUID id;
    private String username;
    private UserRole role;
}
