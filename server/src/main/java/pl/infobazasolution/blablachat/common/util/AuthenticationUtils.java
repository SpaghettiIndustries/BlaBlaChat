package pl.infobazasolution.blablachat.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import pl.infobazasolution.blablachat.security.jwt.util.KeyGenerator;

import javax.inject.Inject;
import java.security.Key;

public class AuthenticationUtils {

    public static Claims parseJwtClaimsFromHeader(KeyGenerator keyGenerator, String authorizationHeader) {
        Key key = keyGenerator.generateKey();

        String token = authorizationHeader.substring("Bearer".length()).trim();

        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
