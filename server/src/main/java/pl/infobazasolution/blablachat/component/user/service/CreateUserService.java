package pl.infobazasolution.blablachat.component.user.service;

import pl.infobazasolution.blablachat.common.util.PasswordUtils;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.dto.NewUser;
import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Optional;

public class CreateUserService {

    @Inject
    private UserDao userDao;

    public UserDto create(NewUser newUser) {
        User newUserEntity = new User();

        newUserEntity.setNick(newUser.getNick().toLowerCase());
        newUserEntity.setEmail(newUser.getEmail().toLowerCase());
        newUserEntity.setPassword(PasswordUtils.digestPassword(newUser.getPassword()));
        newUserEntity.setCreatedAt(ZonedDateTime.now());

        Optional<User> user = userDao.create(newUserEntity);

        if (user.isPresent()) {
            User createdUserEntity = user.get();
            UserDto createdUserDto = new UserDto();

            createdUserDto.setId(createdUserEntity.getId());
            createdUserDto.setNick(createdUserEntity.getNick());
            createdUserDto.setEmail(createdUserEntity.getEmail());
            createdUserDto.setCreatedAt(createdUserEntity.getCreatedAt());

            return createdUserDto;
        }

        return null;
    }
}
