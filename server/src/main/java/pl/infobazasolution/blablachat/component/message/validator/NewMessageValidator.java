package pl.infobazasolution.blablachat.component.message.validator;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.common.util.ValidationUtils;
import pl.infobazasolution.blablachat.component.message.dto.NewMessage;
import pl.infobazasolution.blablachat.component.topic.dao.TopicDao;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;

import javax.inject.Inject;
import java.util.Objects;

public class NewMessageValidator {

    @Inject
    private UserDao userDao;

    @Inject
    private TopicDao topicDao;

    public Boolean validate(NewMessage newMessage) throws ValidationException {
        if ((!Objects.nonNull(newMessage.getSenderId()) || !Objects.nonNull(newMessage.getReceiverId())) && !Objects.nonNull(newMessage.getTopicId()))
            throw new ValidationException("Wiadomość musi mieć zdefiniowanego nadawcę i odbiorcę lub istniejący temat");

        if (Objects.nonNull(newMessage.getSenderId()) && Objects.nonNull(newMessage.getReceiverId())) {
            if (!ValidationUtils.userExists(newMessage.getSenderId(), userDao) || !ValidationUtils.userExists(newMessage.getReceiverId(), userDao))
                throw new ValidationException("Odbiorca i nadawca muszą być istniejącymi użytkownikami");
        } else if (Objects.nonNull(newMessage.getTopicId())) {
            if (!ValidationUtils.topicExists(newMessage.getTopicId(), topicDao))
                throw new ValidationException("Temat musi już istnieć");
        }

        if (!Objects.nonNull(newMessage.getContent()))
            throw new ValidationException("Wiadomość musi mieć treść");

        return true;
    }
}