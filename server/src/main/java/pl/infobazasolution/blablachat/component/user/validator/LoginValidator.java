package pl.infobazasolution.blablachat.component.user.validator;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.dto.LoginUser;
import pl.infobazasolution.blablachat.component.user.dto.UserFilter;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

public class LoginValidator {

    @Inject
    private UserDao userDao;

    public Boolean validate(LoginUser loginUser) throws ValidationException {

        UserFilter filter = new UserFilter();
        filter.setNick(loginUser.getNick());

        Optional<User> optional = userDao.find(filter);
        User user = optional.get();
        if (Objects.nonNull(user.getNick()) && Objects.nonNull(user.getPassword())) {

            if (Objects.nonNull(user.getDeletedAt())) {
                throw new ValidationException("Użytkownika usunięto");
            }

        }else{
            throw new ValidationException("Nie uzupełniono niezbędnych danych");
        }
        return true;
    }
}
