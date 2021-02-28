package fatec.labdev.projeto.pollingapp.user.controller.v1.converter;

import fatec.labdev.projeto.pollingapp.user.controller.v1.request.UserRequest;
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

    public static User convertFrom(UserRequest userRequest) {
        return User.builder()
                   .username(userRequest.getUsername())
                   .password(userRequest.getPassword())
                   .role(userRequest.getRole())
                   .build();
    }
}
