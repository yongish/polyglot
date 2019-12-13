package com.zhiyong.polyglot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zhiyong.polyglot.resources.TermResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PolyglotApplication extends Application<PolyglotConfiguration> {

    private Injector injector;

    public static void main(final String[] args) throws Exception {
        new PolyglotApplication().run(args);
    }

    @Override
    public String getName() {
        return "Polyglot";
    }

    @Override
    public void initialize(Bootstrap<PolyglotConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor()
        ));
    }

    @Override
    public void run(final PolyglotConfiguration configuration,
                    final Environment environment) {
        injector = Guice.createInjector(new PolyglotServiceModule(configuration, environment));

        environment.jersey().register(injector.getInstance(TermResource.class));
    }

}
