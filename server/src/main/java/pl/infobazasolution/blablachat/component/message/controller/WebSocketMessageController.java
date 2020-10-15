package pl.infobazasolution.blablachat.component.message.controller;

import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.entity.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value="/message/socket/{sender}")
public class WebSocketMessageController {

    private Session session;


    @OnOpen
    public void onOpen(Session session) throws IOException {

    }

    @OnMessage
    public void onMessage(Session session, MessageDto messageDto) throws IOException {

    }

    @OnClose
    public void onClose(Session session) throws IOException {

    }

    @OnError
    public void onError(Session session, Throwable throwable) {

    }
}
