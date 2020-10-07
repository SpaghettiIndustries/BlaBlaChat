package pl.infobazasolution.blablachat.component.user.dto;

import pl.infobazasolution.blablachat.common.Filter;

public class UserFilter extends Filter {

    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
