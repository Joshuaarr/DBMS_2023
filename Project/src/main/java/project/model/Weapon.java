package project.model;

public class Weapon extends Equipment {

  protected int damageDone;
  protected Double autoAttack;
  protected Double autoDelay;
  protected Job job;

  public Weapon(int itemCategoryID, String itemName, int maxStackSize, Double vendorPrice,
    int itemLevel, int requiredLevel, int damageDone, Double autoAttack, Double autoDelay,
    Job job) {
    super(itemCategoryID, itemName, maxStackSize, vendorPrice, itemLevel, requiredLevel);
    this.damageDone = damageDone;
    this.autoAttack = autoAttack;
    this.autoDelay = autoDelay;
    this.job = job;
  }

  public Weapon(String itemName, int maxStackSize, Double vendorPrice, int itemLevel,
    int requiredLevel, int damageDone, Double autoAttack, Double autoDelay, Job job) {
    super(itemName, maxStackSize, vendorPrice, itemLevel, requiredLevel);
    this.damageDone = damageDone;
    this.autoAttack = autoAttack;
    this.autoDelay = autoDelay;
    this.job = job;
  }
  

  public int getDamageDone() {
    return damageDone;
  }

  public void setDamageDone(int damageDone) {
    this.damageDone = damageDone;
  }

  public Double getAutoAttack() {
    return autoAttack;
  }

  public void setAutoAttack(Double autoAttack) {
    this.autoAttack = autoAttack;
  }

  public Double getAutoDelay() {
    return autoDelay;
  }

  public void setAutoDelay(Double autoDelay) {
    this.autoDelay = autoDelay;
  }

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }
}
