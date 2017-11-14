package db.Logs;

import java.math.BigInteger;
import java.util.List;

public interface LogsDAO {
    public List<DBLogs> getLogs(Long id);
    public void save(DBLogs logs);
    public void delete(Long id);
}
