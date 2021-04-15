package fatec.labdev.projeto.pollingapp.option.service;

import fatec.labdev.projeto.pollingapp.option.model.Option;
import fatec.labdev.projeto.pollingapp.user.model.User;

import java.util.List;
import java.util.UUID;

public interface OptionService {

    List<Option> saveAll(List<Option> options);

    Option save(Option option);

    Option findById(UUID id);

    void deleteById(UUID id);

    void vote(Option option, User user);

    void removeVote(Option option, User user);

    Option update(Option option);
}
