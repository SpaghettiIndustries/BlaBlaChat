package pl.infobazasolution.blablachat.component.topic.dto;

import pl.infobazasolution.blablachat.common.Filter;

public class TopicFilter extends Filter {

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
