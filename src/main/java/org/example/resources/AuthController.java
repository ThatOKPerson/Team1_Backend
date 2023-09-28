package org.example.resources;

import io.swagger.annotations.Api;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.example.api.AuthService;
import org.example.cli.Login;
import org.example.client.DatabaseConnectionException;
import org.example.client.InvalidEmailOrPasswordException;
import org.example.client.InvalidLoginDetailsException;
import org.example.db.AuthDao;
import org.example.db.DatabaseConnector;

/**
 * The type Auth controller.
 */
@Api("Login API")
@Path("/api")
public class AuthController {

  private static AuthService authService;

  /**
   * Instantiates a new Auth controller.
   */
  public AuthController() {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    authService = new AuthService(new AuthDao(), databaseConnector);
  }

  /**
   * Gets hashed password.
   *
   * @param login the login
   * @return the hashed password
   */
  @POST
  @Path("/login")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getHashedPassword(Login login) {
    try {
      return Response.status(HttpStatus.OK_200)
          .entity(authService.login(login))
          .build();
    } catch (DatabaseConnectionException e) {
      return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
    } catch (InvalidLoginDetailsException | InvalidEmailOrPasswordException e) {
      return Response.status(HttpStatus.UNAUTHORIZED_401).build();
    }
  }
}
