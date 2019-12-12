package com.zhiyong.polyglot.health;

import com.codahale.metrics.health.HealthCheck;

public class PolyglotHealthCheck extends HealthCheck {
//    private final QuizResource quizResource;

//    @Inject
//    public PolyglotHealthCheck(QuizResource quizResource) {
//        this.quizResource = quizResource;
//    }

    @Override
    protected Result check() throws Exception {
//        Optional<String> dbHealthStatus = quizService.performHealthCheck();
//        if (dbHealthStatus.isPresent()) {
//            return Result.unhealthy(dbHealthStatus.get());
//        }
        return Result.healthy();
    }
}
