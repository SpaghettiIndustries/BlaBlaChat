package pl.infobazasolution.blablachat.component.topic.dto;

import pl.infobazasolution.blablachat.component.user.entity.User;

public class NewTopic {

    private Integer firstUserId;
    private Integer secondUserId;

    public Integer getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserId(Integer firstUserId) {
        this.firstUserId = firstUserId;
    }

    public Integer getSecondUserId() {
        return secondUserId;
    }

    public void setSecondUserId(Integer secondUserId) {
        this.secondUserId = secondUserId;
    }
}
