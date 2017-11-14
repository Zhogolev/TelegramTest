package db.Activity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity(name = "activity")
public class DBActivity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false,unique = true)
    private Long user;

    @Column(name = "activity",nullable = false)
    private boolean activity;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public void setActivity(Boolean content) {
        this.activity = content;
    }

    public Long getId() {
        return id;
    }

    public Long getUser() {
        return user;
    }

    public boolean getActivity() {
        return activity;
    }
}
