package org.acme.quickstart;

import org.acme.quickstart.service.HelloService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    HelloService helloService;

    @Inject
    public GreetingResource(HelloService helloService) {
        this.helloService = helloService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return helloService.getGreeting();
    }
}
