package com.zhiyong.polyglot;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.setup.Environment;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;

public class PolyglotServiceModule extends AbstractModule {
    private final PolyglotConfiguration configuration;
    private final Environment environment;
    private Connection conn;
    private DSLContext jooqContext;

    PolyglotServiceModule(PolyglotConfiguration configuration, Environment environment) {
        this.configuration = configuration;
        this.environment = environment;
    }

    @Provides @Singleton
    public DSLContext getJooqContext() {
        if (jooqContext == null) {
            ManagedDataSource dataSource = configuration.getDataSourceFactory().build(environment.metrics(), "jooq");
            jooqContext = DSL.using(dataSource, SQLDialect.MYSQL);
        }
        return jooqContext;
    }
}
