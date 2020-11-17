package pl.infobazasolution.blablachat.component.message.action;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.common.util.AuthenticationUtils;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.NewMessage;
import pl.infobazasolution.blablachat.component.message.service.SendMessageService;
import pl.infobazasolution.blablachat.component.message.validator.NewMessageValidator;
import pl.infobazasolution.blablachat.security.jwt.util.KeyGenerator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class SendMessageAction {

    @Inject
    private KeyGenerator keyGenerator;

    @Inject
    private NewMessageValidator newMessageValidator;

    @Inject
    private SendMessageService sendMessageService;

    public MessageDto execute(String authorizationHeader, NewMessage newMessage) throws ValidationException {
        Integer userId = Integer.parseInt(AuthenticationUtils.parseJwtClaimsFromHeader(keyGenerator, authorizationHeader).getSubject());

        if (newMessageValidator.validate(userId, newMessage))
            return sendMessageService.send(userId, newMessage);

        return null;
    }
}
