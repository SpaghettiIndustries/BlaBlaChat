package pl.infobazasolution.blablachat.component.user.service;

import pl.infobazasolution.blablachat.common.util.PasswordUtils;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.dto.NewUser;
import pl.infobazasolution.blablachat.component.user.dto.UpdateUser;
import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

public class UpdateUserService {

    @Inject
    private UserDao userDao;

    public UserDto update(UpdateUser updateUser) {
        try {
            User updateUserEntity = new User();

            Optional<User> optional = userDao.findById(updateUser.getId());
            User user = optional.get();

            if (!Objects.nonNull(updateUser.getNick()))
                updateUser.setNick(user.getNick());

            if (!Objects.nonNull(updateUser.getEmail()))
                updateUser.setEmail(user.getEmail());

            if (!Objects.nonNull(updateUser.getPassword()))
                updateUser.setPassword(user.getPassword());

            updateUserEntity.setNick(updateUser.getNick());
            updateUserEntity.setEmail(updateUser.getEmail());
            updateUserEntity.setPassword(PasswordUtils.digestPassword(updateUser.getPassword()));

            updateUserEntity.setUpdatedAt(ZonedDateTime.now());
            updateUserEntity.setCreatedAt(user.getCreatedAt());

            updateUserEntity.setId(user.getId());

            userDao.update(updateUser.getId(),updateUserEntity);

            UserDto userDto = new UserDto();

            userDto.setId(updateUserEntity.getId());
            userDto.setNick(updateUser.getNick());
            userDto.setEmail(updateUser.getEmail());

            return userDto;
        } catch (Exception e) {
            return null;
        }
    }
}
