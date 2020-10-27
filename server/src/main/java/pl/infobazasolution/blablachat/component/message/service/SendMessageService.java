package pl.infobazasolution.blablachat.component.message.service;

import pl.infobazasolution.blablachat.component.message.dao.MessageDao;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.NewMessage;
import pl.infobazasolution.blablachat.component.message.entity.Message;
import pl.infobazasolution.blablachat.component.topic.dao.TopicDao;
import pl.infobazasolution.blablachat.component.topic.dto.NewTopic;
import pl.infobazasolution.blablachat.component.topic.dto.TopicDto;
import pl.infobazasolution.blablachat.component.topic.dto.TopicFilter;
import pl.infobazasolution.blablachat.component.topic.entity.Topic;
import pl.infobazasolution.blablachat.component.topic.service.CreateTopicService;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.entity.User;
import pl.infobazasolution.blablachat.component.user.session.UserSession;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

public class SendMessageService {

    @Inject
    private MessageDao messageDao;

    @Inject
    private TopicDao topicDao;

    @Inject
    private UserDao userDao;

    @Inject
    private CreateTopicService createTopicService;

    @Inject
    private UserSession userSession;

    public MessageDto send(NewMessage newMessage) {
        Optional<Topic> topicMaybe;

        if (Objects.nonNull(newMessage.getReceiverId())) {
            TopicFilter filter = new TopicFilter();
            filter.setFirstUserId(userSession.getId());
            filter.setSecondUserId(newMessage.getReceiverId());

            topicMaybe = topicDao.find(filter);

            if (!topicMaybe.isPresent()) {
                NewTopic newTopic = new NewTopic();
                newTopic.setFirstUserId(userSession.getId());
                newTopic.setSecondUserId(newMessage.getReceiverId());

                TopicDto createdTopic = createTopicService.create(newTopic);

                topicMaybe = Optional.of(topicDao.findById(createdTopic.getId()).get());
            }
        } else if (Objects.nonNull(newMessage.getTopicId())) {
            topicMaybe = topicDao.findById(newMessage.getTopicId());
        } else {
            return null;
        }

        if (topicMaybe.isPresent()) {
            Topic topic = topicMaybe.get();
            User sender = userDao.findById(userSession.getId()).get();

            Message newMessageEntity = new Message();

            newMessageEntity.setSender(sender);
            newMessageEntity.setTopic(topic);
            newMessageEntity.setContent(newMessage.getContent());
            newMessageEntity.setCreatedAt(ZonedDateTime.now());

            Optional<Message> message = messageDao.create(newMessageEntity);

            if (message.isPresent()) {
                Message createdMessageEntity = message.get();
                MessageDto createdMessageDto = new MessageDto();

                topic.setUpdatedAt(createdMessageEntity.getCreatedAt());
                topicDao.update(topic.getId(), topic);

                createdMessageDto.setId(createdMessageEntity.getId());
                createdMessageDto.setTopicId(createdMessageEntity.getTopic().getId());
                createdMessageDto.setContent(createdMessageEntity.getContent());
                createdMessageDto.setCreatedAt(createdMessageEntity.getCreatedAt());

                return createdMessageDto;
            }

            return null;
        }

        return null;
    }
}
