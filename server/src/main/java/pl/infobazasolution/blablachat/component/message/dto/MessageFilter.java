package pl.infobazasolution.blablachat.component.message.dto;

import pl.infobazasolution.blablachat.common.Filter;

public class MessageFilter extends Filter {

    private Integer senderId;
    private Integer receiverId;
    private Integer numberOfLastMessages;

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public void setNumberOfLastMessages(Integer numberOfLastMessages) {
        this.numberOfLastMessages = numberOfLastMessages;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public Integer getNumberOfLastMessages() {
        return numberOfLastMessages;
    }
}
