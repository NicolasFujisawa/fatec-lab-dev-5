package fatec.labdev.projeto.pollingapp.poll.service;

import fatec.labdev.projeto.pollingapp.poll.model.Poll;

import java.util.Optional;
import java.util.UUID;

public interface PollService {

    Poll save(Poll poll);

    Optional<Poll> findById(UUID id);

    void deleteById(UUID id);
}
