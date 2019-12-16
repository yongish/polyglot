package com.zhiyong.polyglot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zhiyong.polyglot.health.PolyglotHealthCheck;
import com.zhiyong.polyglot.resources.SuggestionResource;
import com.zhiyong.polyglot.resources.TermResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

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
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "*");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        injector = Guice.createInjector(new PolyglotServiceModule(configuration, environment));
        environment.healthChecks().register("polyglot", injector.getInstance(PolyglotHealthCheck.class));

        environment.jersey().register(injector.getInstance(TermResource.class));
        environment.jersey().register(injector.getInstance(SuggestionResource.class));
    }

}
