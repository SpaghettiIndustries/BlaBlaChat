package pl.infobazasolution.blablachat.component.user.service;

import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.dto.DeleteUser;
import pl.infobazasolution.blablachat.component.user.dto.UserFilter;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.inject.Inject;
import javax.validation.constraints.Null;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

public class DeleteUserService {

    @Inject
    private UserDao userDao;

    public Boolean delete(Integer userId) {
        Optional<User> userToDeleteEntityProbably = userDao.findById(userId);
        User userToDeleteEntity = userToDeleteEntityProbably.get();

        userToDeleteEntity.setEmail(null);
        userToDeleteEntity.setNick("Usunięto");
        userToDeleteEntity.setDeletedAt(ZonedDateTime.now());

        Optional<User> deletedUser = userDao.update(userToDeleteEntity.getId(), userToDeleteEntity);

        return deletedUser.isPresent();
    }
}
