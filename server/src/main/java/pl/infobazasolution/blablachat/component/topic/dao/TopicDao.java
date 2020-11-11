package pl.infobazasolution.blablachat.component.topic.dao;

import pl.infobazasolution.blablachat.common.AbstractDao;
import pl.infobazasolution.blablachat.component.topic.dto.RecentTopicFilter;
import pl.infobazasolution.blablachat.component.topic.dto.TopicFilter;
import pl.infobazasolution.blablachat.component.topic.entity.Topic;
import pl.infobazasolution.blablachat.component.user.session.UserSession;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TopicDao extends AbstractDao<Topic, TopicFilter> {

    @Transactional
    public Optional<Topic> create(Topic topic) {
        try {
            entityManager.persist(topic);

            return Optional.of(topic);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Topic> readAll() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Topic> criteriaQuery = criteriaBuilder.createQuery(Topic.class);

            Root<Topic> topicRoot = criteriaQuery.from(Topic.class);

            criteriaQuery.select(topicRoot);

            TypedQuery<Topic> topicTypedQuery = entityManager.createQuery(criteriaQuery);

            return topicTypedQuery.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Optional<Topic> findById(Integer id) {
        try {
            return Optional.ofNullable(entityManager.find(Topic.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Topic> find(TopicFilter filter) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Topic> criteriaQuery = criteriaBuilder.createQuery(Topic.class);

            Root<Topic> topicRoot = criteriaQuery.from(Topic.class);

            criteriaQuery.select(topicRoot);

            if (Objects.nonNull(filter.getId())) {
                criteriaQuery.where(criteriaBuilder.equal(topicRoot.get("id"), filter.getId()));
            } else if (Objects.nonNull(filter.getFirstUserId()) && Objects.nonNull(filter.getSecondUserId())) {
                criteriaQuery.where(criteriaBuilder.and(
                    criteriaBuilder.equal(topicRoot.get("firstUser"), filter.getFirstUserId()),
                    criteriaBuilder.equal(topicRoot.get("secondUser"), filter.getSecondUserId())
                ));
            }

            TypedQuery<Topic> topicTypedQuery = entityManager.createQuery(criteriaQuery);

            if (topicTypedQuery.getResultList().isEmpty()) {
                criteriaQuery.where(criteriaBuilder.and(
                        criteriaBuilder.equal(topicRoot.get("firstUser"), filter.getSecondUserId()),
                        criteriaBuilder.equal(topicRoot.get("secondUser"), filter.getFirstUserId())
                ));

                topicTypedQuery = entityManager.createQuery(criteriaQuery);
            }

            return Optional.of(topicTypedQuery.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Topic> findAllUserParticipatesIn(TopicFilter filter) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Topic> criteriaQuery = criteriaBuilder.createQuery(Topic.class);

            Root<Topic> topicRoot = criteriaQuery.from(Topic.class);

            criteriaQuery.select(topicRoot);

            if (Objects.nonNull(filter.getFirstUserId())) {
                Integer userId = filter.getFirstUserId();

                criteriaQuery.where(criteriaBuilder.or(
                        criteriaBuilder.equal(topicRoot.get("first_user_id"), userId),
                        criteriaBuilder.equal(topicRoot.get("second_user_id"), userId)
                ));
            }

            TypedQuery<Topic> topicTypedQuery = entityManager.createQuery(criteriaQuery);

            return topicTypedQuery.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<Topic> findAllRecentUserParticipatesIn(Integer userId, RecentTopicFilter filter) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Topic> criteriaQuery = criteriaBuilder.createQuery(Topic.class);

            Root<Topic> topicRoot = criteriaQuery.from(Topic.class);

            criteriaQuery.select(topicRoot);

            if (Objects.nonNull(userId)) {
                if (Objects.nonNull(filter.getStartIndex())) {
                    criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.or(
                        criteriaBuilder.equal(topicRoot.get("first_user_id"), userId),
                        criteriaBuilder.equal(topicRoot.get("second_user_id"), userId)
                    ), criteriaBuilder.lt(topicRoot.get("id"), filter.getStartIndex())));
                } else {
                    criteriaQuery.where(criteriaBuilder.or(
                        criteriaBuilder.equal(topicRoot.get("first_user_id"), userId),
                        criteriaBuilder.equal(topicRoot.get("second_user_id"), userId)
                    ));
                }
            } else {
                return Collections.emptyList();
            }

            TypedQuery<Topic> topicTypedQuery = entityManager.createQuery(criteriaQuery).setMaxResults(filter.getNumberOfTopics());

            return topicTypedQuery.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Transactional
    public Optional<Topic> update(Integer id, Topic topicToUpdate) {
        try {
            if (entityManager.find(Topic.class, id) != null) {
                Topic updatedTopic = entityManager.merge(topicToUpdate);

                return Optional.of(updatedTopic);
            }
        } catch (Exception e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    @Transactional
    public Boolean delete(Integer id) {
        try {
            Optional<Topic> topicToDelete = findById(id);

            if (topicToDelete.isPresent()) {
                entityManager.remove(topicToDelete.get());

                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}
