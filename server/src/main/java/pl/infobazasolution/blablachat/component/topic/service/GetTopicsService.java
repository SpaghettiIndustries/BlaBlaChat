package pl.infobazasolution.blablachat.component.topic.service;

import pl.infobazasolution.blablachat.component.message.dao.MessageDao;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.MessageFilter;
import pl.infobazasolution.blablachat.component.message.entity.Message;
import pl.infobazasolution.blablachat.component.topic.dao.TopicDao;
import pl.infobazasolution.blablachat.component.topic.dto.RecentTopicFilter;
import pl.infobazasolution.blablachat.component.topic.dto.TopicDto;
import pl.infobazasolution.blablachat.component.topic.dto.TopicFilter;
import pl.infobazasolution.blablachat.component.topic.entity.Topic;
import pl.infobazasolution.blablachat.component.user.session.UserSession;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GetTopicsService {

    @Inject
    private TopicDao topicDao;

    @Inject
    private MessageDao messageDao;

    @Inject
    private UserSession userSession;

    public List<TopicDto> get() {
        TopicFilter filter = new TopicFilter();
        filter.setFirstUserId(userSession.getId());

        List<Topic> topicEntities = topicDao.findAllUserParticipatesIn(filter);

        return convertToDto(topicEntities);
    }

    public List<TopicDto> get(RecentTopicFilter filter) {
        List<Topic> topicEntities = topicDao.findAllRecentUserParticipatesIn(userSession.getId(), filter);

        return convertToDto(topicEntities);
    }

    private List<TopicDto> convertToDto(List<Topic> topicEntities) {
        if (!topicEntities.isEmpty()) {
            List<TopicDto> topics = topicEntities.stream().map(topic -> {
                TopicDto topicDto = new TopicDto();

                topicDto.setId(topic.getId());
                topicDto.setSecondUserId(topic.getFirstUser().getId().equals(userSession.getId()) ? topic.getSecondUser().getId() : topic.getFirstUser().getId());

                MessageFilter messageFilter = new MessageFilter();
                messageFilter.setTopicId(topic.getId());
                messageFilter.setNumberOfMessages(1);

                Message lastMessage = messageDao.findRecent(messageFilter).get(0);

                MessageDto lastMessageDto = new MessageDto();
                lastMessageDto.setId(lastMessage.getId());
                lastMessageDto.setSenderId(lastMessage.getSender().getId());
                lastMessageDto.setTopicId(lastMessage.getTopic().getId());
                lastMessageDto.setContent(lastMessage.getContent());
                lastMessageDto.setCreatedAt(lastMessage.getCreatedAt());
                lastMessageDto.setUpdatedAt(lastMessage.getUpdatedAt());
                lastMessageDto.setReadAt(lastMessage.getReadAt());
                lastMessageDto.setDeletedAt(lastMessage.getDeletedAt());

                topicDto.setLastMessage(lastMessageDto);
                topicDto.setCreatedAt(topic.getCreatedAt());
                topicDto.setUpdatedAt(topic.getUpdatedAt());

                return topicDto;
            }).collect(Collectors.toList());

            return topics;
        }

        return null;
    }
}
