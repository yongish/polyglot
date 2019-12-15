package com.zhiyong.polyglot.jdbi;

import com.zhiyong.polyglot.db.Term;
import org.jooq.DSLContext;

import java.time.Instant;
import java.util.List;

import static org.jooq.codegen.maven.polyglot.tables.Term.TERM;

public class TermDAO {
    public List<String> getLatestTerms(DSLContext jooqContext) {
        return jooqContext.select(TERM.TERM_).from(TERM)
                .orderBy(TERM.CREATED_AT.desc())
                .limit(1000)
                .fetch()
                .getValues(TERM.TERM_);
    }

    public List<String> findTerms(String searchTerm, DSLContext jooqContext) {
        return jooqContext.select(TERM.TERM_).from(TERM)
                .where(TERM.TERM_.like("%" + searchTerm + "%"))
                .fetch()
                .getValues(TERM.TERM_);
    }

    public void insert(Term term, DSLContext jooqContext) {
        jooqContext.insertInto(TERM, TERM.CREATED_AT, TERM.FAMILYNAME, TERM.GIVENNAME, TERM.TERM_, TERM.VIEWS, TERM.VOTES)
                .values(Math.toIntExact(Instant.now().getEpochSecond()),
                        term.getFamilyName(), term.getGivenName(), term.getTerm(), term.getViews(), term.getVotes())
                .execute();
    }
}
