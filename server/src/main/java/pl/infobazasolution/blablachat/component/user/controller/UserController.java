package pl.infobazasolution.blablachat.component.user.controller;

import pl.infobazasolution.blablachat.component.user.action.GetUserAction;
import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.dto.UserFilter;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserController {

    @Inject
    private GetUserAction getUserAction;

    @POST
    @Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto getUser(UserFilter filter) {
        return getUserAction.execute(filter);
    }

}
