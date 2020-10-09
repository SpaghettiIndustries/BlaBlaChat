package pl.infobazasolution.blablachat.component.user.service;

import pl.infobazasolution.blablachat.common.exception.AuthenticationException;
import pl.infobazasolution.blablachat.common.util.PasswordUtils;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.dto.LoginUser;
import pl.infobazasolution.blablachat.component.user.dto.UserFilter;
import pl.infobazasolution.blablachat.component.user.entity.User;
import pl.infobazasolution.blablachat.security.jwt.util.UserTokenIssuer;

import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.time.LocalDateTime;
import java.util.Optional;

public class LoginService {

    @Inject
    private UserDao userDao;

    @Inject
    private UserTokenIssuer userTokenIssuer;

    public String login(LoginUser loginUser, UriInfo uriInfo) throws AuthenticationException {

        UserFilter filter = new UserFilter();
        filter.setNick(loginUser.getNick());

        Optional<User> user = userDao.find(filter);

        if (user.isPresent()) {
            User userEntity = user.get();

            loginUser.setPassword(PasswordUtils.digestPassword(loginUser.getPassword()));

            if (loginUser.getPassword().equals(userEntity.getPassword())) {
                return userTokenIssuer.issueToken(userEntity.getNick(), LocalDateTime.now().plusDays(7L), uriInfo);
            }

            throw new AuthenticationException("Podane hasło jest nieprawidłowe");
        }

        throw new AuthenticationException("Nie ma takiego użytkownika");
    }
}
