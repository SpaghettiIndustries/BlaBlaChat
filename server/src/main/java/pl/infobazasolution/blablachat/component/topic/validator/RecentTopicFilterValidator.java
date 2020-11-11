package pl.infobazasolution.blablachat.component.topic.validator;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.topic.dto.RecentTopicFilter;

import java.util.Objects;

public class RecentTopicFilterValidator {

    public Boolean validate(RecentTopicFilter filter) throws ValidationException {
        if (!Objects.nonNull(filter.getNumberOfTopics()))
            throw new ValidationException("Należy podać liczbę konwersacji, które chcesz pobrać");

        if (filter.getNumberOfTopics() < 0)
            throw new ValidationException("Liczba konwersacji, które chcesz pobrać, nie może być ujemna");

        if (Objects.nonNull(filter.getStartIndex()) && filter.getStartIndex() < 0)
            throw new ValidationException("Indeks początkowy nie może być ujemny");

        return true;
    }
}
