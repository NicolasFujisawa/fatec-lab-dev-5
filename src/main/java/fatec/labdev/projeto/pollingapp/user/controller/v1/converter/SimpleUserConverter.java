package fatec.labdev.projeto.pollingapp.user.controller.v1.converter;

import java.util.HashSet;
import java.util.Set;

import fatec.labdev.projeto.pollingapp.user.controller.v1.response.SimpleUserResponse;
import fatec.labdev.projeto.pollingapp.user.model.User;

public class SimpleUserConverter {
    public static SimpleUserResponse convertFrom(User user) {
        return SimpleUserResponse.builder()
                           .id(user.getId())
                           .build();
    }

    public static Set<SimpleUserResponse> convertManyFrom(Set<User> users) {
        Set<SimpleUserResponse> userResponses = new HashSet<>();
        for (User user : users){
            userResponses.add(SimpleUserConverter.convertFrom(user));
        }

        return userResponses;
    }
}
