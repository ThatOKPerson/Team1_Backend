package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.example.client.DatabaseConnectionException;

/**
 * The database connector class contains the method for connecting to the database.
 *
 * @author orankelly
 */
public class DatabaseConnector {
  private static Connection conn;

  /**
   * This method establishes a connection to the database.
   * It uses environment variables to do this.
   *
   * @return the connection to the database.
   * @throws DatabaseConnectionException when connection fails.
   * @throws SQLException                when SQL syntax is incorrect.
   */
  public Connection getConnection() throws DatabaseConnectionException, SQLException {
    String user;
    String password;
    String host;
    String database;

    if (conn != null && !conn.isClosed()) {
      return conn;
    }

    try {
      user = System.getenv("DB_USERNAME");
      password = System.getenv("DB_PASSWORD");
      host = System.getenv("DB_HOST");
      database = System.getenv("DB_NAME");

      if (user == null || password == null || host == null) {
        throw new IllegalArgumentException(
            "Environment variables not set.");
      }

      conn = DriverManager.getConnection("jdbc:mysql://"
          + host + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false", user, password);

      return conn;
    } catch (Exception e) {
      throw new DatabaseConnectionException(e);
    }
  }
}
