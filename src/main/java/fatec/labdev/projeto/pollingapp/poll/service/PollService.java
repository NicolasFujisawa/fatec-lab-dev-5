package fatec.labdev.projeto.pollingapp.poll.service;

import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.poll.model.Poll;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PollService {

    Poll save(Poll poll);

    Optional<Poll> findById(UUID id);

    void deleteById(UUID id);

    List<Option> optionMostVoted(UUID id);
}
