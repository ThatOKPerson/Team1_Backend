package org.example.client;

/**
 * The type Invalid email or password exception.
 */
public class InvalidEmailOrPasswordException extends Throwable {
  /**
   * Instantiates a new Invalid email or password exception.
   *
   * @param error the error
   */
  public InvalidEmailOrPasswordException(String error) {
    super(error);
  }
}
