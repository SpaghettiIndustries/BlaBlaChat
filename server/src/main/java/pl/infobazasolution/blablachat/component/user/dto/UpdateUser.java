package pl.infobazasolution.blablachat.component.user.dto;

import java.time.ZonedDateTime;
import java.util.Date;

public class UpdateUser {

    private Integer id;
    private String nick;
    private String email;
    private String password;
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
}
