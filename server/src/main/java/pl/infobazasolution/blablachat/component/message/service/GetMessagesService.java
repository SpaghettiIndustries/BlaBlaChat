package pl.infobazasolution.blablachat.component.message.service;

import pl.infobazasolution.blablachat.component.message.dao.MessageDao;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.MessageFilter;
import pl.infobazasolution.blablachat.component.message.entity.Message;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class GetMessagesService {

    @Inject
    private MessageDao messageDao;

    public List<MessageDto> get(MessageFilter filter) {
        List<Message> messageEntities = messageDao.findRecent(filter);

        if (!messageEntities.isEmpty()) {
            List<MessageDto> messages = messageEntities.stream().map(message -> {
              MessageDto messageDto = new MessageDto();

              messageDto.setId(message.getId());
              messageDto.setTopicId(message.getTopic().getId());
              messageDto.setContent(message.getContent());
              messageDto.setCreatedAt(message.getCreatedAt());
              messageDto.setUpdatedAt(message.getUpdatedAt());
              messageDto.setReadAt(message.getReadAt());
              messageDto.setDeletedAt(messageDto.getDeletedAt());

              return messageDto;
            }).collect(Collectors.toList());

            return messages;
        }

        return null;
    }
}
