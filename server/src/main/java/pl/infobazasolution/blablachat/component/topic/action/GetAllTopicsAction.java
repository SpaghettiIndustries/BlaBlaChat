package pl.infobazasolution.blablachat.component.topic.action;

import pl.infobazasolution.blablachat.component.topic.dto.TopicDto;
import pl.infobazasolution.blablachat.component.topic.service.GetTopicsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class GetAllTopicsAction {

    @Inject
    private GetTopicsService getTopicsService;

    public List<TopicDto> execute(String authorizationHeader) {
        return getTopicsService.get(authorizationHeader);
    }
}
