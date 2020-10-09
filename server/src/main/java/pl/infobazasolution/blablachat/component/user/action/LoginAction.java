package pl.infobazasolution.blablachat.component.user.action;

import pl.infobazasolution.blablachat.common.exception.AuthenticationException;
import pl.infobazasolution.blablachat.component.user.dto.LoginUser;
import pl.infobazasolution.blablachat.component.user.service.LoginService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@RequestScoped
public class LoginAction {

    @Inject
    private LoginService loginService;

    public Response execute(LoginUser loginUser, UriInfo uriInfo) throws AuthenticationException {
        String token = loginService.login(loginUser, uriInfo);

        NewCookie cookie = new NewCookie("token", token);

        return Response.ok("OK").cookie(cookie).build();
    }
}
