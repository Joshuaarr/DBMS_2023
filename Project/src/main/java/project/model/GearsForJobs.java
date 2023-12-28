package project.model;

public class GearsForJobs {

  protected Gear gear;
  protected Job associatedJob;

  public GearsForJobs(Gear gear, Job associatedJob) {
    this.gear = gear;
    this.associatedJob = associatedJob;
  }

  public Gear getGear() {
    return gear;
  }

  public void setGear(Gear gear) {
    this.gear = gear;
  }

  public Job getAssociatedJob() {
    return associatedJob;
  }

  public void setAssociatedJob(Job associatedJob) {
    this.associatedJob = associatedJob;
  }
}
