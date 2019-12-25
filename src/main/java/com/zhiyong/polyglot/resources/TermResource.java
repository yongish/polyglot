package com.zhiyong.polyglot.resources;

import com.google.inject.Inject;
import com.zhiyong.polyglot.api.User;
import com.zhiyong.polyglot.db.Term;
import com.zhiyong.polyglot.jdbi.TermDAO;
import com.zhiyong.polyglot.utils.Utils;
import org.jooq.DSLContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/term")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class TermResource {
    private TermDAO termDAO;
    private DSLContext jooqContext;

    @Inject
    public TermResource(TermDAO termDAO, DSLContext jooqContext) {
        this.termDAO = termDAO;
        this.jooqContext = jooqContext;
    }

    @Path("/latest")
    @GET
    public Response getLatestTerms() {
        GenericEntity<List<String>> entities = new GenericEntity<>(termDAO.getLatestTerms(jooqContext)){};
        return Response.ok(entities).build();
    }

    @Path("/{searchTerm}")
    @GET
    public Response findTerms(@PathParam("searchTerm") String searchTerm) {
        GenericEntity<List<String>> entities = new GenericEntity<>(termDAO.findTerms(searchTerm, jooqContext)){};
        return Response.ok(entities).build();
    }

    @Path("/{term}")
    @POST
    public Response insertTerm(@PathParam("term") String term, User user) {
        termDAO.insert(new Term(
                Utils.getEpochSecond(),
                user.getFamilyName(),
                user.getGivenName(),
                term,
                0,
                0,
                0
        ), jooqContext);
        GenericEntity<List<String>> entities = new GenericEntity<>(termDAO.findTerms(term, jooqContext)){};
        return Response.ok(entities).build();
    }
}
