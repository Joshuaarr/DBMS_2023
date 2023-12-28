package project.model;

public class ConsumableBonus {

  protected Consumable consumable;
  protected AttributeName attributeName;
  protected Double attributeBonusPercent;
  protected int attributeBonusCap;

  public enum AttributeName {
    Strength, Dexterity, Vitality, Mind, Intelligence
  }

  public ConsumableBonus(Consumable consumable, AttributeName attributeName, Double attributeBonusPercent,
    int attributeBonusCap) {
    this.consumable = consumable;
    this.attributeName = attributeName;
    this.attributeBonusPercent = attributeBonusPercent;
    this.attributeBonusCap = attributeBonusCap;
  }

  public Consumable getConsumable() {
    return consumable;
  }

  public void setConsumable(Consumable consumable) {
    this.consumable = consumable;
  }

  public AttributeName getAttributeName() {
    return attributeName;
  }

  public void setAttributeName(AttributeName attributeName) {
    this.attributeName = attributeName;
  }

  public Double getAttributeBonusPercent() {
    return attributeBonusPercent;
  }

  public void setAttributeBonusPercent(Double attributeBonusPercent) {
    this.attributeBonusPercent = attributeBonusPercent;
  }

  public int getAttributeBonusCap() {
    return attributeBonusCap;
  }

  public void setAttributeBonusCap(int attributeBonusCap) {
    this.attributeBonusCap = attributeBonusCap;
  }
}
