package pl.infobazasolution.blablachat.security.jwt.util;

import javax.ws.rs.core.UriInfo;
import java.time.LocalDateTime;

public interface TokenIssuer {
    String issueToken(String subject, LocalDateTime expirationDateTime, UriInfo uriInfo);
}
