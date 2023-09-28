package org.example.unit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.password4j.Password;
import java.sql.Connection;
import java.sql.SQLException;
import org.example.api.AuthService;
import org.example.cli.Login;
import org.example.client.DatabaseConnectionException;
import org.example.client.InvalidEmailOrPasswordException;
import org.example.client.InvalidLoginDetailsException;
import org.example.db.AuthDao;
import org.example.db.DatabaseConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * The type Auth service test.
 */
public class AuthServiceTest {
  String email = System.getenv("ADMIN_EMAIL");
  String password = System.getenv("ADMIN_PASSWORD");
  String hashedPassword = Password.hash(password).
      withArgon2().
      getResult();
  /**
   * The Auth dao.
   */
  AuthDao authDao = Mockito.mock(AuthDao.class);
  /**
   * The Database connector.
   */
  DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

  /**
   * The Auth service.
   */
  AuthService authService = new AuthService(authDao, databaseConnector);

  /**
   * The Conn.
   */
  Connection conn;

  /**
   * The Login.
   */
  Login login;

  Login hashedLogin;

  /**
   * Sets .
   */
  @BeforeEach
  void setup() {
    login = new Login(email, password);
    hashedLogin = new Login(email, hashedPassword);
  }

  /**
   * Login should return jwt when valid login.
   *
   * @throws DatabaseConnectionException the database connection exception
   * @throws SQLException                the sql exception
   */
  @Test
  void login_shouldReturnJwt_whenValidLogin()
      throws DatabaseConnectionException, SQLException {
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(authDao.getPasswordHash(login, conn)).thenReturn(hashedLogin);

    assertDoesNotThrow(() -> authService.login(login));
  }

  /**
   * Login should throw invalid login exception when sql exception.
   *
   * @throws DatabaseConnectionException the database connection exception
   * @throws SQLException                the sql exception
   */
  @Test
  void login_shouldThrowInvalidLoginException_whenSqlException()
      throws DatabaseConnectionException, SQLException {
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(authDao.getPasswordHash(login, conn)).thenThrow(SQLException.class);

    assertThrows(InvalidLoginDetailsException.class,
        () -> authService.login(login));
  }

  /**
   * Login should throw invalid login exception when password does not match hash.
   *
   * @throws DatabaseConnectionException the database connection exception
   * @throws SQLException                the sql exception
   */
  @Test
  void login_shouldThrowInvalidLoginException_whenPasswordDoesNotMatchHash()
      throws DatabaseConnectionException, SQLException {
    login.setPassword("WrongPassword");
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(authDao.getPasswordHash(login, conn)).thenReturn(hashedLogin);

    assertThrows(InvalidLoginDetailsException.class,
        () -> authService.login(login));
  }

  @Test
  void login_shouldThrowInvalidEmailOrPasswordException_whenEmailDoesNotMatchPattern()
      throws DatabaseConnectionException, SQLException {
    login.setEmail("@mail.com");
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(authDao.getPasswordHash(login, conn)).thenReturn(hashedLogin);

    InvalidEmailOrPasswordException exception = assertThrows(InvalidEmailOrPasswordException.class,
        () -> authService.login(login));

    assertEquals("Email does not match expected pattern.", exception.getMessage());
  }

  @Test
  void login_shouldThrowInvalidEmailOrPasswordException_whenEmailIsEmpty()
      throws DatabaseConnectionException, SQLException {
    login.setEmail("");
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(authDao.getPasswordHash(login, conn)).thenReturn(hashedLogin);

    InvalidEmailOrPasswordException exception = assertThrows(InvalidEmailOrPasswordException.class,
        () -> authService.login(login));

    assertEquals("Please enter a email.", exception.getMessage());
  }

  @Test
  void login_shouldThrowInvalidEmailOrPasswordException_whenPasswordIsEmpty()
      throws DatabaseConnectionException, SQLException {
    login.setPassword("");
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(authDao.getPasswordHash(login, conn)).thenReturn(hashedLogin);

    InvalidEmailOrPasswordException exception = assertThrows(InvalidEmailOrPasswordException.class,
        () -> authService.login(login));

    assertEquals("Please enter a password.", exception.getMessage());
  }
}
