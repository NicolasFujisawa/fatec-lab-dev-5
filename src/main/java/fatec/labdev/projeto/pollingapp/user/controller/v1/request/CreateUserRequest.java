package fatec.labdev.projeto.pollingapp.user.controller.v1.request;

import fatec.labdev.projeto.pollingapp.user.enums.UserRole;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private UserRole role;
}
