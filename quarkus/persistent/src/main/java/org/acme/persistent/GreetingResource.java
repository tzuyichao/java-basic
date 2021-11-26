package org.acme.persistent;

import io.agroal.api.AgroalDataSource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    @Inject
    AgroalDataSource dataSource;

    @Inject
    @io.quarkus.agroal.DataSource("users")
    AgroalDataSource userDataSource;

    @Inject
    @io.quarkus.agroal.DataSource("inventory")
    AgroalDataSource inventoryDataSource;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }
}