package db.Logs;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class LogsDAOlmpl implements LogsDAO {
    final int MAXIMUM_RESULTS_LOG = 10;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DBLogs> getLogs(Long curent_id) {
        return  em.createQuery("from logs c where c.user = :user order by c.date desc ", DBLogs.class)
                .setParameter("user", curent_id).setMaxResults(MAXIMUM_RESULTS_LOG)
                .getResultList();
    }
    @Override
    public void save(DBLogs logs) {
        em.merge(logs);
    }

    @Override
    public void delete(Long id) {
        em.createQuery("delete from logs c where c.user =:user").setParameter("user", id).executeUpdate();
    }
}
