package pl.infobazasolution.blablachat.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T, E extends Filter> {

    @PersistenceContext(unitName = "blablachat")
    protected EntityManager entityManager;

    protected abstract Optional<T> create(T t);

    protected abstract List<T> readAll();

    protected abstract Optional<T> findById(Integer id);

    protected abstract Optional<T> find(E filter);

    protected abstract Optional<T> update(Integer id, T t);

    protected abstract Boolean delete(Integer id);

}
