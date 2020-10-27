package pl.infobazasolution.blablachat.security.jwt;

import io.jsonwebtoken.Claims;
import pl.infobazasolution.blablachat.component.user.session.UserSession;
import pl.infobazasolution.blablachat.security.jwt.util.CookieJwtParser;
import pl.infobazasolution.blablachat.security.jwt.util.JwtParser;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtTokenNeededFilter implements Filter {

    @Inject
    private CookieJwtParser jwtParser;

    @Inject
    private UserSession userSession;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Claims claims = jwtParser.parse((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);

        if (Objects.nonNull(claims)) {
            userSession.setId(Integer.parseInt(claims.getSubject()));
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
