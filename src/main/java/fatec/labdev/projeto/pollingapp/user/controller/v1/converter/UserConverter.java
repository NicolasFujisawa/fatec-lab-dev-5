package fatec.labdev.projeto.pollingapp.user.controller.v1.converter;

import fatec.labdev.projeto.pollingapp.user.controller.v1.request.CreateUserRequest;
import fatec.labdev.projeto.pollingapp.user.controller.v1.response.UserResponse;
import fatec.labdev.projeto.pollingapp.user.model.User;

public class UserConverter {
    public static UserResponse convertFrom(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .username(user.getUsername())
            .role(user.getRole())
            .build();
    }

    public static User convertFrom(CreateUserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());

        return user;
    }
}
