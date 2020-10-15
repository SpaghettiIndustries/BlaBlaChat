package pl.infobazasolution.blablachat.component.user.session;


import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class UserSession implements Serializable {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
