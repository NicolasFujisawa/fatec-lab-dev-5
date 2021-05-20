package fatec.labdev.projeto.pollingapp.option.service;

import fatec.labdev.projeto.pollingapp.common.exceptions.EntityNotFoundException;
import fatec.labdev.projeto.pollingapp.log.service.LogService;
import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.option.repository.OptionRepository;
import fatec.labdev.projeto.pollingapp.user.model.User;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private LogService logService;

    @Autowired
    private OptionRepository optionRepository;

    @PreAuthorize("isAuthenticated()")
    @Override
    public List<Option> saveAll(List<Option> options) {
        return this.optionRepository.saveAll(options);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public Option save(Option option) {
        return this.optionRepository.save(option);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public Option findById(UUID id) {
        return this.optionRepository.findById(id)
                                    .orElseThrow(EntityNotFoundException::new);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public void deleteById(UUID id) {
        this.optionRepository.deleteById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    @Transactional
    public void vote(Option option, User user) {
        user.vote(option);
        logService.createLog(
                Option.class.getName(),
                String.format("User=%s vote for option=%s",
                        user.getId(),
                        option.getId()));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    @Transactional
    public void removeVote(Option option, User user) {
        user.removeVote(option);
        logService.createLog(
                Option.class.getName(),
                String.format("User=%s remove vote for option=%s",
                        user.getId(),
                        option.getId()));
    }
}
