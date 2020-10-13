package pl.infobazasolution.blablachat.common.util;

import org.apache.commons.validator.EmailValidator;
import pl.infobazasolution.blablachat.component.topic.dao.TopicDao;
import pl.infobazasolution.blablachat.component.topic.entity.Topic;
import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.entity.User;

import javax.inject.Inject;
import java.util.List;

public class ValidationUtils {

    public static Boolean containsWhitespace(String str) {
        if (!(str != null && str.length() > 0)) {
            return false;
        }

        int strLen = str.length();

        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static Boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();

        return validator.isValid(email);
    }

    public static Boolean userExists(Integer id, UserDao userDao) {
        List<User> existingUsers = userDao.readAll();

        Boolean exists = false;

        for (User existingUser : existingUsers) {
            if (existingUser.getId().equals(id))
                exists = true;
        }

        return exists;
    }

    public static Boolean topicExists(Integer id, TopicDao topicDao) {
        List<Topic> existingTopics = topicDao.readAll();

        Boolean exists = false;

        for (Topic existingTopic : existingTopics) {
            if (existingTopic.getId().equals(id))
                exists = true;
        }

        return exists;
    }
}
