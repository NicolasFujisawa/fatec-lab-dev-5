package fatec.labdev.projeto.pollingapp.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import fatec.labdev.projeto.pollingapp.user.controller.v1.response.UserResponse;
import fatec.labdev.projeto.pollingapp.user.model.User;

import java.util.UUID;

public interface UserService {

    User save(User user);

    User update(User user);

    User findById(UUID id);

    boolean existsByUsername(String username);

    User findByUsername(String username);

    void deleteById(UUID id);

    UserResponse signInUser(User userRequest) throws JsonProcessingException;
}
