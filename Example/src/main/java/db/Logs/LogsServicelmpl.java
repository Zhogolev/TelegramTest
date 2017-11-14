package db.Logs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;


@Service("LogService")
public class LogsServicelmpl implements LogsService{

    @Autowired
    private LogsDAO logsDAO;

    @Override
    public List<DBLogs> getLogs(Long id) {
        return logsDAO.getLogs(id);
    }

    @Transactional
    @Override
    public void save(DBLogs logs) {
        logsDAO.save(logs);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        logsDAO.delete(id);
    }
}
