package com.zhiyong.polyglot.resources;

import com.google.inject.Inject;
import com.zhiyong.polyglot.api.NewSuggestion;
import com.zhiyong.polyglot.db.Suggestion;
import com.zhiyong.polyglot.jdbi.SuggestionDAO;
import com.zhiyong.polyglot.utils.Utils;
import org.jooq.DSLContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/suggestion/{term}")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class SuggestionResource {
    private SuggestionDAO suggestionDAO;
    private DSLContext jooqContext;

    @Inject
    public SuggestionResource(SuggestionDAO suggestionDAO, DSLContext jooqContext) {
        this.suggestionDAO = suggestionDAO;
        this.jooqContext = jooqContext;
    }

    @GET
    public Response getSuggestions(@PathParam("term") String term) {
        GenericEntity<List<Suggestion>> entities = new GenericEntity<>(suggestionDAO.get(term, jooqContext)){};
        return Response.ok(entities).build();
    }

    @POST
    @Path("/create")
    public Response insertSuggestion(@PathParam("term") String term, NewSuggestion suggestion) {
        try {
            suggestionDAO.insert(
                    new Suggestion(
                            Utils.getEpochSecond(), term,
                            suggestion.getContent(),
                            suggestion.getEmail(), suggestion.getFamilyName(), suggestion.getGivenName()
                    ),
                    jooqContext
            );
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage())
                    .build();
        }
        GenericEntity<List<Suggestion>> entities = new GenericEntity<>(suggestionDAO.get(term, jooqContext)){};
        return Response.ok(entities).build();
    }

    @POST
    @Path("/update")
    public Response updateSuggestion(@PathParam("term") String term, NewSuggestion suggestion) {
        suggestionDAO.update(
                new Suggestion(
                        Utils.getEpochSecond(), // Unused.
                        term,
                        suggestion.getContent(),
                        suggestion.getEmail(), suggestion.getFamilyName(), suggestion.getGivenName()
                ),
                jooqContext
        );
        GenericEntity<List<Suggestion>> entities = new GenericEntity<>(suggestionDAO.get(term, jooqContext)){};
        return Response.ok(entities).build();
    }

    @DELETE
    public Response deleteSuggestion(@PathParam("term") String term, NewSuggestion suggestion) {
        suggestionDAO.delete(term, suggestion.getContent(), jooqContext);
        GenericEntity<List<Suggestion>> entities = new GenericEntity<>(suggestionDAO.get(term, jooqContext)){};
        return Response.ok(entities).build();
    }
}
