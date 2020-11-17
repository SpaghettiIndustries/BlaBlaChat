package pl.infobazasolution.blablachat.component.user.action;

import pl.infobazasolution.blablachat.common.util.AuthenticationUtils;
import pl.infobazasolution.blablachat.component.user.dto.DeleteUser;
import pl.infobazasolution.blablachat.component.user.service.DeleteUserService;
import pl.infobazasolution.blablachat.security.jwt.util.KeyGenerator;

import javax.inject.Inject;

public class DeleteUserAction {

    @Inject
    private KeyGenerator keyGenerator;

    @Inject
    private DeleteUserService deleteUserService;

    public Boolean execute(String authorizationHeader) {
        Integer userId = Integer.parseInt(AuthenticationUtils.parseJwtClaimsFromHeader(keyGenerator, authorizationHeader).getSubject());

        return deleteUserService.delete(userId);
    }
}
