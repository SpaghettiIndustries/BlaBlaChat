package pl.infobazasolution.blablachat.component.user.action;

import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.dto.UserFilter;
import pl.infobazasolution.blablachat.component.user.service.FindUserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class GetUserAction {

    @Inject
    private FindUserService findUserService;

    public UserDto execute(UserFilter filter){
        return findUserService.find(filter);
    }
}
