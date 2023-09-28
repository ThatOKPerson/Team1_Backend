package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Configuration of main application.
 */
public class TrueConfiguration extends Configuration {
  @Valid
  @NotNull
  private final SwaggerBundleConfiguration swagger = new SwaggerBundleConfiguration();

  /**
   * This is the swagger configuration.
   *
   * @return the endpoints to a http/https swagger url
   */
  @JsonProperty("swagger")
  public SwaggerBundleConfiguration getSwagger() {
    swagger.setResourcePackage("org.example.resources");
    String[] schemes = {"http", "https"};
    swagger.setSchemes(schemes);
    return swagger;
  }
}
