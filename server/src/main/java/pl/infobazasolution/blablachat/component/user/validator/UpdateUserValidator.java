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

    public Boolean validate(UpdateUser updateUser) throws ValidationException {

        List<User> existingUsers = userDao.readAll();
        /*Boolean foundId = false;

        for (User existingUser : existingUsers) {

            if (existingUser.getId().equals(updateUser.getId()))
                foundId = true;

        }
        if (foundId) {*/
            if (Objects.nonNull(updateUser.getNick())
                    || Objects.nonNull(updateUser.getPassword())
                    || Objects.nonNull(updateUser.getEmail())) {

                if (Objects.nonNull(updateUser.getNick())) {

                    if (!(updateUser.getNick().trim().isEmpty())) {

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

                    } else {
                        throw new ValidationException("Nick nie może być pusty!");
                    }
                }
                if (Objects.nonNull(updateUser.getEmail())) {
                    if (!(updateUser.getEmail().trim().isEmpty())) {

                        if (!Objects.nonNull(updateUser.getEmail()) && !ValidationUtils.isValidEmail(updateUser.getEmail()))
                            throw new ValidationException("E-mail nie jest prawidłowy");


                    } else {
                        throw new ValidationException("Email nie może być pusty!");
                    }

                }
                if (Objects.nonNull(updateUser.getPassword())) {
                    if (!(updateUser.getPassword().trim().isEmpty())) {

                        if (ValidationUtils.containsWhitespace(updateUser.getPassword()))
                            throw new ValidationException("Hasło nie może zawierać spacji");

                    } else {
                        throw new ValidationException("Hasło nie może być puste!");
                    }
                }

                return true;

            }
            throw new ValidationException("Nie podano żadnych wartości");
        }
        /*throw new ValidationException("Nie znaleziono użytkownika o podanym id");
    }*/
}
