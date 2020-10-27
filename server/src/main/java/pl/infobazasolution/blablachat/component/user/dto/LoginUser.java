package pl.infobazasolution.blablachat.component.user.dto;

public class LoginUser {
    private String nick;
    private String password;

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }
}
