package pl.infobazasolution.blablachat.component.message.decoder;

import com.google.gson.Gson;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.NewMessage;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class NewMessageDecoder implements Decoder.Text<NewMessage> {

    private static Gson gson = new Gson();

    @Override
    public NewMessage decode(String s) throws DecodeException {
        return gson.fromJson(s, NewMessage.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
