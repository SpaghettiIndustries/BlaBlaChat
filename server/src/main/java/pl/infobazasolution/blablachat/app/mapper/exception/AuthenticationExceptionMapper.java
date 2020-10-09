package pl.infobazasolution.blablachat.app.mapper.exception;

import pl.infobazasolution.blablachat.common.exception.AuthenticationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    @Override
    public Response toResponse(AuthenticationException e) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(e.getMessage())
                .build();
    }
}
