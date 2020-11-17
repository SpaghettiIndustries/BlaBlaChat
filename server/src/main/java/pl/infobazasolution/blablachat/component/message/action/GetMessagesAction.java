package pl.infobazasolution.blablachat.component.message.action;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.common.util.AuthenticationUtils;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.MessageFilter;
import pl.infobazasolution.blablachat.component.message.service.GetMessagesService;
import pl.infobazasolution.blablachat.component.message.validator.MessageFilterValidator;
import pl.infobazasolution.blablachat.security.jwt.util.KeyGenerator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class GetMessagesAction {

    @Inject
    private KeyGenerator keyGenerator;

    @Inject
    private MessageFilterValidator messageFilterValidator;

    @Inject
    private GetMessagesService getMessagesService;

    public List<MessageDto> execute(String authorizationHeader, MessageFilter filter) throws ValidationException {
        Integer userId = Integer.parseInt(AuthenticationUtils.parseJwtClaimsFromHeader(keyGenerator, authorizationHeader).getSubject());

        if (messageFilterValidator.validate(userId, filter))
            return getMessagesService.get(userId, filter);

        return null;
    }
}
