package pl.infobazasolution.blablachat.component.topic.action;

import pl.infobazasolution.blablachat.common.util.AuthenticationUtils;
import pl.infobazasolution.blablachat.component.topic.dto.TopicDto;
import pl.infobazasolution.blablachat.component.topic.service.GetTopicsService;
import pl.infobazasolution.blablachat.security.jwt.util.KeyGenerator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class GetAllTopicsAction {

    @Inject
    private KeyGenerator keyGenerator;

    @Inject
    private GetTopicsService getTopicsService;

    public List<TopicDto> execute(String authorizationHeader) {
        Integer userId = Integer.parseInt(AuthenticationUtils.parseJwtClaimsFromHeader(keyGenerator, authorizationHeader).getSubject());

        return getTopicsService.get(userId);
    }
}
