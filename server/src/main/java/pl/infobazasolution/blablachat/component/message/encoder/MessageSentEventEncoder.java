package pl.infobazasolution.blablachat.component.message.encoder;

import com.google.gson.Gson;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.entity.Message;
import pl.infobazasolution.blablachat.component.message.event.MessageSentEvent;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageSentEventEncoder implements Encoder.Text<MessageSentEvent> {

    private static Gson gson = new Gson();

    @Override
    public String encode(MessageSentEvent messageSentEvent) throws EncodeException {
        return gson.toJson(messageSentEvent);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
