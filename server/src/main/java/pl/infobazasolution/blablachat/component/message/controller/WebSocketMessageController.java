package pl.infobazasolution.blablachat.component.message.controller;

import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.encoder.MessageSentEventEncoder;
import pl.infobazasolution.blablachat.component.message.event.MessageSentEvent;
import pl.infobazasolution.blablachat.component.topic.dao.TopicDao;
import pl.infobazasolution.blablachat.component.topic.entity.Topic;
import pl.infobazasolution.blablachat.component.user.session.UserSession;

import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint(
    value="/message/socket",
    encoders = MessageSentEventEncoder.class
)
public class WebSocketMessageController {

    @Inject
    private TopicDao topicDao;

    @Inject
    private UserSession userSession;

    private Session session;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
    }

    public void onMessageSent(@ObservesAsync MessageSentEvent messageSentEvent) throws IOException, EncodeException {
        Topic topic = topicDao.findById(messageSentEvent.getId()).get();

        Integer receiverId = messageSentEvent.getSenderId().equals(topic.getFirstUser().getId()) ? topic.getSecondUser().getId() : topic.getFirstUser().getId();

        if (userSession.getId().equals(receiverId)) {
            session.getBasicRemote().sendObject(messageSentEvent);
        }
    }
}
