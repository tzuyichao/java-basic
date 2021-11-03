package com.example.rest.thesaurus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoSuchWordExceptionMapper implements ExceptionMapper<NoSuchWordException> {

    @Override
    public Response toResponse(NoSuchWordException ex) {
        return Response.status(404).entity(ex.getMessage()).build();
    }
}
