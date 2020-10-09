package pl.infobazasolution.blablachat.component.message.dto;

import pl.infobazasolution.blablachat.common.Filter;

public class MessageFilter extends Filter {

    private Integer topicId;
    private Integer numberOfLastMessages;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getNumberOfLastMessages() {
        return numberOfLastMessages;
    }

    public void setNumberOfLastMessages(Integer numberOfLastMessages) {
        this.numberOfLastMessages = numberOfLastMessages;
    }
}
