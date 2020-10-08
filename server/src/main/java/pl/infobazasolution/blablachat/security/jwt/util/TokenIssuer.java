package pl.infobazasolution.blablachat.security.jwt.util;

import java.time.LocalDateTime;

public interface TokenIssuer {
    String issueToken(String subject, LocalDateTime expirationDateTime);
}
