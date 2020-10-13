package pl.infobazasolution.blablachat.component.message.service;

import pl.infobazasolution.blablachat.component.message.dao.MessageDao;
import pl.infobazasolution.blablachat.component.message.dto.MessageDto;
import pl.infobazasolution.blablachat.component.message.dto.MessageFilter;
import pl.infobazasolution.blablachat.component.message.entity.Message;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GetMessagesService {

    @Inject
    private MessageDao messageDao;

    public List<MessageDto> get(MessageFilter filter) {
        List<Message> messageEntities = messageDao.findRecent(filter);

        if (!messageEntities.isEmpty()) {
            List<MessageDto> messages = messageEntities.stream().map(message -> {
                MessageDto messageDto = new MessageDto();

                if (Objects.isNull(message.getReadAt())) {
                  message.setReadAt(ZonedDateTime.now());

                  Message updatedReadMessage = messageDao.update(message.getId(), message).get();

                  messageDto.setReadAt(updatedReadMessage.getReadAt());
                }


                messageDto.setId(message.getId());
                messageDto.setTopicId(message.getTopic().getId());
                messageDto.setContent(message.getContent());
                messageDto.setCreatedAt(message.getCreatedAt());
                messageDto.setUpdatedAt(message.getUpdatedAt());
                messageDto.setDeletedAt(messageDto.getDeletedAt());

                if (Objects.isNull(messageDto.getReadAt()))
                    messageDto.setReadAt(message.getReadAt());

                return messageDto;
            }).collect(Collectors.toList());

            return messages;
        }

        return null;
    }
}
