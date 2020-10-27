package pl.infobazasolution.blablachat.component.user.action;

import pl.infobazasolution.blablachat.common.exception.AuthenticationException;
import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.user.dto.LoginUser;
import pl.infobazasolution.blablachat.component.user.dto.NewUser;
import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.service.CreateUserService;
import pl.infobazasolution.blablachat.component.user.service.LoginService;
import pl.infobazasolution.blablachat.component.user.validator.NewUserValidator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@RequestScoped
public class CreateUserAction {

    @Inject
    private NewUserValidator newUserValidator;

    @Inject
    private CreateUserService createUserService;

    @Inject
    private LoginService loginService;

    public Response execute(NewUser newUser, UriInfo uriInfo) throws ValidationException, AuthenticationException {
        if (newUserValidator.validate(newUser)) {
            UserDto newUserDto = createUserService.create(newUser);

            LoginUser loginUser = new LoginUser();
            loginUser.setNick(newUser.getNick());
            loginUser.setPassword(newUser.getPassword());

            String token = loginService.login(loginUser, uriInfo);

            return Response.ok(newUserDto).header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        }

        return null;
    }
}
