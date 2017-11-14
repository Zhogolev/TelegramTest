package db.Logs;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Entity(name = "logs")
public class DBLogs implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "user_id", nullable = false)
    private Long user;

    @Column(name = "content",nullable = false, length = 10000)
    private String content;

    @Column(name = "date")
    private Date date;


    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Long getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}