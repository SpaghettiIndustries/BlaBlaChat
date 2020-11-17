package pl.infobazasolution.blablachat.common.websocket;

import pl.infobazasolution.blablachat.common.util.AuthenticationUtils;
import pl.infobazasolution.blablachat.security.jwt.util.KeyGenerator;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.ws.rs.core.HttpHeaders;
import java.lang.reflect.Field;

public class ServletAwareConfigurator extends ServerEndpointConfig.Configurator {

    @Inject
    private KeyGenerator keyGenerator;

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpServletRequest httpServletRequest = getField(request, HttpServletRequest.class);

        String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        Integer userId = Integer.parseInt(AuthenticationUtils.parseJwtClaimsFromHeader(keyGenerator, authorizationHeader).getSubject());

        config.getUserProperties().put("userId", userId);
    }

    private static <I, F> F getField(I instance, Class<F> fieldType) {
        try {
            for (Class<?> type = instance.getClass(); type != Object.class; type = type.getSuperclass()) {
                for (Field field : type.getDeclaredFields()) {
                    if (fieldType.isAssignableFrom(field.getType())) {
                        field.setAccessible(true);

                        return (F) field.get(instance);
                    }
                }
            }
        } catch (Exception e) {

        }

        return null;
    }
}
