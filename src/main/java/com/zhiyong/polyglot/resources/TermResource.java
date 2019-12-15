package com.zhiyong.polyglot.resources;

import com.google.inject.Inject;
import com.zhiyong.polyglot.api.User;
import com.zhiyong.polyglot.db.Term;
import com.zhiyong.polyglot.jdbi.TermDAO;
import com.zhiyong.polyglot.utils.Utils;
import org.jooq.DSLContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    private User user;

    @Inject
    public TermResource(TermDAO termDAO, DSLContext jooqContext) {
        this.termDAO = termDAO;
        this.jooqContext = jooqContext;
        this.user = user;
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

    @PUT
    public Response insertTerm(@QueryParam("username") String familyName,
                               @QueryParam("givenname") String givenName,
//                               @QueryParam("user") User user,
                               @QueryParam("term") String term) {
        termDAO.insert(new Term(
                Utils.getEpochSecond(),
                familyName, givenName,
                term,
                0,
                0
        ), jooqContext);
        return Response.status(Response.Status.OK).build();
    }
}
