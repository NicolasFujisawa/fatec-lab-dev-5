package fatec.labdev.projeto.pollingapp.log.repository;

import fatec.labdev.projeto.pollingapp.log.model.Log;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository  extends JpaRepository<Log, UUID> {
    List<Log> findByEntity(String entity);
}
