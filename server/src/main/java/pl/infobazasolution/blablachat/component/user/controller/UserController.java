package pl.infobazasolution.blablachat.component.user.controller;

import pl.infobazasolution.blablachat.common.exception.AuthenticationException;
import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.user.action.*;
import pl.infobazasolution.blablachat.component.user.dto.*;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

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

    @Inject
    private UpdateUserAction updateUserAction;

    @Inject
    private DeleteUserAction deleteUserAction;

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
    public Response createUser(NewUser newUser) throws AuthenticationException, ValidationException {
        return createUserAction.execute(newUser, uriInfo);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginUser loginUser) throws AuthenticationException, ValidationException {
        return loginAction.execute(loginUser, uriInfo);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto update(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader, UpdateUser updateUser) throws ValidationException {
        return updateUserAction.execute(authorizationHeader, updateUser);
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean delete(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return deleteUserAction.execute(authorizationHeader);
    }

}
