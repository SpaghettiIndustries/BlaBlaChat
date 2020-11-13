package pl.infobazasolution.blablachat.component.topic.service;

import pl.infobazasolution.blablachat.common.util.AuthenticationUtils;
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
import pl.infobazasolution.blablachat.security.jwt.util.KeyGenerator;

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
    private KeyGenerator keyGenerator;

    public List<TopicDto> get(String authorizationHeader) {
        Integer userId = Integer.parseInt(AuthenticationUtils.parseJwtClaimsFromHeader(keyGenerator, authorizationHeader).getSubject());

        TopicFilter filter = new TopicFilter();
        filter.setFirstUserId(userId);

        List<Topic> topicEntities = topicDao.findAllUserParticipatesIn(filter);

        return convertToDto(topicEntities, userId);
    }

    public List<TopicDto> get(String authorizationHeader, RecentTopicFilter filter) {
        Integer userId = Integer.parseInt(AuthenticationUtils.parseJwtClaimsFromHeader(keyGenerator, authorizationHeader).getSubject());

        List<Topic> topicEntities = topicDao.findAllRecentUserParticipatesIn(userId, filter);

        return convertToDto(topicEntities, userId);
    }

    private List<TopicDto> convertToDto(List<Topic> topicEntities, Integer userId) {
        if (!topicEntities.isEmpty()) {
            List<TopicDto> topics = topicEntities.stream().map(topic -> {
                TopicDto topicDto = new TopicDto();

                topicDto.setId(topic.getId());
                topicDto.setSecondUserId(topic.getFirstUser().getId().equals(userId) ? topic.getSecondUser().getId() : topic.getFirstUser().getId());

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
