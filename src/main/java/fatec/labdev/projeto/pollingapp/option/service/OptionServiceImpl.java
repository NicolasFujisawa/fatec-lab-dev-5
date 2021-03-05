package fatec.labdev.projeto.pollingapp.option.service;

import fatec.labdev.projeto.pollingapp.log.service.LogService;
import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.option.repository.OptionRepository;
import fatec.labdev.projeto.pollingapp.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private LogService logService;

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public List<Option> saveAll(List<Option> options) {
        return this.optionRepository.saveAll(options);
    }

    @Override
    public Option save(Option option) {
        return this.optionRepository.save(option);
    }

    @Override
    public Optional<Option> findById(UUID id) {
        return this.optionRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        this.optionRepository.deleteById(id);
    }

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
