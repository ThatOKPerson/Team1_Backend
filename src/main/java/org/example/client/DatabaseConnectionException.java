package org.example.client;

/**
 * This is an exception for when a database connection fails.
 * This could be due to incorrect environment variables.
 *
 * @author orankelly
 */
public class DatabaseConnectionException extends Throwable {
  public DatabaseConnectionException(Exception e) {
    super(e);
  }
}
