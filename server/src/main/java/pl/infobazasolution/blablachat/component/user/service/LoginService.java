package pl.infobazasolution.blablachat.component.user.service;

import pl.infobazasolution.blablachat.common.util.PasswordUtils;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.dto.LoginUser;
import pl.infobazasolution.blablachat.component.user.dto.UserFilter;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.inject.Inject;
import java.util.Optional;

public class LoginService {

    @Inject
    private UserDao userDao;

    public String login(LoginUser loginUser){

        UserFilter filter = new UserFilter();
        filter.setNick(loginUser.getNick());
        Optional<User> user = userDao.find(filter);

        if(user.isPresent()){
            User userEntity = user.get();
            loginUser.setPassword(PasswordUtils.digestPassword(loginUser.getPassword()));
            if(loginUser.getPassword().equals(userEntity.getPassword())){
                return "Zalogowano do konta " + loginUser.getNick();
            }
            return "Podane hasło jest nieprawidłowe";
        }
        return "Nie ma takiego użytkownika";
    }
}
