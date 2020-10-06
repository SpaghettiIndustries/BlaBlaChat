package pl.infobazasolution.blablachat.component.user.dao;

import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class UserDao {

    @PersistenceContext(unitName = "blablachat")
    private EntityManager entityManager;

    public User createUser(User user) {
        return user;
    }

    public List<User> selectAllUsers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public Optional<User> selectUserById(Integer id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }
}
