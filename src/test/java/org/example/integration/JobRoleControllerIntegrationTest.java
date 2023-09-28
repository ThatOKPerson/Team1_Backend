package org.example.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import java.util.List;
import javax.ws.rs.core.Response;
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
  private final String apiUrlJobSpecification = baseUrl + "/job-specification/";

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

  @Test
  void getSpecificationById_shouldGetSpecificationById() {
    int id = 1;
    Response response = APP.client().target(apiUrlJobSpecification + id)
        .request()
        .get();

    Assertions.assertEquals(200, response.getStatus());
    Assertions.assertEquals(1, response.readEntity(JobRole.class).getJobRoleId());
  }
}
