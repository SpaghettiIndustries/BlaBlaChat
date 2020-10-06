package pl.infobazasolution.blablachat.component.user.dao;

import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class UserDao {

    @PersistenceContext(unitName = "blablachat")
    private EntityManager entityManager;

    public User createUser(User user) {
        return user;
    }

    /*public List<User> selectAllUsers() {
        return entityManager.find();
    }*/

    public Optional<User> selectUserById(Integer id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }
}
