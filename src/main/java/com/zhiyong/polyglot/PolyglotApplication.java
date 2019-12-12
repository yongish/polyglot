package com.zhiyong.polyglot;

import com.bendb.dropwizard.jooq.JooqBundle;
import com.bendb.dropwizard.jooq.JooqFactory;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PolyglotApplication extends Application<PolyglotConfiguration> {

    public static void main(final String[] args) throws Exception {
        new PolyglotApplication().run(args);
    }

    @Override
    public String getName() {
        return "Polyglot";
    }

    @Override
    public void initialize(final Bootstrap<PolyglotConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor()
        ));
        bootstrap.addBundle(new JooqBundle<PolyglotConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(PolyglotConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public JooqFactory getJooqFactory(PolyglotConfiguration configuration) {
                return super.getJooqFactory(configuration);
            }
        });
    }

    @Override
    public void run(final PolyglotConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
