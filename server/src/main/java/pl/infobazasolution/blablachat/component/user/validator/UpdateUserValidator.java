package pl.infobazasolution.blablachat.component.user.validator;

import pl.infobazasolution.blablachat.common.exception.ValidationException;
import pl.infobazasolution.blablachat.common.util.ValidationUtils;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.dto.UpdateUser;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

public class UpdateUserValidator {

    @Inject
    private UserDao userDao;

    public Boolean validate(UpdateUser updateUser) throws ValidationException{

        List<User> existingUsers = userDao.readAll();

        if (!(updateUser.getNick().trim().isEmpty())
                && !(updateUser.getPassword().trim().isEmpty())
                && !(updateUser.getEmail().trim().isEmpty())) {

            if (!(updateUser.getNick().trim().isEmpty())){

                if (ValidationUtils.containsWhitespace(updateUser.getNick()))
                    throw new ValidationException("Nick nie może mieć spacji przed lub po");

                if (updateUser.getNick().length() < 5)
                    throw new ValidationException("Nick nie może mieć mniej niż 5 znaków");

                if (updateUser.getNick().length() > 25)
                    throw new ValidationException("Nick nie może mieć więcej niż 25 znaków");

                for (User existingUser : existingUsers) {
                    if (existingUser.getNick().equals(updateUser.getNick()))
                        throw new ValidationException("Podany nick już istnieje");
                }

            }

            if (!(updateUser.getEmail().trim().isEmpty())){

                if (!Objects.nonNull(updateUser.getEmail())) //&& !ValidationUtils.isValidEmail(updateUser.getEmail()))
                    throw new ValidationException("E-mail nie jest prawidłowy");

            }

            if (!(updateUser.getPassword().trim().isEmpty())) {

                if (ValidationUtils.containsWhitespace(updateUser.getPassword()))
                    throw new ValidationException("Hasło nie może zawierać spacji");

            }
            return true;
        }
        throw new ValidationException("Nie podano żadnych wartości");
    }

}
