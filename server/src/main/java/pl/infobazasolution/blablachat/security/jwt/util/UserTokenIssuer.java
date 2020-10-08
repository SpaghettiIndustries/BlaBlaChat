package pl.infobazasolution.blablachat.security.jwt.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class UserTokenIssuer implements TokenIssuer {

    @Context
    private UriInfo uriInfo;

    @Inject
    private KeyGenerator keyGenerator;

    @Override
    public String issueToken(String subject, LocalDateTime expirationDate) {
        Key key = keyGenerator.generateKey();

        String jwtToken = Jwts.builder()
                .setSubject(subject)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(expirationDate)) // LocalDateTime.now().plusDays(7L)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return jwtToken;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
