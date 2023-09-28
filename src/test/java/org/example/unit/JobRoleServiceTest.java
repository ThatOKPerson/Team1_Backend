package org.example.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.api.JobRoleService;
import org.example.cli.JobRole;
import org.example.client.DatabaseConnectionException;
import org.example.client.FailedToGetJobRoleException;
import org.example.client.FailedToGetSpecificationException;
import org.example.client.SpecificationDoesNotExistException;
import org.example.db.DatabaseConnector;
import org.example.db.JobRoleDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Mocking test class to see how each part of the service works.
 *
 * @author orankelly
 */
public class JobRoleServiceTest {
  JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
  DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

  JobRoleService jobRoleService = new JobRoleService(jobRoleDao, databaseConnector);

  Connection conn;

  /*
  Unit test for the getRoles method

  When the dao returns a SQLException

  Expect the SQLException to be caught

  This should pass without code changes
   */
  @Test
  void getJobRoles_shouldReturnFailedToGetRolesException_whenDaoThrowsSqlException() throws
      SQLException, DatabaseConnectionException {
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(jobRoleDao.getAllRoles(conn)).thenThrow(SQLException.class);

    assertThrows(FailedToGetJobRoleException.class,
        () -> jobRoleService.getAllRoles());
  }

  /*
  Unit test for the getRoles method

  When the dao returns a SQLException

  Expect the DatabaseConnectionException to be caught

  This should pass without code changes
   */
  @Test
  void getJobRoles_shouldReturnFailedToGetRolesException_whenDcThrowsDatabaseConnectionException()
      throws SQLException, DatabaseConnectionException {
    List<JobRole> expectedResult = new ArrayList<>();
    Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
    Mockito.when(jobRoleDao.getAllRoles(conn)).thenReturn(expectedResult);

    assertThrows(FailedToGetJobRoleException.class,
        () -> jobRoleService.getAllRoles());
    try {
      jobRoleService.getAllRoles();
    } catch (FailedToGetJobRoleException e) {
      assertEquals("Failed to get role from the database!", e.getMessage());
    }
  }

  /*
  Unit test for the getRoles method

  When the dao returns a list of roles

  Expect the list of roles to be returned

  This should pass without code changes
   */
  @Test
  void getAllRoles_shouldReturnRoleList()
      throws SQLException, FailedToGetJobRoleException, DatabaseConnectionException {
    List<JobRole> expectedResult = new ArrayList<>();
    JobRole jobRole1 = new JobRole(1, "test", "test", "test", "test", "test", "test");
    JobRole jobRole2 = new JobRole(2, "tester", "tester", "tester", "tester", "tester", "tester");

    expectedResult.add(jobRole1);
    expectedResult.add(jobRole2);

    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(jobRoleDao.getAllRoles(conn)).thenReturn(expectedResult);

    List<JobRole> result = jobRoleService.getAllRoles();

    assertEquals(expectedResult.get(0).getJobRoleId(), result.get(0).getJobRoleId());
    assertEquals(expectedResult.get(0).getCapability(), result.get(0).getCapability());
    assertEquals(expectedResult.get(0).getJobFamily(), result.get(0).getJobFamily());
    assertEquals(expectedResult.get(0).getJobProfileTitle(), result.get(0).getJobProfileTitle());
    assertEquals(expectedResult.get(0).getManagementLevel(), result.get(0).getManagementLevel());
    assertEquals(expectedResult.get(0).getDescription(), result.get(0).getDescription());
    assertEquals(expectedResult.get(0).getMinimalEssentialRequirements(),
        result.get(0).getMinimalEssentialRequirements());
    assertEquals(expectedResult.get(1).getJobRoleId(), result.get(1).getJobRoleId());
    assertEquals(expectedResult.get(1).getCapability(), result.get(1).getCapability());
    assertEquals(expectedResult.get(1).getJobFamily(), result.get(1).getJobFamily());
    assertEquals(expectedResult.get(1).getJobProfileTitle(), result.get(1).getJobProfileTitle());
    assertEquals(expectedResult.get(1).getManagementLevel(), result.get(1).getManagementLevel());
    assertEquals(expectedResult.get(1).getDescription(), result.get(1).getDescription());
    assertEquals(expectedResult.get(1).getMinimalEssentialRequirements(),
        result.get(1).getMinimalEssentialRequirements());
  }

  /*
  Unit test for the getSpecificationById method

  When the dao throws an SpecificationDoesNotExist Exception

  Expect SpecificationDoesNotExist to be thrown
      */
  @Test
  void getSpecificationById_shouldThrowSpecificationDoesNotExistException_whenDaoReturnsNull()
      throws SQLException, DatabaseConnectionException {
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    int id = 1000000;
    Mockito.when(jobRoleDao.getSpecificationById(id, conn)).thenReturn(null);
    assertThrows(SpecificationDoesNotExistException.class,
        () -> jobRoleService.getSpecificationById(id));
  }

  /*
Unit test for the getSpecificationById method

Should return a jobRoleId when Dao Returns an ID

    */
  @Test
  void getSpecificationById_shouldReturnId_whenDaoReturnsId() throws DatabaseConnectionException,
      SQLException, SpecificationDoesNotExistException, FailedToGetSpecificationException {
    int roleId = 1;
    JobRole specification = new JobRole(roleId, "test", "test", "test", "test", "test", "test");
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(jobRoleDao.getSpecificationById(roleId, conn)).thenReturn(specification);
    assertEquals(specification, jobRoleService.getSpecificationById(roleId));
  }

  /*
  Unit test for the getSpecificationById method

  When the dao returns a SQLException

  Expect the SQLException to be caught
  */
  @Test
  void getSpecificationById_shouldThrowSqlException_whenDaoThrowsSqlException() throws
      SQLException, DatabaseConnectionException {
    int id = 10000;
    Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
    Mockito.when(jobRoleDao.getSpecificationById(id, conn)).thenThrow(SQLException.class);

    assertThrows(SQLException.class,
        () -> jobRoleService.getSpecificationById(id));
  }

}
