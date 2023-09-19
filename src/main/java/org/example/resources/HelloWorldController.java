package org.example.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.example.api.HelloWorldService;

/**
 * Hello World Controller Route.
 */
@Path("/api")
public class HelloWorldController {

  private final HelloWorldService helloWorldService = new HelloWorldService();

  /**
   * Returns Hello World.
   *
   * @return Response
   */
  @GET
  @Path("/helloWorld")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDeliveryEmployees() {
    try {
      return Response.ok(helloWorldService.helloWorld()).build();
    } catch (Exception e) {
      System.err.println(e.getMessage());

      return Response.serverError().build();
    }
  }
}
