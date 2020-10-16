package pl.infobazasolution.blablachat.component.message.encoder;

import com.google.gson.Gson;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageDtoEncoder implements Encoder.Text<MessageDto> {

    private static Gson gson = new Gson();

    @Override
    public String encode(MessageDto messageDto) throws EncodeException {
        return gson.toJson(messageDto);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
