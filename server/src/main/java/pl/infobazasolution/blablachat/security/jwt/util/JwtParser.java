package pl.infobazasolution.blablachat.security.jwt.util;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;

public interface JwtParser {
    Claims parse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}