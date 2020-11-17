package pl.infobazasolution.blablachat.component.message.controller;

import pl.infobazasolution.blablachat.common.websocket.ServletAwareConfigurator;
import pl.infobazasolution.blablachat.component.message.encoder.MessageSentEventEncoder;
import pl.infobazasolution.blablachat.component.message.event.MessageSentEvent;
import pl.infobazasolution.blablachat.component.topic.dao.TopicDao;
import pl.infobazasolution.blablachat.component.topic.entity.Topic;

import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(
    value = "/socket/{socketId}",
    configurator = ServletAwareConfigurator.class,
    encoders = MessageSentEventEncoder.class
)
public class WebsocketMessageEndpoint {

    @Inject
    private TopicDao topicDao;

    private Session session;
    private Integer userId;

    private static Set<WebsocketMessageEndpoint> endpoints = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        this.session = session;
        this.userId = (Integer) config.getUserProperties().get("userId");

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
