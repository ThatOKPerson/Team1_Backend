package org.example.validator;

import org.example.cli.Login;

/**
 * The type Auth validator.
 */
public class AuthValidator {
  private final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
      + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

  /**
   * Is valid email string.
   *
   * @param login the login
   * @return the string
   */
  public String isValidLogin(Login login) {

    if (login.getEmail().isEmpty()) {
      return "Please enter a email.";
    }

    if (!login.getEmail().matches(regexPattern)) {
      return "Email does not match expected pattern.";
    }

    if (login.getPassword().isEmpty()) {
      return "Please enter a password.";
    }

    return null;
  }
}
