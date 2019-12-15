package com.zhiyong.polyglot.jdbi;

import com.zhiyong.polyglot.db.Suggestion;
import org.jooq.DSLContext;
import org.jooq.codegen.maven.polyglot.tables.records.SuggestionRecord;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.codegen.maven.polyglot.Tables.SUGGESTION;

public class SuggestionDAO {
    public List<Suggestion> get(String term, DSLContext jooqContext) {
        return jooqContext.select().from(SUGGESTION).where(SUGGESTION.TERM.equal(term))
                .fetch().into(SuggestionRecord.class)
                .stream().map(x -> new Suggestion(
                        x.getCreatedAt(), x.getTerm(), x.getContent(), x.getFamilyName(), x.getGivenName()
                ))
                .collect(Collectors.toList());
    }

    public void insert(Suggestion suggestion, DSLContext jooqContext) {
        jooqContext.insertInto(
                SUGGESTION,
                SUGGESTION.CREATED_AT,
                SUGGESTION.TERM,
                SUGGESTION.CONTENT,
                SUGGESTION.FAMILY_NAME,
                SUGGESTION.GIVEN_NAME).values(Math.toIntExact(Instant.now().getEpochSecond()),
                        suggestion.getTerm(),
                        suggestion.getContent(),
                        suggestion.getFamilyName(),
                        suggestion.getGivenName()
                ).execute();
    }
}
