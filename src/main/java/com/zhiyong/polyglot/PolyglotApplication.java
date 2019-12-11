package com.zhiyong.polyglot;

import io.dropwizard.Application;
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
        // TODO: application initialization
    }

    @Override
    public void run(final PolyglotConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
