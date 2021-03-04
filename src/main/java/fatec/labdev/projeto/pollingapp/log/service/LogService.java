package fatec.labdev.projeto.pollingapp.log.service;

import fatec.labdev.projeto.pollingapp.log.model.Log;

import java.util.List;

public interface LogService {
    Log createLog(String entity, String message);

    List<Log> findByEntity(String entity);
}
