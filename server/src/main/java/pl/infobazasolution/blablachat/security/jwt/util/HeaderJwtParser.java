package pl.infobazasolution.blablachat.security.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import pl.infobazasolution.blablachat.common.util.AuthenticationUtils;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
    public Claims parse(ServletRequest servletRequest, ServletResponse servletResponse) {
        String authorizationHeader = ((HttpServletRequest) servletRequest).getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new NotAuthorizedException("Nagłówek autoryzacyjny jest wymagany");
        }

        try {
            return AuthenticationUtils.parseJwtClaimsFromHeader(keyGenerator, authorizationHeader);
        } catch (Exception e) {
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        throw new NotAuthorizedException("Pozyskanie danych z tokenu nie powiodło się");
    }
}
