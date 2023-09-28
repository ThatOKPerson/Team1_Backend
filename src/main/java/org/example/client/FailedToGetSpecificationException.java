package org.example.client;

/**
 * This is an exception for when no Job Specification is returned.
 * This should only happen if the database connection fails or the SQL is incorrect.
 *
 * @author LeighM
 */
public class FailedToGetSpecificationException extends Throwable {
  @Override
  public String getMessage() {
    return "Failed to job specification!";
  }
}

