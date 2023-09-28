package org.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.example.cli.JobRole;


/**
 * This is the Dao class which uses the database connection.
 * It contains methods to run SQL commands
 *
 * @author orankelly
 */
public class JobRoleDao {

  /**
   * This is a method which runs a SQL query.
   * It should get all roles from the database.
   *
   * @param c Connection to the database passed by param.
   * @return The list of job roles from the database.
   * @throws SQLException If the SQL query doesn't execute.
   */
  public List<JobRole> getAllRoles(Connection c) throws SQLException {
    Statement st = c.createStatement();

    ResultSet rs = st.executeQuery("SELECT roleId, capability, jobFamily, jobProfileTitle,"
            + " managementLevel, description, minimalEssentialRequirements FROM Roles;");

    List<JobRole> list = new ArrayList<>();

    while (rs.next()) {
      JobRole jobRole = new JobRole(
              rs.getInt("roleId"),
              rs.getString("capability"),
              rs.getString("jobFamily"),
              rs.getString("jobProfileTitle"),
              rs.getString("managementLevel"),
              rs.getString("description"),
              rs.getString("minimalEssentialRequirements")
      );
      list.add(jobRole);
    }
    return list;
  }

  /**
   * This is a method which runs a SQL query.
   * It should get a job specification using the role ID.
   *
   * @param c Connection to the database passed by param.
   * @return A job specification from the database using role ID.
   * @throws SQLException If the SQL query doesn't execute.
   */
  public JobRole getSpecificationById(int id, Connection c) throws SQLException {

    String selectStatement = "SELECT roleId, capability, jobFamily, jobProfileTitle, "
           + "managementLevel, description, minimalEssentialRequirements, sharepointLink "
           + "FROM Roles Where roleId = ?;";

    PreparedStatement st = c.prepareStatement(selectStatement);

    st.setInt(1, id);

    ResultSet rs = st.executeQuery();

    while (rs.next()) {
      JobRole specification = new JobRole(
              rs.getInt("roleId"),
              rs.getString("capability"),
              rs.getString("jobFamily"),
              rs.getString("jobProfileTitle"),
              rs.getString("managementLevel"),
              rs.getString("description"),
              rs.getString("minimalEssentialRequirements"),
              rs.getString("sharepointLink")
      );
      return specification;
    }
    return null;
  }
}