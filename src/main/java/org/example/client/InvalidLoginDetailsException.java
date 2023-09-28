package org.example.client;

/**
 * The type Invalid login details.
 */
public class InvalidLoginDetailsException extends Throwable {
  @Override
  public String getMessage() {
    return "Login details do not match";
  }
}
