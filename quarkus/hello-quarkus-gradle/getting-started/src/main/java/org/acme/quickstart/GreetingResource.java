package org.acme.quickstart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void create(String message) {
        System.out.println("Create");
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public String update(String message) {
        System.out.println("Update");
        return message;
    }

    @DELETE
    public void delete() {
        System.out.println("Delete");
    }
}