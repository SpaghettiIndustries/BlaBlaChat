package pl.infobazasolution.blablachat.component.message.action;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.MessageFilter;
import pl.infobazasolution.blablachat.component.message.service.GetMessagesService;
import pl.infobazasolution.blablachat.component.message.validator.MessageFilterValidator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class GetMessagesAction {

    @Inject
    private MessageFilterValidator messageFilterValidator;

    @Inject
    private GetMessagesService getMessagesService;

    public List<MessageDto> execute(MessageFilter filter) throws ValidationException {
        if (messageFilterValidator.validate(filter))
            return getMessagesService.get(filter);

        return null;
    }
}
