package org.example.client;

/**
 * This is an exception for when no Job Roles are returned.
 * This should only happen if the database connection fails or the SQL is incorrect.
 *
 * @author orankelly
 */
public class FailedToGetJobRoleException extends Throwable {
  @Override
  public String getMessage() {
    return "Failed to get role from the database!";
  }
}
