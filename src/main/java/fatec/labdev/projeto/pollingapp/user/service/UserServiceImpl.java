package fatec.labdev.projeto.pollingapp.user.service;

import java.util.Optional;
import java.util.UUID;

import fatec.labdev.projeto.pollingapp.user.model.User;

import fatec.labdev.projeto.pollingapp.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return this.userRepository.findById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public void deleteById(UUID id) {
        this.userRepository.deleteById(id);
    }
}
