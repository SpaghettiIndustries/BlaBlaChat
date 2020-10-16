package pl.infobazasolution.blablachat.component.message.controller;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.message.action.SendMessageAction;
import pl.infobazasolution.blablachat.component.message.decoder.NewMessageDecoder;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.NewMessage;
import pl.infobazasolution.blablachat.component.message.encoder.MessageDtoEncoder;
import pl.infobazasolution.blablachat.component.topic.dao.TopicDao;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;

@ServerEndpoint(
    value="/message/socket/{user}",
    decoders = NewMessageDecoder.class,
    encoders = MessageDtoEncoder.class
)
public class WebSocketMessageController {

    @Inject
    private SendMessageAction sendMessageAction;

    @Inject
    private TopicDao topicDao;

    private Session session;
    private Integer userId;

    /*private static Set<WebSocketMessageController> chatEndpoints = new CopyOnWriteArraySet<>();*/
    private static HashMap<String, Integer> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("user") Integer userId) throws IOException {
        this.session = session;
        this.userId = userId;

        session.getUserProperties().put("userId", userId);

        /*chatEndpoints.add(this);*/
        users.put(session.getId(), userId);
    }

    @OnMessage
    public void onMessage(Session session, NewMessage newMessage) throws IOException, ValidationException {
        MessageDto messageDto = sendMessageAction.execute(newMessage);
        Integer receiverId;

        if (messageDto.getTopicId())

        Predicate<Session> filterCriteria = (s) -> messageDto.get
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
