package fatec.labdev.projeto.pollingapp.poll.repository;

import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.poll.model.Poll;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PollRepository extends JpaRepository<Poll, UUID> {

    @Query(value = "SELECT opt from Poll pol \n" +
            "JOIN pol.options opt \n" +
            "WHERE opt.votes.size = (SELECT max(opt2.votes.size) from pol.options opt2) AND \n" +
            "pol.id = ?1")
    List<Option> mostVotedOptions(UUID pollId);
}
