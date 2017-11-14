package db.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService{
    @Autowired
    ActivityDao activityDao;

    @Transactional
    @Override
    public boolean getActivate(Long id) {
        return activityDao.getActivate(id);
    }

    @Transactional
    @Override
    public void setActivate(Long id, Boolean active) {
        activityDao.setActivate(id,active);
    }
}
