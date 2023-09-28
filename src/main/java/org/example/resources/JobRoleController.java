package org.example.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.example.api.JobRoleService;
import org.example.client.FailedToGetJobRoleException;
import org.example.db.DatabaseConnector;
import org.example.db.JobRoleDao;

/**
 * This is the controller class which contains the endpoints.
 *
 * @author orankelly
 */
@Path("/api")
public class JobRoleController {
  private static JobRoleService jobRoleService;

  public JobRoleController() {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    jobRoleService = new JobRoleService(new JobRoleDao(), databaseConnector);
  }

  /**
   * This is the GET endpoint for getting all job roles from the database.
   *
   * @return the http response code
   */
  @GET
  @Path("/job-roles")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getRoles() {
    try {
      return Response.ok(jobRoleService.getAllRoles()).build();
    } catch (FailedToGetJobRoleException e) {
      return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
    }
  }
}
