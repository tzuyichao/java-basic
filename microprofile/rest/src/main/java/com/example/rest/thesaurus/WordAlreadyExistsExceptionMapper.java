package com.example.rest.thesaurus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WordAlreadyExistsExceptionMapper implements ExceptionMapper<WordAlreadyExistsException> {

    @Override
    public Response toResponse(WordAlreadyExistsException e) {
        return Response.status(409).build();
    }
}
