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
    public String post(String synonyms) throws WordAlreadyExistsException {
        List<String> synonymList = new ArrayList<>(Arrays.asList(synonyms.split(",")));
        if(null != map.putIfAbsent(word, synonymList)) {
            throw new WordAlreadyExistsException(word);
        }
        return String.join(",", synonymList);
    }

    @PUT
    public String put(String synonyms) throws NoSuchWordException {
        List<String> synonymList = new ArrayList<>(Arrays.asList(synonyms.split(",")));
        if(null == map.replace(word, synonymList)) {
            throw new NoSuchWordException(word);
        }
        return String.join(",", synonymList);
    }

    @PATCH
    public String patch(String newSynonyms) throws NoSuchWordException {
        List<String> synonyms = map.get(word);
        if (synonyms == null) {
            throw new NoSuchWordException(word);
        }
        synonyms.addAll(Arrays.asList(newSynonyms.split(",")));
        return String.join(",", synonyms);
    }
}
