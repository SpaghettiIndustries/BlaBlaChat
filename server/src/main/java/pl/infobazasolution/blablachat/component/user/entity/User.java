package pl.infobazasolution.blablachat.component.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Table(name = "user_")
public class User {
    @Id
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "nick")
    private String nick;

    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "upZonedDateTimed_at")
    private ZonedDateTime upZonedDateTimedAt;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpZonedDateTimedAt() {
        return upZonedDateTimedAt;
    }

    public void setUpZonedDateTimedAt(ZonedDateTime upZonedDateTimedAt) {
        this.upZonedDateTimedAt = upZonedDateTimedAt;
    }

    public ZonedDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(ZonedDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
