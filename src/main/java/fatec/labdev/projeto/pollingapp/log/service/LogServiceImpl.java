package fatec.labdev.projeto.pollingapp.log.service;

import fatec.labdev.projeto.pollingapp.log.model.Log;
import fatec.labdev.projeto.pollingapp.log.repository.LogRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Override
    public Log createLog(String entity, String message) {
        Log log = Log
                .builder()
                .entity(entity)
                .message(message)
                .build();
        return logRepository.save(log);
    }

    @Override
    public List<Log> findByEntity(String entity) {
        return logRepository.findByEntity(entity);
    }
}
