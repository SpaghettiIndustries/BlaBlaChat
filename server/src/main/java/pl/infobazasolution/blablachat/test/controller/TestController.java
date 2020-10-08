package pl.infobazasolution.blablachat.test.controller;

import pl.infobazasolution.blablachat.component.user.dao.UserDao;
import pl.infobazasolution.blablachat.component.user.entity.User;

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

    @Inject
    private UserDao userDao;

    @GET
    @Path("/ping")
    public String ping(){
        return LocalDateTime.now().toString();
    }

    @GET
    @Path("/hibernate/{id}")
    public String hibernateTest(@PathParam("id") Integer id) {
        Optional<User> user = userDao.selectUserById(id);

        if(user.isPresent()) {
            return user.get().getNick();
        }

        return "EMPTY";
    }

    @GET
    @Path("/hibernate_all")
    public String hibernateTestAll() {
        ArrayList<String> users = new ArrayList<String>();

        userDao.selectAllUsers().forEach(user -> {
            users.add(user.getNick());
        });

        return users.toString();
    }
}
