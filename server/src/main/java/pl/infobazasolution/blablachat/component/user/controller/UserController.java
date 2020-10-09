package pl.infobazasolution.blablachat.component.user.controller;

import pl.infobazasolution.blablachat.common.exception.AuthenticationException;
import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.user.action.CreateUserAction;
import pl.infobazasolution.blablachat.component.user.action.GetUserAction;
import pl.infobazasolution.blablachat.component.user.action.LoginAction;
import pl.infobazasolution.blablachat.component.user.dto.LoginUser;
import pl.infobazasolution.blablachat.component.user.dto.NewUser;
import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.dto.UserFilter;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/user")
public class UserController {

    @Context
    private UriInfo uriInfo;

    @Inject
    private GetUserAction getUserAction;

    @Inject
    private CreateUserAction createUserAction;

    @Inject
    private LoginAction loginAction;

    @POST
    @Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto getUser(UserFilter filter) {
        return getUserAction.execute(filter);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto createUser(NewUser newUser) throws ValidationException {
        return createUserAction.execute(newUser);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(LoginUser loginUser) throws AuthenticationException {
        return loginAction.execute(loginUser, uriInfo);
    }
}
