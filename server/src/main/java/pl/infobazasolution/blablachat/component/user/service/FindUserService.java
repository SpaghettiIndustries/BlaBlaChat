package pl.infobazasolution.blablachat.component.user.service;

import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.dto.UserFilter;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.inject.Inject;
import java.util.Optional;

public class FindUserService {

    @Inject
    private UserDao userDao;

    public UserDto find(UserFilter filter) {
        Optional<User> user = userDao.find(filter);

        if (user.isPresent()) {
            User userEntity = user.get();
            UserDto userDto = new UserDto();

            userDto.setId(userEntity.getId());
            userDto.setNick(userEntity.getNick());
            userDto.setEmail(userEntity.getEmail());
            userDto.setCreatedAt(userEntity.getCreatedAt());

            return userDto;
        }

        return null;
    }
}
