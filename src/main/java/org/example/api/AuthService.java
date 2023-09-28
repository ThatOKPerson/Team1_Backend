package org.example.api;

import com.password4j.BadParametersException;
import com.password4j.Password;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.sql.SQLException;
import java.util.Date;
import org.example.cli.Login;
import org.example.client.DatabaseConnectionException;
import org.example.client.InvalidEmailOrPasswordException;
import org.example.client.InvalidLoginDetailsException;
import org.example.db.AuthDao;
import org.example.db.DatabaseConnector;
import org.example.validator.AuthValidator;

/**
 * The type Auth service.
 */
public class AuthService {

  private final AuthDao authDao;
  private final AuthValidator authValidator;
  private final DatabaseConnector databaseConnector;

  /**
   * Instantiates a new Auth service.
   *
   * @param authDao           the auth dao
   * @param databaseConnector the database connector
   */
  public AuthService(AuthDao authDao, DatabaseConnector databaseConnector) {
    this.authDao = authDao;
    this.databaseConnector = databaseConnector;
    this.authValidator = new AuthValidator();
  }

  /**
   * Login string.
   *
   * @param login the login
   * @return JWT token
   * @throws DatabaseConnectionException  the database connection exception
   * @throws InvalidLoginDetailsException the invalid login details
   */
  public String login(Login login) throws DatabaseConnectionException,
      InvalidLoginDetailsException, InvalidEmailOrPasswordException {
    String validation = authValidator.isValidLogin(login);

    if (validation != null) {
      throw new InvalidEmailOrPasswordException(validation);
    }

    if (validLogin(login)) {
      return generateJwtToken(login.getEmail(), login.getRoleId());
    }
    throw new InvalidLoginDetailsException();
  }

  private String generateJwtToken(String email, int roleId) {

    Date dateNow = new Date();
    long nextWeek = System.currentTimeMillis() + (86400 * 7 * 1000);
    Date dateNextWeek = new Date(nextWeek);

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    return Jwts.builder().setSubject(email).claim("role", roleId).setIssuedAt(dateNow)
        .setExpiration(dateNextWeek).signWith(key).compact();
  }

  private Boolean validLogin(Login login) throws DatabaseConnectionException,
      InvalidLoginDetailsException {
    try {
      String password = login.getPassword();

      String getPasswordHash =
          authDao.getPasswordHash(login, databaseConnector.getConnection()).getPassword();

      return Password.check(password, getPasswordHash).withArgon2();
    } catch (SQLException | BadParametersException e) {
      System.err.println(e.getMessage());

      throw new InvalidLoginDetailsException();
    }
  }
}
