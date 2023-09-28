package org.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.cli.Login;

/**
 * The type Auth dao.
 */
public class AuthDao {

  /**
   * Gets password hash.
   *
   * @param login the login
   * @param conn  the conn
   * @return the password hash
   * @throws SQLException the sql exception
   */
  public Login getPasswordHash(Login login, Connection conn) throws SQLException {

    String loginQuery = "SELECT PasswordHash, RoleID FROM `User` WHERE Email=?;";

    PreparedStatement preparedStatement = conn.prepareStatement(loginQuery);

    preparedStatement.setString(1, login.getEmail());

    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
      login.setPassword(resultSet.getString("PasswordHash"));
      login.setRoleId(resultSet.getInt("RoleId"));
      return login;
    }

    return login;
  }
}
