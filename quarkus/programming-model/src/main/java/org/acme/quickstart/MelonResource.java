package org.acme.quickstart;

import org.acme.quickstart.entity.Melon;
import org.acme.quickstart.service.MelonService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/melon")
public class MelonResource {
    private MelonService melonService;

    @Inject
    public MelonResource(MelonService melonService) {
        this.melonService = melonService;
    }

    @GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Melon find(@PathParam("type") String type) {
        return melonService.find(type);
    }
}
