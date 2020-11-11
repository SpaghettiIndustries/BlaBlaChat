package pl.infobazasolution.blablachat.component.topic.controller;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.topic.action.GetAllTopicsAction;
import pl.infobazasolution.blablachat.component.topic.action.GetRecentTopicsAction;
import pl.infobazasolution.blablachat.component.topic.dto.RecentTopicFilter;
import pl.infobazasolution.blablachat.component.topic.dto.TopicDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/topic")
public class TopicController {

    @Inject
    private GetAllTopicsAction getAllTopicsAction;

    @Inject
    private GetRecentTopicsAction getRecentTopicsAction;

    @POST
    @Path("/get_all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TopicDto> getAllTopics() {
        return getAllTopicsAction.execute();
    }

    @POST
    @Path("/get_recent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TopicDto> getRecentTopics(RecentTopicFilter filter) throws ValidationException {
        return getRecentTopicsAction.execute(filter);
    }


}
