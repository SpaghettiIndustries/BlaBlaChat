package pl.infobazasolution.blablachat.component.user.entity;

import java.sql.Timestamp;

public class User {
    private Integer id;
    private String nick;
    private String email;
    private String password;

    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;

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

    public Timestamp getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getDeletedAt() {
        return deleted_at;
    }

    public void setDeletedAt(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }
}
