package org.example.client;

/**
 * This is an exception for when there is no Job Specification found.
 * This should only happen if the specification is returned.
 *
 * @author LeighM
 */
public class SpecificationDoesNotExistException extends Throwable {
  @Override
  public String getMessage() {
    return "Specification Does Not Exist!";
  }
}
