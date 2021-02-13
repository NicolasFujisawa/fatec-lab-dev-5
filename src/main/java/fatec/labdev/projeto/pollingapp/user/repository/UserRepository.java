package fatec.labdev.projeto.pollingapp.user.repository;

import java.util.UUID;

import fatec.labdev.projeto.pollingapp.user.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUsername(String username);
}
