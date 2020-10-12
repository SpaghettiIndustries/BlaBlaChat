package pl.infobazasolution.blablachat.component.user.service;

import pl.infobazasolution.blablachat.common.util.PasswordUtils;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.dto.NewUser;
import pl.infobazasolution.blablachat.component.user.dto.UpdateUser;
import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Optional;

public class UpdateUserService {

    @Inject
    private UserDao userDao;

    public UserDto update(UpdateUser updateUser) {
    try{
        User newUserEntity = new User();

        Optional<User> optional = userDao.findById(updateUser.getId());
        User user = optional.get();

        if(updateUser.getNick().trim().isEmpty())
            updateUser.setNick(user.getNick());
        if(updateUser.getEmail().trim().isEmpty())
            updateUser.setEmail(user.getEmail());
        if(updateUser.getPassword().trim().isEmpty())
            updateUser.setPassword(user.getPassword());

        newUserEntity.setNick(updateUser.getNick());
        newUserEntity.setEmail(updateUser.getEmail());
        newUserEntity.setPassword(updateUser.getPassword());

        newUserEntity.setUpdatedAt(ZonedDateTime.now());
        newUserEntity.setCreatedAt(user.getCreatedAt());

        newUserEntity.setId(user.getId());
        userDao.update(updateUser.getId(),newUserEntity);

        UserDto userDto = new UserDto();

        userDto.setId(newUserEntity.getId());
        userDto.setNick(updateUser.getNick());
        userDto.setEmail(updateUser.getEmail());

        return userDto;
    } catch (Exception e) {
        return null;
    }
    }
}
