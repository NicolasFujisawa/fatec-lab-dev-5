package fatec.labdev.projeto.pollingapp.poll.repository;

import java.util.UUID;

import fatec.labdev.projeto.pollingapp.poll.model.Poll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, UUID> {
}
