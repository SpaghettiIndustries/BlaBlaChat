package pl.infobazasolution.blablachat.component.user.action;

import pl.infobazasolution.blablachat.component.user.dto.DeleteUser;
import pl.infobazasolution.blablachat.component.user.service.DeleteUserService;

import javax.inject.Inject;

public class DeleteUserAction {

    @Inject
    private DeleteUserService deleteUserService;

    public boolean execute(DeleteUser deleteUser){
        return deleteUserService.delete(deleteUser);
    }
}
