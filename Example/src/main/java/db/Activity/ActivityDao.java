package db.Activity;

import java.math.BigInteger;

public interface ActivityDao {
    public boolean getActivate(Long id);
    public void setActivate(Long id, Boolean active);
}
