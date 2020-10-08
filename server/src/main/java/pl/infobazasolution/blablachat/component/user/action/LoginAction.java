package pl.infobazasolution.blablachat.component.user.action;

import pl.infobazasolution.blablachat.common.exception.AuthenticationException;
import pl.infobazasolution.blablachat.component.user.dto.LoginUser;
import pl.infobazasolution.blablachat.component.user.service.LoginService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class LoginAction {

    @Inject
    private LoginService loginService;

    public String execute(LoginUser loginUser) throws AuthenticationException {
        return loginService.login(loginUser);
    }
}
