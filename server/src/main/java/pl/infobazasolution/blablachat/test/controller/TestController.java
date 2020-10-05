package pl.infobazasolution.blablachat.test.controller;

import pl.infobazasolution.blablachat.security.jwt.JWTTokenNeeded;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import java.time.LocalDateTime;

@Path("/test")
public class TestController {
    @GET
    @Path("/ping")
    @JWTTokenNeeded
    public String ping(){
        return LocalDateTime.now().toString();
    }
}
