package fatec.labdev.projeto.pollingapp.poll.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.labdev.projeto.pollingapp.common.exceptions.EntityNotFoundException;
import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import fatec.labdev.projeto.pollingapp.poll.repository.PollRepository;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @Override
    public Poll save(Poll poll) {
        return this.pollRepository.save(poll);
    }

    @Override
    public Poll findById(UUID id) {
        return this.pollRepository.findById(id)
                                  .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteById(UUID id) {
        this.pollRepository.deleteById(id);
    }

    @Override
    public List<Option> mostVotedOptions(UUID id) {
        return this.pollRepository.mostVotedOptions(id);
    }
}
