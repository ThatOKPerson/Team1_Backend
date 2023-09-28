package org.example.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.password4j.Password;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.example.TrueApplication;
import org.example.TrueConfiguration;
import org.example.cli.Login;
import org.example.client.DatabaseConnectionException;
import org.example.db.DatabaseConnector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The type Auth controller integration test.
 */
@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthControllerIntegrationTest {
  private final String baseUrl = System.getenv("BASE_URL");
  private final String apiLogin = baseUrl + "/login";
  private static final String email = System.getenv("ADMIN_EMAIL");
  private static final String password = System.getenv("ADMIN_PASSWORD");

  /**
   * The App.
   */
  static final DropwizardAppExtension<TrueConfiguration> APP = new DropwizardAppExtension<>(
      TrueApplication.class, null,
      new ResourceConfigurationSourceProvider()
  );

  private static void addAdminAccount() throws SQLException, DatabaseConnectionException {
    String passwordHash = Password.hash(password).withArgon2().getResult();
    DatabaseConnector databaseConnector = new DatabaseConnector();
    Connection c = databaseConnector.getConnection();

    String insertStatement = "INSERT INTO `User`(Email,PasswordHash,RoleID) VALUES(?,?,1);";
    PreparedStatement preparedStatement = c.prepareStatement(insertStatement);

    preparedStatement.setString(1, email);
    preparedStatement.setString(2, passwordHash);

    preparedStatement.executeUpdate();
  }

  private static void deleteAccount() throws SQLException, DatabaseConnectionException {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    Connection c = databaseConnector.getConnection();

    try {
      String deleteStatement = "DELETE FROM `User` WHERE Email = ?";
      PreparedStatement preparedStatement = c.prepareStatement(deleteStatement);

      preparedStatement.setString(1, email);

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Setup Tests.
   */
  @BeforeAll
  static void setup() {
    try {
      addAdminAccount();
    } catch (SQLException | DatabaseConnectionException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Post login should return 200 and token on valid login.
   */
  @Test
  void postLogin_shouldReturn200AndToken_OnValidLogin() {
    Login login = new Login(email, password);
    Response response = APP.client().target(apiLogin)
        .request()
        .post(Entity.entity(login, MediaType.APPLICATION_JSON_TYPE));

    String token = response.readEntity(String.class);

    assertEquals(200, response.getStatus());
    assertNotNull(token);
  }

  /**
   * Post login should return 401 on invalid email.
   */
  @Test
  void postLogin_shouldReturn401_OnInvalidEmail() {
    Login login = new Login("@mail.com", password);
    Response response = APP.client().target(apiLogin)
        .request()
        .post(Entity.entity(login, MediaType.APPLICATION_JSON_TYPE));

    assertEquals(401, response.getStatus());
  }

  @Test
  void postLogin_shouldReturn401_OnInvalidPassword() {
    Login login = new Login(email, "");
    Response response = APP.client().target(apiLogin)
        .request()
        .post(Entity.entity(login, MediaType.APPLICATION_JSON_TYPE));

    assertEquals(401, response.getStatus());
  }

  /**
   * Post login should return 401 on invalid login.
   */
  @Test
  void postLogin_shouldReturn401_OnInvalidLogin() {
    Login login = new Login(email, "wrongPassword");
    Response response = APP.client().target(apiLogin)
        .request()
        .post(Entity.entity(login, MediaType.APPLICATION_JSON_TYPE));

    assertEquals(401, response.getStatus());
  }

  /**
   * Teardown.
   */
  @AfterAll
  public static void after() {
    try {
      deleteAccount();
    } catch (SQLException | DatabaseConnectionException e) {
      System.err.println(e.getMessage());
    }
  }
}
