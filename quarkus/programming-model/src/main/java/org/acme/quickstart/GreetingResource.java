package org.acme.quickstart;

import org.acme.quickstart.service.GreetingService;
import org.acme.quickstart.service.HelloService;
import org.acme.quickstart.service.RecommendationService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello")
public class GreetingResource {
    HelloService helloService;
    GreetingService greetingService;
    private RecommendationService recommendationService;

    @Inject
    public GreetingResource(HelloService helloService, RecommendationService recommendationService, GreetingService greetingService) {
        this.helloService = helloService;
        this.recommendationService = recommendationService;
        this.greetingService = greetingService;
    }

    @GET
    @Path("/greeting")
    @Produces(MediaType.TEXT_PLAIN)
    public String greeting() {
        return greetingService.message();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return helloService.getGreeting();
    }

    @GET
    @Path("/l")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWithLocale(@QueryParam("locale") String locale) {
        return helloService.getGreeting(locale);
    }

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> products() {
        return recommendationService.getProducts();
    }
}
