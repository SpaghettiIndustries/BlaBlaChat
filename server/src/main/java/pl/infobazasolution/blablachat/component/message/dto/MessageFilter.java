package pl.infobazasolution.blablachat.component.message.dto;

import pl.infobazasolution.blablachat.common.Filter;

public class MessageFilter extends Filter {

    private Integer topicId;
    private Integer numberOfMessages;
    private Integer startIndex;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getNumberOfMessages() {
        return numberOfMessages;
    }

    public void setNumberOfMessages(Integer numberOfMessages) {
        this.numberOfMessages = numberOfMessages;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
