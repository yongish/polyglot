package com.zhiyong.polyglot.resources;

import com.google.inject.Inject;
import com.zhiyong.polyglot.api.User;
import com.zhiyong.polyglot.db.Suggestion;
import com.zhiyong.polyglot.jdbi.SuggestionDAO;
import com.zhiyong.polyglot.utils.Utils;
import org.jooq.DSLContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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

    @PUT
    public Response insertSuggestion(@PathParam("term") String term,
                                     String suggestion,
                                     User user
    ) {
        suggestionDAO.insert(
                new Suggestion(Utils.getEpochSecond(), term, suggestion, user.getFamilyName(), user.getGivenName()),
                jooqContext
        );
        return Response.status(Response.Status.OK).build();
    }
}
