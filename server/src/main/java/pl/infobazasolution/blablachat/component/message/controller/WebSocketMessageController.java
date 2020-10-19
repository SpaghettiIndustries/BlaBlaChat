package pl.infobazasolution.blablachat.component.message.controller;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.common.util.ValidationUtils;
import pl.infobazasolution.blablachat.component.message.action.SendMessageAction;
import pl.infobazasolution.blablachat.component.message.decoder.NewMessageDecoder;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.NewMessage;
import pl.infobazasolution.blablachat.component.message.encoder.MessageDtoEncoder;
import pl.infobazasolution.blablachat.component.message.event.MessageSentEvent;
import pl.infobazasolution.blablachat.component.topic.dao.TopicDao;
import pl.infobazasolution.blablachat.component.topic.entity.Topic;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;

@ServerEndpoint(
    value="/message/socket/{user}",
    decoders = NewMessageDecoder.class,
    encoders = MessageDtoEncoder.class
)
public class WebSocketMessageController {

    // TODO: bierz użytkownika
    // TODO: tylko powiadomienie
    // TODO: użyj asynchronicznych eventów

    private Session session;
    private Integer userId;

    private static Map<String, Integer> peers = Collections.synchronizedMap(new HashMap<String, Integer>());
    private static HashMap<String, Integer> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("user") Integer userId) throws IOException {
        this.session = session;

        session.getUserProperties().put("userId", userId);

        /*chatEndpoints.add(this);*/
        users.put(session.getId(), userId);
    }

    public void onMessageSent(@Observes MessageSentEvent messageSentEvent) {

    }

    @OnMessage
    public void onMessage(Session session, NewMessage newMessage) throws IOException {

    }

    @OnClose
    public void onClose(Session session) throws IOException {
        chatEndpoints.remove(this);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {

    }

    private static void broadcastMessage(MessageDto messageDto) throws IOException, EncodeException {
        chatEndpoints.stream().
    }
}
