package pl.infobazasolution.blablachat.component.user.action;

import pl.infobazasolution.blablachat.common.exception.AuthenticationException;
import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.user.dto.LoginUser;
import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.dto.UserFilter;
import pl.infobazasolution.blablachat.component.user.service.FindUserService;
import pl.infobazasolution.blablachat.component.user.service.LoginService;
import pl.infobazasolution.blablachat.component.user.validator.LoginValidator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.time.LocalDateTime;

@RequestScoped
public class LoginAction {

    @Inject
    private LoginValidator loginValidator;

    @Inject
    private LoginService loginService;

    @Inject
    private FindUserService findUserService;

    public Response execute(LoginUser loginUser, UriInfo uriInfo) throws AuthenticationException, ValidationException {
        if (loginValidator.validate(loginUser)) {
            String token = loginService.login(loginUser, uriInfo);
            NewCookie cookie = new NewCookie("token", token, "/", "localhost", 1, "", 604800, false);

            UserFilter filter = new UserFilter();
            filter.setNick(loginUser.getNick());
            UserDto userDto = findUserService.find(filter);

            return Response.ok(userDto).cookie(cookie).build();
        }

        return null;
    }
}
