package fatec.labdev.projeto.pollingapp.user.service;

import java.util.Optional;
import java.util.UUID;

import fatec.labdev.projeto.pollingapp.user.model.User;

public interface UserService {

    User save(User user);

    Optional<User> findById(UUID id);

    boolean existsByUsername(String username);

    void deleteById(UUID id);
}