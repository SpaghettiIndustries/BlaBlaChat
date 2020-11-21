package pl.infobazasolution.blablachat.component.topic.service;

import pl.infobazasolution.blablachat.component.topic.dao.TopicDao;
import pl.infobazasolution.blablachat.component.topic.dto.NewTopic;
import pl.infobazasolution.blablachat.component.topic.dto.TopicDto;
import pl.infobazasolution.blablachat.component.topic.entity.Topic;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.dto.UserDto;
import pl.infobazasolution.blablachat.component.user.entity.User;

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

        User firstUserEntity = userDao.findById(newTopic.getFirstUserId()).get();
        User secondUserEntity = userDao.findById(newTopic.getSecondUserId()).get();

        newTopicEntity.setFirstUser(firstUserEntity);
        newTopicEntity.setSecondUser(secondUserEntity);
        newTopicEntity.setCreatedAt(ZonedDateTime.now());

        Optional<Topic> topic = topicDao.create(newTopicEntity);

        if (topic.isPresent()) {
            Topic createdTopicEntity = topic.get();
            TopicDto createdTopicDto = new TopicDto();

            createdTopicDto.setId(createdTopicEntity.getId());

            UserDto secondUserDto = new UserDto();
            secondUserDto.setId(secondUserEntity.getId());
            secondUserDto.setNick(secondUserEntity.getNick());
            secondUserDto.setEmail(secondUserEntity.getEmail());
            secondUserDto.setCreatedAt(secondUserEntity.getCreatedAt());

            createdTopicDto.setSecondUser(secondUserDto);
            createdTopicDto.setCreatedAt(createdTopicEntity.getCreatedAt());

            return createdTopicDto;
        }

        return null;
    }
}
