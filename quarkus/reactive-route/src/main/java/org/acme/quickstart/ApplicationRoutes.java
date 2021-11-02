package org.acme.quickstart;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import io.quarkus.vertx.web.Route;

@ApplicationScoped
public class ApplicationRoutes {
    public void routes(@Observes Router router) {
        router
                .get("/ok")
                .handler(routingContext -> {
                    routingContext.response().end("Ok from Route");
                });
    }

    @Route(path = "declarativeok", methods = Route.HttpMethod.GET)
    public void greetings(RoutingContext routingContext) {
        String name = routingContext.request().getParam("name");
        if(null == name) {
            name = "world";
        }
        routingContext.response().end("OK " + name + " you are right");
    }
}
