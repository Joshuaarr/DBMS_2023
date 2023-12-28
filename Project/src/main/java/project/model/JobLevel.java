package project.model;

public class JobLevel {

  protected int levelID;
  protected int minExp;
  protected int maxExp;
  protected boolean isMaxLevel;

  public JobLevel(int minExp, int maxExp, boolean isMaxLevel) {
    this.minExp = minExp;
    this.maxExp = maxExp;
    this.isMaxLevel = isMaxLevel;
  }

  public JobLevel(int levelID, int minExp, int maxExp, boolean isMaxLevel) {
    this.levelID = levelID;
    this.minExp = minExp;
    this.maxExp = maxExp;
    this.isMaxLevel = isMaxLevel;
  }

  public int getLevelID() {
    return levelID;
  }

  public void setLevelID(int levelID) {
    this.levelID = levelID;
  }

  public int getMinExp() {
    return minExp;
  }

  public void setMinExp(int minExp) {
    this.minExp = minExp;
  }

  public int getMaxExp() {
    return maxExp;
  }

  public void setMaxExp(int maxExp) {
    this.maxExp = maxExp;
  }

  public boolean isMaxLevel() {
    return isMaxLevel;
  }

  public void setMaxLevel(boolean maxLevel) {
    isMaxLevel = maxLevel;
  }
}
