package com.example.rest.beanparam;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/beanparam/{path}")
@Produces(MediaType.TEXT_PLAIN)
public class BeanParamResource {
    @GET
    public Response get(@BeanParam ParamBean params) {
        System.out.printf("id= %d%n", params.getId());
        System.out.printf("X-SomeHeader= %s%n", params.getSomeHeaderValue());
        System.out.printf("path= %s%n", params.pathParamValue);
        return Response.ok(params.toString()).build();
    }
}
