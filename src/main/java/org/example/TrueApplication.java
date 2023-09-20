package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.resources.HelloWorldController;

/**
 * Main Application.
 */
public class TrueApplication extends Application<TrueConfiguration> {

  public static void main(final String[] args) throws Exception {
    new TrueApplication().run(args);
  }

  @Override
  public String getName() {
    return "true";
  }

  @Override
  public void initialize(final Bootstrap<TrueConfiguration> bootstrap) {
    // TODO: application initialization
  }

  @Override
  public void run(final TrueConfiguration configuration,
                  final Environment environment) {
    environment.jersey().register(new HelloWorldController());
  }

}
