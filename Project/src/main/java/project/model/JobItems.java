package project.model;

public class JobItems {

  protected Character character;
  protected Job job;
  protected int exp;
  protected JobLevel jobLevel;
  protected boolean isCurrentJob;

  public JobItems(Character character, Job job, int exp, JobLevel jobLevel, boolean isCurrentJob) {
    this.character = character;
    this.job = job;
    this.exp = exp;
    this.jobLevel = jobLevel;
    this.isCurrentJob = isCurrentJob;
  }

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  public int getExp() {
    return exp;
  }

  public void setExp(int exp) {
    this.exp = exp;
  }

  public JobLevel getJobLevel() {
    return jobLevel;
  }

  public void setJobLevel(JobLevel jobLevel) {
    this.jobLevel = jobLevel;
  }

  public boolean isCurrentJob() {
    return isCurrentJob;
  }

  public void setCurrentJob(boolean currentJob) {
    isCurrentJob = currentJob;
  }
}
