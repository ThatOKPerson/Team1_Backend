package org.example.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Login.
 */
public class Login {
  private String email;
  private String password;

  private int roleId;

  /**
   * Instantiates a new Login.
   *
   * @param email    the email
   * @param password the password
   */
  @JsonCreator
  public Login(@JsonProperty("email") String email,
               @JsonProperty("password") String password) {
    this.email = email;
    this.password = password;
  }

  /**
   * Gets email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email.
   *
   * @param email the email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets password.
   *
   * @param password the password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets role id.
   *
   * @return the role id
   */
  public int getRoleId() {
    return roleId;
  }

  /**
   * Sets role id.
   *
   * @param roleId the role id
   */
  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }
}
