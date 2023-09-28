package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.example.resources.AuthController;
import org.example.resources.JobRoleController;

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
    bootstrap.addBundle(new SwaggerBundle<TrueConfiguration>() {
      @Override
      protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
          TrueConfiguration configuration) {
        return configuration.getSwagger();
      }
    });
  }

  @Override
  public void run(final TrueConfiguration configuration,
                  final Environment environment) {
    environment.jersey().register(new JobRoleController());
    environment.jersey().register(new AuthController());
  }

}
