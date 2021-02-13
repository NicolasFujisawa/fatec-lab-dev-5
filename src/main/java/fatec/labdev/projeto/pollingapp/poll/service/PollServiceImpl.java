package fatec.labdev.projeto.pollingapp.poll.service;

import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import fatec.labdev.projeto.pollingapp.poll.repository.PollRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @Override
    public Poll save(Poll poll) {
        return this.pollRepository.save(poll);
    }

    @Override
    public Optional<Poll> findById(UUID id) {
        return this.pollRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        this.pollRepository.deleteById(id);
    }
}