package fatec.labdev.projeto.pollingapp.poll.service;

import fatec.labdev.projeto.pollingapp.common.exceptions.EntityNotFoundException;
import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.poll.model.Poll;
import fatec.labdev.projeto.pollingapp.poll.repository.PollRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @PreAuthorize("isAuthenticated()")
    @Override
    public Poll save(Poll poll) {
        return this.pollRepository.save(poll);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public Poll findById(UUID id) {
        return this.pollRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteById(UUID id) {
        this.pollRepository.deleteById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public List<Option> mostVotedOptions(UUID id) {
        return this.pollRepository.mostVotedOptions(id);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public List<Poll> findByOwnerAndIsEnabled(UUID ownerId, Boolean isEnabled) {
        return pollRepository.findByOwnerAndIsEnabled(ownerId, isEnabled);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public List<Poll> findEnabledByOwner(UUID ownerId) {
        return findByOwnerAndIsEnabled(ownerId, true);
    }

    @Override
    public List<Poll> findAll() {
        return this.pollRepository.findAll();
    }
}
