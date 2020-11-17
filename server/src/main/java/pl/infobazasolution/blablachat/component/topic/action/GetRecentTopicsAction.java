package pl.infobazasolution.blablachat.component.topic.action;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.common.util.AuthenticationUtils;
import pl.infobazasolution.blablachat.component.topic.dto.RecentTopicFilter;
import pl.infobazasolution.blablachat.component.topic.dto.TopicDto;
import pl.infobazasolution.blablachat.component.topic.service.GetTopicsService;
import pl.infobazasolution.blablachat.component.topic.validator.RecentTopicFilterValidator;
import pl.infobazasolution.blablachat.security.jwt.util.KeyGenerator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class GetRecentTopicsAction {

    @Inject
    private KeyGenerator keyGenerator;

    @Inject
    private RecentTopicFilterValidator recentTopicFilterValidator;

    @Inject
    private GetTopicsService getTopicsService;

    public List<TopicDto> execute(String authorizationHeader, RecentTopicFilter filter) throws ValidationException {
        Integer userId = Integer.parseInt(AuthenticationUtils.parseJwtClaimsFromHeader(keyGenerator, authorizationHeader).getSubject());

        if (recentTopicFilterValidator.validate(filter));
            return getTopicsService.get(userId, filter);
    }
}
