package pl.infobazasolution.blablachat.test.controller;

import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.entity.User;
import pl.infobazasolution.blablachat.security.jwt.JWTTokenNeeded;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Path("/test")
public class TestController {

    @GET
    @Path("/ping")
    @JWTTokenNeeded
    public String ping(){
        return LocalDateTime.now().toString();
    }
}
