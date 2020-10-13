package pl.infobazasolution.blablachat.component.user.action;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.user.dto.NewUser;
import pl.infobazasolution.blablachat.component.user.dto.UpdateUser;
import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.entity.User;
import pl.infobazasolution.blablachat.component.user.service.UpdateUserService;
import pl.infobazasolution.blablachat.component.user.validator.NewUserValidator;
import pl.infobazasolution.blablachat.component.user.validator.UpdateUserValidator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@RequestScoped
public class UpdateUserAction {

    @Inject
    private UpdateUserValidator updateUserValidator;

    @Inject
    private UpdateUserService updateUserService;

    public UserDto execute(UpdateUser updateUser) throws ValidationException {
        if(updateUserValidator.validate(updateUser)){

            return updateUserService.update(updateUser);

        }

        return null;
    }
}
