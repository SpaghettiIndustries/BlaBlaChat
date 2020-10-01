package pl.infobazasolution.blablachat.component.message.entity;

import pl.infobazasolution.blablachat.component.user.entity.User;

import java.util.Date;

public class Message {
    private Integer id;
    private User sender;
    private User receiver;
    private String content;

    private Date created_at;
    private Date updated_at;
    private Date deleted_at;
    private Date read_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeletedAt() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Date getReadAt() {
        return read_at;
    }

    public void setReadAt(Date read_at) {
        this.read_at = read_at;
    }
}
