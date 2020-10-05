package pl.infobazasolution.blablachat.test.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pl.infobazasolution.blablachat.security.jwt.JWTTokenNeeded;
import pl.infobazasolution.blablachat.security.jwt.util.KeyGenerator;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.UriInfo;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Path("/test")
public class TestController {
    @Inject
    private KeyGenerator keyGenerator;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/ping")
    @JWTTokenNeeded
    public String ping() {
        return LocalDateTime.now().toString();
    }

    @GET
    @Path("/jwt")
    public String getTestJwt() {
        return issueToken("alice");
    }

    private String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
