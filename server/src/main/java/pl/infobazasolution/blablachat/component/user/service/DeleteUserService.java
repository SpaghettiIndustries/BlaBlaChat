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

    public Boolean delete(DeleteUser deleteUser){

        if (Objects.nonNull(deleteUser.getId()) || Objects.nonNull(deleteUser.getNick())) {

            UserFilter filter = new UserFilter();
            User deleteUserEntity = new User();

            if(Objects.nonNull(deleteUser.getId()))
                filter.setId(deleteUser.getId());

            if(Objects.nonNull(deleteUser.getNick()))
                filter.setNick(deleteUser.getNick());

            Optional<User> optional = userDao.find(filter);
            deleteUserEntity = optional.get();

            deleteUserEntity.setEmail(null);
            deleteUserEntity.setNick("Usunięto");
            deleteUserEntity.setDeletedAt(ZonedDateTime.now());

            userDao.update(deleteUserEntity.getId(),deleteUserEntity);

            return true;
        }
        return false;
    }
}
