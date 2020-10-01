package pl.infobazasolution.blablachat.test.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import java.time.LocalDateTime;

@Path("/test")
public class TestController {
    @GET
    @Path("/ping")
    public String ping(){
        return LocalDateTime.now().toString();
    }
}
