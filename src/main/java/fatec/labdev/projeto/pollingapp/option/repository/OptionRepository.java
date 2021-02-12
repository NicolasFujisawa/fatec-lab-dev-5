package fatec.labdev.projeto.pollingapp.option.repository;

import java.util.UUID;

import fatec.labdev.projeto.pollingapp.option.model.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, UUID> {
}
