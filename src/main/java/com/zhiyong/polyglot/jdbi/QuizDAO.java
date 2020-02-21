package com.zhiyong.polyglot.jdbi;

import com.zhiyong.polyglot.db.Quiz;
import org.jooq.DSLContext;
import org.jooq.codegen.maven.polyglot.tables.records.QuizRecord;

import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.codegen.maven.polyglot.Tables.QUIZ;

public class QuizDAO {
    public List<Quiz> get(String userUid, DSLContext jooqContext) {
        return jooqContext.select().from(QUIZ).where(QUIZ.USER_UID.equal(userUid))
                .fetch().into(QuizRecord.class)
                .stream().map(x -> new Quiz(x.getUserUid(), x.getQuizId(), x.getEmail(), x.getDate(),
                        x.getTitle(), x.getTotalWords(), x.getNotLearned(), x.getRound()))
                .collect(Collectors.toList());
    }

    public void insert(Quiz quiz, DSLContext jooqContext) {
        jooqContext.insertInto(QUIZ, QUIZ.USER_UID, QUIZ.QUIZ_ID, QUIZ.EMAIL, QUIZ.DATE,
                QUIZ.TITLE, QUIZ.TOTAL_WORDS, QUIZ.NOT_LEARNED, QUIZ.ROUND)
                .values(quiz.getUserUid(), quiz.getQuizId(), quiz.getEmail(), quiz.getDate(),
                        quiz.getTitle(), quiz.getTotalWords(), quiz.getNotLearned(), quiz.getRound())
                .execute();
    }

    public void update(Quiz quiz, DSLContext jooqContext) {
        jooqContext.update(QUIZ)
                .set(QUIZ.EMAIL, quiz.getEmail())
                .set(QUIZ.DATE, quiz.getDate())
                .set(QUIZ.TITLE, quiz.getTitle())
                .set(QUIZ.TOTAL_WORDS, quiz.getTotalWords())
                .set(QUIZ.NOT_LEARNED, quiz.getNotLearned())
                .set(QUIZ.ROUND, quiz.getRound())
                .where(QUIZ.USER_UID.equal(quiz.getUserUid())).and(QUIZ.QUIZ_ID.equal(quiz.getQuizId()))
                .execute();
    }

    public void delete(String userUid, int quizId, DSLContext jooqContext) {
        jooqContext.delete(QUIZ)
                .where(QUIZ.USER_UID.equal(userUid)).and(QUIZ.QUIZ_ID.equal(quizId))
                .execute();
    }
}
