package pl.infobazasolution.blablachat.component.topic.service;

import pl.infobazasolution.blablachat.component.topic.dao.TopicDao;
import pl.infobazasolution.blablachat.component.topic.dto.NewTopic;
import pl.infobazasolution.blablachat.component.topic.dto.TopicDto;
import pl.infobazasolution.blablachat.component.topic.entity.Topic;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Optional;

public class CreateTopicService {

    @Inject
    private TopicDao topicDao;

    @Inject
    private UserDao userDao;

    public TopicDto create(NewTopic newTopic) {
        Topic newTopicEntity = new Topic();

        newTopicEntity.setFirstUser(userDao.findById(newTopic.getFirstUserId()).get());
        newTopicEntity.setSecondUser(userDao.findById(newTopic.getSecondUserId()).get());
        newTopicEntity.setCreatedAt(ZonedDateTime.now());

        Optional<Topic> topic = topicDao.create(newTopicEntity);

        if (topic.isPresent()) {
            Topic createdTopicEntity = topic.get();
            TopicDto createdTopicDto = new TopicDto();

            createdTopicDto.setId(createdTopicEntity.getId());
            createdTopicDto.setFirstUserId(createdTopicEntity.getFirstUser().getId());
            createdTopicDto.setSecondUserId(createdTopicEntity.getSecondUser().getId());
            createdTopicDto.setCreatedAt(createdTopicEntity.getCreatedAt());

            return createdTopicDto;
        }

        return null;
    }
}
