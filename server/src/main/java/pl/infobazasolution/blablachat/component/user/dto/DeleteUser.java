package pl.infobazasolution.blablachat.component.user.dto;

public class DeleteUser {
    private Integer id;
    private String nick;

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
}
