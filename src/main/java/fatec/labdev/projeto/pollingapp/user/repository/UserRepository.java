package fatec.labdev.projeto.pollingapp.user.repository;

import fatec.labdev.projeto.pollingapp.user.model.User;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUsername(String username);

    User findByUsername(String username);
}
