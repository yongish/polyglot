package com.zhiyong.polyglot.resources;

import com.google.inject.Inject;
import com.zhiyong.polyglot.db.Quiz;
import com.zhiyong.polyglot.jdbi.QuizDAO;
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

@Path("/quiz/{uid}")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class QuizResource {
    private QuizDAO quizDAO;
    private DSLContext jooqContext;

    @Inject
    public QuizResource(QuizDAO quizDAO, DSLContext jooqContext) {
        this.quizDAO = quizDAO;
        this.jooqContext = jooqContext;
    }

    @GET
    public Response getQuizzes(@PathParam("uid") String uid) {
        GenericEntity<List<Quiz>> entities = new GenericEntity<>(quizDAO.get(uid, jooqContext)){};
        return Response.ok(entities).build();
    }

    @POST
    @Path("/create")
    public Response insertQuiz(@PathParam("uid") String uid, Quiz quiz) {
        try {
            quizDAO.insert(quiz, jooqContext);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        GenericEntity<List<Quiz>> entities = new GenericEntity<>(quizDAO.get(uid, jooqContext)){};
        return Response.ok(entities).build();
    }
}
