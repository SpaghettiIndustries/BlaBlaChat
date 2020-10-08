package pl.infobazasolution.blablachat.component.user.validator;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.common.util.ValidationUtils;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.dto.NewUser;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

public class NewUserValidator {

    @Inject
    private UserDao userDao;

    public Boolean validate(NewUser newUser) throws ValidationException {
        List<User> existingUsers = userDao.readAll();

        if (newUser.getNick().trim().isEmpty())
            throw new ValidationException("Nick nie może być pusty");

        if (ValidationUtils.containsWhitespace(newUser.getNick()))
            throw new ValidationException("Nick nie może mieć spacji przed lub po");

        if (newUser.getNick().length() < 5)
            throw new ValidationException("Nick nie może mieć mniej niż 5 znaków");

        if (newUser.getNick().length() > 25)
            throw new ValidationException("Nick nie może mieć więcej niż 25 znaków");

        if (Objects.nonNull(newUser.getEmail()) && !ValidationUtils.isValidEmail(newUser.getEmail()))
            throw new ValidationException("E-mail nie jest prawidłowy");

        if (newUser.getPassword().trim().isEmpty())
            throw new ValidationException("Hasło nie może być puste");

        if (ValidationUtils.containsWhitespace(newUser.getPassword()))
            throw new ValidationException("Hasło nie może zawierać spacji");

        for (User existingUser : existingUsers) {
            if (existingUser.getNick().equals(newUser.getNick()))
                throw new ValidationException("Podany nick już istnieje");
        }

        return true;
    }
}
