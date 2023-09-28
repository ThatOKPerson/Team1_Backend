package org.example.api;

import java.sql.SQLException;
import java.util.List;
import org.example.cli.JobRole;
import org.example.client.DatabaseConnectionException;
import org.example.client.FailedToGetJobRoleException;
import org.example.client.FailedToGetSpecificationException;
import org.example.client.SpecificationDoesNotExistException;
import org.example.db.DatabaseConnector;
import org.example.db.JobRoleDao;

/**
 * This is the service class which uses the dao and database connection.
 * It uses these to execute the SQL query and catches any errors.
 *
 * @author orankelly
 */
public class JobRoleService {
  public final JobRoleDao dao;
  public final DatabaseConnector databaseConnector;

  public JobRoleService(JobRoleDao dao, DatabaseConnector databaseConnector) {
    this.dao = dao;
    this.databaseConnector = databaseConnector;
  }

  /**
   * This is a method which runs the SQL query from the dao method.
   *
   * @return The list of job roles from the database.
   * @throws FailedToGetJobRoleException When the database fails to connect or SQL is incorrect.
   */
  public List<JobRole> getAllRoles() throws FailedToGetJobRoleException {
    try {
      return dao.getAllRoles(databaseConnector.getConnection());
    } catch (SQLException | DatabaseConnectionException e) {
      System.err.println(e.getMessage());
      throw new FailedToGetJobRoleException();
    }
  }

  /**
   * This is a method which runs the SQL query from the dao method.
   *
   * @return A job specification using the role ID.
   * @throws FailedToGetJobRoleException When the database fails to connect or SQL is incorrect.
   */
  public JobRole getSpecificationById(int roleId) throws SQLException,
      FailedToGetSpecificationException, SpecificationDoesNotExistException {
    try {
      JobRole specification = dao.getSpecificationById(roleId, databaseConnector.getConnection());
      if (specification == null) {
        throw new SpecificationDoesNotExistException();
      }
      return specification;
    } catch (DatabaseConnectionException e) {
      System.err.println(e.getMessage());
      throw new FailedToGetSpecificationException();
    }
  }
}
