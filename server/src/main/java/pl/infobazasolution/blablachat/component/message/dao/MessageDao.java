package pl.infobazasolution.blablachat.component.message.dao;

import pl.infobazasolution.blablachat.common.AbstractDao;
import pl.infobazasolution.blablachat.component.message.dto.MessageFilter;
import pl.infobazasolution.blablachat.component.message.entity.Message;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MessageDao extends AbstractDao<Message, MessageFilter> {

    @Transactional
    public Optional<Message> create(Message message) {
        try {
            entityManager.persist(message);

            return Optional.of(message);
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Message> readAll() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);

            Root<Message> messageRoot = criteriaQuery.from(Message.class);

            criteriaQuery.select(messageRoot);

            TypedQuery<Message> messageTypedQuery = entityManager.createQuery(criteriaQuery);

            return messageTypedQuery.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Optional<Message> findById(Integer id) {
        try {
            return Optional.ofNullable(entityManager.find(Message.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Message> find(MessageFilter filter) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);

            Root<Message> messageRoot = criteriaQuery.from(Message.class);

            criteriaQuery.select(messageRoot);

            if (Objects.nonNull(filter.getId())) {
                criteriaQuery.where(criteriaBuilder.equal(messageRoot.get("id"), filter.getId()));
            } else {
                return Optional.empty();
            }

            TypedQuery<Message> messageTypedQuery = entityManager.createQuery(criteriaQuery);

            return Optional.of(messageTypedQuery.getSingleResult());
        } catch(Exception e) {
            return Optional.empty();
        }
    }

    public List<Message> findRecent(MessageFilter filter) {
        return null;
    }

    @Transactional
    public Optional<Message> update(Integer id, Message messageToUpdate) {
        try {
            if (entityManager.find(Message.class, id) != null) {
                Message updatedMessage = entityManager.merge(messageToUpdate);

                return Optional.of(updatedMessage);
            }
        } catch (Exception e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    @Transactional
    public Boolean delete(Integer id) {
        try {
            Optional<Message> messageToDelete = findById(id);

            if (messageToDelete.isPresent()) {
                entityManager.remove(messageToDelete.get());

                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    /*protected List<Message> findAllUserMessages(MessageFilter messageFilter) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Message.class);

            Root<Message> messageRoot = criteriaQuery.from(Message.class);

            criteriaQuery.select(messageRoot);

            criteriaQuery.where(criteriaBuilder.equal(messageRoot.get("sender_id"),messageFilter.getSenderId()));

            TypedQuery<Message> messageTypedQuerry = entityManager.createQuery(criteriaQuery);
            return messageTypedQuerry.getResultList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }*/
    /*protected Optional<Message> findFew(MessageFilter filter,Integer number){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Message.class);

            Root<Message> messageRoot = criteriaQuery.from(Message.class);

            criteriaQuery.select(messageRoot);


        }catch (Exception e){

        }
    }*/
}
