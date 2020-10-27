package pl.infobazasolution.blablachat.security.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.security.Key;

public class HeaderJwtParser implements JwtParser {

    @Inject
    private KeyGenerator keyGenerator;

    @Override
    public Claims parse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String authorizationHeader = httpServletResponse.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            Key key = keyGenerator.generateKey();
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return null;
    }
}
