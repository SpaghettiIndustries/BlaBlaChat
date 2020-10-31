package pl.infobazasolution.blablachat.component.user.dto;

import java.time.ZonedDateTime;

public class LoggedInUser {
    private Integer id;
    private String nick;
    private String email;
    private ZonedDateTime createdAt;
    private String token;

    public LoggedInUser(UserDto userDto, String token) {
        this.id = userDto.getId();
        this.nick = userDto.getNick();
        this.email = userDto.getEmail();
        this.createdAt = userDto.getCreatedAt();

        this.token = token;
    }

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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
