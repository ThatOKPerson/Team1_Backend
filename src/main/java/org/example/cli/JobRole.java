package org.example.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is the base class to store a job role as an object.
 *
 * @author orankelly
 */
public class JobRole {
  private int jobRoleId;
  private String capability;
  private String jobFamily;
  private String jobProfileTitle;
  private String managementLevel;
  private String description;
  private String minimalEssentialRequirements;
  private String sharepointLink;

  /**
   * This is the constructor for the jobRole object.
   *
   * @param jobRoleId                    The id associated with the job role.
   * @param capability                   The capability(job group) that the job role belongs to.
   * @param jobFamily                    The family of the job role.
   * @param jobProfileTitle              The title of the job role.
   * @param managementLevel              The management level of the job role.
   * @param description                  The job role's description.
   * @param minimalEssentialRequirements The minimal essential requirements to apply.
   */
  public JobRole(int jobRoleId, String capability, String jobFamily, String jobProfileTitle,
                 String managementLevel, String description, String minimalEssentialRequirements) {
    this.jobRoleId = jobRoleId;
    this.capability = capability;
    this.jobFamily = jobFamily;
    this.jobProfileTitle = jobProfileTitle;
    this.managementLevel = managementLevel;
    this.description = description;
    this.minimalEssentialRequirements = minimalEssentialRequirements;
  }

  /**
   * Constructs a new instance of the JobRole class using JSON properties.
   * This constructor is annotated with {@code @JsonCreator} to specify that it should be used
   * when deserializing JSON data into a JobRole object. It accepts individual JSON properties
   * as arguments and initializes the corresponding fields of the JobRole instance.
   *
   * @param capability                   The capability(job group) that the job role belongs to.
   * @param jobFamily                    The family of the job role.
   * @param jobProfileTitle              The title of the job role.
   * @param managementLevel              The management level of the job role.
   * @param description                  The job role's description.
   * @param minimalEssentialRequirements The minimal essential requirements to apply.
   * @param sharepointLink               The link to job profile on sharepoint
   */
  @JsonCreator
  public JobRole(
      @JsonProperty("roleId") int roleId,
      @JsonProperty("capability") String capability,
      @JsonProperty("jobFamily") String jobFamily,
      @JsonProperty("jobProfileTitle") String jobProfileTitle,
      @JsonProperty("managementLevel") String managementLevel,
      @JsonProperty("description") String description,
      @JsonProperty("minimalEssentialRequirements") String minimalEssentialRequirements,
      @JsonProperty("sharepointLink") String sharepointLink) {
    this.setJobRoleId(roleId);
    this.setCapability(capability);
    this.setJobFamily(jobFamily);
    this.setJobProfileTitle(jobProfileTitle);
    this.setManagementLevel(managementLevel);
    this.setDescription(description);
    this.setMinimalEssentialRequirements(minimalEssentialRequirements);
    this.setSharepointLink(sharepointLink);
  }

  public int getJobRoleId() {
    return jobRoleId;
  }

  public void setJobRoleId(int roleId) {
    this.jobRoleId = roleId;
  }

  public String getJobFamily() {
    return jobFamily;
  }

  public void setJobFamily(String jobFamily) {
    this.jobFamily = jobFamily;
  }

  public String getCapability() {
    return capability;
  }

  public void setCapability(String capability) {
    this.capability = capability;
  }

  public String getJobProfileTitle() {
    return jobProfileTitle;
  }

  public void setJobProfileTitle(String jobProfileTitle) {
    this.jobProfileTitle = jobProfileTitle;
  }

  public String getManagementLevel() {
    return managementLevel;
  }

  public void setManagementLevel(String managementLevel) {
    this.managementLevel = managementLevel;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getMinimalEssentialRequirements() {
    return minimalEssentialRequirements;
  }

  public void setMinimalEssentialRequirements(String minimalEssentialRequirements) {
    this.minimalEssentialRequirements = minimalEssentialRequirements;
  }

  public String getSharepointLink() {
    return sharepointLink;
  }

  public void setSharepointLink(String sharepointLink) {
    this.sharepointLink = sharepointLink;
  }
}
