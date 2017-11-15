package db.Activity;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ActivityDAOImpl implements ActivityDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean getActivate(Long id) {
        List <DBActivity> list = em.createQuery("from activity c where c.user = :user", DBActivity.class)
                .setParameter("user", id)
                .getResultList();
        if (list.isEmpty()) {
            DBActivity actvt = new DBActivity();
            actvt.setActivity(false);
            actvt.setId(id);
            actvt.setUser(id);
            em.merge(actvt);
            return false;
        } else{
            return list.get(0).getActivity();
        }
    }


    @Override
    public void setActivate(Long id, Boolean active) {
        em.createQuery("update activity set activity=:activ where user = :user")
                .setParameter("user", id).setParameter("activ",active)
                .executeUpdate();
    }
}