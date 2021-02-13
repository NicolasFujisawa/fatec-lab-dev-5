package fatec.labdev.projeto.pollingapp.option.service;

import fatec.labdev.projeto.pollingapp.option.model.Option;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OptionService {

    List<Option> saveAll(List<Option> options);

    Option save(Option option);

    Optional<Option> findById(UUID id);

    void deleteById(UUID id);
}
