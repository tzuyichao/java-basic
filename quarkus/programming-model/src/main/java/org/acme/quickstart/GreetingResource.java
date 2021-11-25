package org.acme.quickstart;

import org.acme.quickstart.service.HelloService;
import org.acme.quickstart.service.RecommendationService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello")
public class GreetingResource {
    HelloService helloService;
    private RecommendationService recommendationService;

    @Inject
    public GreetingResource(HelloService helloService, RecommendationService recommendationService) {
        this.helloService = helloService;
        this.recommendationService = recommendationService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return helloService.getGreeting();
    }

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> products() {
        return recommendationService.getProducts();
    }
}
