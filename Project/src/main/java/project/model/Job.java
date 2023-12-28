package project.model;

public class Job {

  protected int jobID;
  protected String jobName;

  public Job(int jobID, String jobName) {
    this.jobID = jobID;
    this.jobName = jobName;
  }

  public Job(String jobName) {
    this.jobName = jobName;
  }

  public int getJobID() {
    return jobID;
  }

  public void setJobID(int jobID) {
    this.jobID = jobID;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }
}
