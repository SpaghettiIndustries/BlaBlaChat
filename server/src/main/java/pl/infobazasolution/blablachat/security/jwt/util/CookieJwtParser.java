/*
package pl.infobazasolution.blablachat.security.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import pl.infobazasolution.blablachat.component.user.session.UserSession;
import pl.infobazasolution.blablachat.common.util.CookieUtils;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;

public class CookieJwtParser implements JwtParser {

    @Inject
    private KeyGenerator keyGenerator;

    @Override
    public Claims parse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Cookie tokenCookie = CookieUtils.findCookie(httpServletRequest.getCookies(), "token");

        if (tokenCookie == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        try {
            String token = tokenCookie.getValue();
            Key key = keyGenerator.generateKey();
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return null;
    }
}
*/
