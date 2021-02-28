package fatec.labdev.projeto.pollingapp.user.controller.v1.request;

import fatec.labdev.projeto.pollingapp.user.enums.UserRole;
import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private UserRole role;

    public UserRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(UserRole.MEMBER);
    }
}
