package fatec.labdev.projeto.pollingapp.poll.repository;

import fatec.labdev.projeto.pollingapp.poll.model.Poll;

import javax.transaction.Transactional;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PollRepository extends JpaRepository<Poll, UUID> {
}
