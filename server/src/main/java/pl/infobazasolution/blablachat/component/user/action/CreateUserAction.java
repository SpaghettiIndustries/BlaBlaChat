package pl.infobazasolution.blablachat.component.user.action;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.user.dto.NewUser;
import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.service.CreateUserService;
import pl.infobazasolution.blablachat.component.user.validator.NewUserValidator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CreateUserAction {

    @Inject
    private NewUserValidator newUserValidator;

    @Inject
    private CreateUserService createUserService;

    public UserDto execute(NewUser newUser) throws ValidationException {
        if (newUserValidator.validate(newUser))
            return createUserService.create(newUser);

        return null;
    }
}
