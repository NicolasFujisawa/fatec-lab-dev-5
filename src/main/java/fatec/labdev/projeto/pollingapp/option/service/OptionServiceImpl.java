package fatec.labdev.projeto.pollingapp.option.service;

import fatec.labdev.projeto.pollingapp.common.exceptions.EntityNotFoundException;
import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.option.repository.OptionRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements OptionService {

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
    public Option findById(UUID id) {
        return this.optionRepository.findById(id)
                                    .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteById(UUID id) {
        this.optionRepository.deleteById(id);
    }
}
