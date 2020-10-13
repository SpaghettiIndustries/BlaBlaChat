package pl.infobazasolution.blablachat.component.message.controller;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.NewMessage;
import pl.infobazasolution.blablachat.component.message.action.SendMessageAction;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/message")
public class MessageController {

    @Inject
    private SendMessageAction sendMessageAction;

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MessageDto sendMessage(NewMessage newMessage) throws ValidationException {
        return sendMessageAction.execute(newMessage);
    }
}
