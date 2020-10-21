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
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(
    value = "/socket/{socketId}",
    encoders = MessageSentEventEncoder.class
)
public class WebsocketMessageEndpoint {

    @Inject
    private TopicDao topicDao;

    /*@Inject
    private UserSession userSession;*/

    private final Integer userId = 902;

    private Session session;
    private static Set<WebsocketMessageEndpoint> endpoints = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;

        endpoints.add(this);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        endpoints.remove(this);
    }

    public void onMessageSent(@ObservesAsync MessageSentEvent messageSentEvent) throws IOException, EncodeException {
        Topic topic = topicDao.findById(messageSentEvent.getTopicId()).get();

        Integer receiverId = messageSentEvent.getSenderId().equals(topic.getFirstUser().getId()) ? topic.getSecondUser().getId() : topic.getFirstUser().getId();

        endpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    if (endpoint.userId.equals(receiverId))
                        endpoint.session.getBasicRemote().sendObject(messageSentEvent);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
