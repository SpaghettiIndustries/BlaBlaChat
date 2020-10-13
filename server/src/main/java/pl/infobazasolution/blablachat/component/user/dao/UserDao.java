package pl.infobazasolution.blablachat.component.user.dao;

import pl.infobazasolution.blablachat.common.AbstractDao;
import pl.infobazasolution.blablachat.component.user.dto.UserFilter;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDao extends AbstractDao<User, UserFilter> {

    @Transactional
    public Optional<User> create(User user) {
        try {
            entityManager.persist(user);

            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<User> readAll() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

            Root<User> userRoot = criteriaQuery.from(User.class);

            criteriaQuery.select(userRoot);

            TypedQuery<User> userTypedQuery = entityManager.createQuery(criteriaQuery);

            return userTypedQuery.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Optional<User> findById(Integer id) {
        try {
            return Optional.ofNullable(entityManager.find(User.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> find(UserFilter filter) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

            Root<User> userRoot = criteriaQuery.from(User.class);

            criteriaQuery.select(userRoot);

            if (Objects.nonNull(filter.getId())) {
                criteriaQuery.where(criteriaBuilder.equal(userRoot.get("id"), filter.getId()));
            } else if (Objects.nonNull(filter.getNick())) {
                criteriaQuery.where(criteriaBuilder.equal(userRoot.get("nick"), filter.getNick()));
            }

            TypedQuery<User> userTypedQuery = entityManager.createQuery(criteriaQuery);

            return Optional.of(userTypedQuery.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<User> update(Integer id, User userToUpdate) {
        try {
            if (entityManager.find(User.class, id) != null) {
                User updatedUser = entityManager.merge(userToUpdate);

                return Optional.of(updatedUser);
            }
        } catch (Exception e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    @Transactional
    public Boolean delete(Integer id) {
        try {
            Optional<User> userToDelete = findById(id);

            if (userToDelete.isPresent()) {
                entityManager.remove(userToDelete.get());

                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}
