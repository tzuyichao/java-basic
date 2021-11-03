package com.example.rest.thesaurus;

import com.example.rest.thesaurus.NoSuchWordException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/thesaurus/{word}")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class ThesaurusResource {
    private static Map<String, List<String>> map = new HashMap<>();

    @PathParam("word")
    private String word;

    @GET
    public String get() throws NoSuchWordException {
        List<String> synonyms = map.get(word);
        if(null == synonyms) {
            throw new NoSuchWordException(word);
        }
        return String.join(",", synonyms);
    }

    @POST
    public String post(String synonyms) {
        List<String> synonymList = new ArrayList<>(Arrays.asList(synonyms.split(",")));
        map.put(word, synonymList);
        return String.join(",", synonymList);
    }
}
