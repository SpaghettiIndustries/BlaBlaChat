package pl.infobazasolution.blablachat.component.message.entity;

import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "message_generator")
    @SequenceGenerator(name = "message_generator", sequenceName = "message_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "sender_id")
    private User sender;

    @NotNull
    @Column(name = "receiver_id")
    private User receiver;

    @NotBlank
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "read_at")
    private Date readAt;

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
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Date getReadAt() {
        return readAt;
    }

    public void setReadAt(Date readAt) {
        this.readAt = readAt;
    }
}
