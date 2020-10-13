package pl.infobazasolution.blablachat.component.message.validator;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.common.util.ValidationUtils;
import pl.infobazasolution.blablachat.component.message.dto.MessageFilter;
import pl.infobazasolution.blablachat.component.topic.dao.TopicDao;

import javax.inject.Inject;
import java.util.Objects;

public class MessageFilterValidator {

    @Inject
    private TopicDao topicDao;

    public Boolean validate(MessageFilter filter) throws ValidationException {
        if (!Objects.nonNull(filter.getTopicId()) || !Objects.nonNull(filter.getNumberOfMessages()))
            throw new ValidationException("Aby pobrać wiadomości, należy podać ID tematu oraz pożądaną liczbę");

        if (!ValidationUtils.topicExists(filter.getTopicId(), topicDao))
            throw new ValidationException("Temat musi już istnieć");

        return true;
    }
}
