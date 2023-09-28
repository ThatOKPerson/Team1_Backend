package org.example.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import java.util.List;
import org.example.TrueApplication;
import org.example.TrueConfiguration;
import org.example.cli.JobRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Integration test class to see if the class works as expected as a whole.
 *
 * @author orankelly
 */
@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleControllerIntegrationTest {
  private final String baseUrl = System.getenv("BASE_URL");
  private final String apiUrlJobRoles = baseUrl + "/job-roles";

  static final DropwizardAppExtension<TrueConfiguration> APP = new DropwizardAppExtension<>(
      TrueApplication.class, null,
      new ResourceConfigurationSourceProvider()
  );

  @Test
  void getRoles_shouldReturnListOfRoles() {
    List<JobRole> response = APP.client().target(apiUrlJobRoles)
        .request()
        .get(List.class);

    Assertions.assertNotNull(response);
  }
}
