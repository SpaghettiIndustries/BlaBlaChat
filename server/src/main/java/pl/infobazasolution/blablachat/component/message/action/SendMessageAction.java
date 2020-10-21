package pl.infobazasolution.blablachat.component.message.action;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.NewMessage;
import pl.infobazasolution.blablachat.component.message.service.SendMessageService;
import pl.infobazasolution.blablachat.component.message.validator.NewMessageValidator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class SendMessageAction {

    @Inject
    private NewMessageValidator newMessageValidator;

    @Inject
    private SendMessageService sendMessageService;

    public MessageDto execute(NewMessage newMessage) throws ValidationException {
        if (newMessageValidator.validate(newMessage))
            return sendMessageService.send(newMessage);

        return null;
    }
}
