package pl.infobazasolution.blablachat.security.jwt;

import io.jsonwebtoken.Claims;
import pl.infobazasolution.blablachat.security.jwt.util.HeaderJwtParser;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.util.Objects;

public class JwtTokenNeededFilter implements Filter {

    @Inject
    private HeaderJwtParser jwtParser;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Claims claims = jwtParser.parse(servletRequest, servletResponse);

        if (Objects.nonNull(claims)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
