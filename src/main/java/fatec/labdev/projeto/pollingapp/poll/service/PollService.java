package fatec.labdev.projeto.pollingapp.poll.service;

import java.util.Optional;
import java.util.UUID;

import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import fatec.labdev.projeto.pollingapp.user.model.User;

public interface PollService {

    Poll save(Poll poll);

    Optional<Poll> findById(UUID id);

    void deleteById(UUID id);
}
